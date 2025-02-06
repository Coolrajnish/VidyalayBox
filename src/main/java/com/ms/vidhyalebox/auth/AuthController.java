package com.ms.vidhyalebox.auth;

import com.ms.shared.api.auth.*;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.ModalDTO;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.FatalException;
import com.ms.vidhyalebox.emailsender.EmailDetails;
import com.ms.vidhyalebox.emailsender.EmailService;
import com.ms.vidhyalebox.emailsender.EmailServiceImpl;
import com.ms.vidhyalebox.orgclient.IOrgClientService;

import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmailService emailService;
    private final UserServiceImpl _userService;
   // private final IUserRepo userRepo;

    private final TokenBlacklistService _tokenBlacklistService;

    private final AuthenticationManager _authenticationManager;
    private final JwtTokenProvider _jwtTokenProvider;

    private final IOrgClientService _iOrgClientService;
//    private final IStaffService _iStaffService;
//    private final IParentService _iParentService;
//    private final ITeacherService _iTeacherService;
    private final IUserService _iUserService;
    public AuthController(TokenBlacklistService tokenBlacklistService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IOrgClientService iOrgClientService, IUserService iUserService, EmailService emailService, UserServiceImpl userService) {
        _tokenBlacklistService = tokenBlacklistService;
        _authenticationManager = authenticationManager;
        _jwtTokenProvider = jwtTokenProvider;
        _iOrgClientService = iOrgClientService;
//        _iStaffService = iStaffService;
//        _iParentService = iParentService;
//        _iTeacherService = iTeacherService;
        _iUserService = iUserService;
        this.emailService = emailService;
        _userService = userService;
       // this.userRepo = userRepo;
    }


    @PostMapping("/org-signup")
    public GenericResponse registerOrg(@Valid @RequestBody OrgSignupRequestDTO orgSignupRequestDTO) {

        List<Notification> notifications = new ArrayList<>();

        if (_iOrgClientService.isEmailAlreadyExist(orgSignupRequestDTO.getEmailAddress())) {
            Notification notification = new Notification();
            notification.setNoificationCode("401");
            notification.setNotificationDescription("User's email address already exists");
            notifications.add(notification);
        }

        if (_iOrgClientService.isMobileNumberExist(orgSignupRequestDTO.getMobileNumber())) {
            Notification notification = new Notification();
            notification.setNoificationCode("401");
            notification.setNotificationDescription("User's Phone Number already exists");
            notifications.add(notification);
        }

        if (!notifications.isEmpty()) {

            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            genericResponse.setNotifications(notifications);

            return genericResponse;
        }
        String token = UUID.randomUUID().toString();  // This token can be generated using JWT having expiry
        GenericDTO genericDTO = _iOrgClientService.signup(orgSignupRequestDTO, token);

        List<GenericDTO> genericDTOs = new ArrayList<>();
        genericDTOs.add(genericDTO);

        ModalDTO modalDTO = new ModalDTO();
        modalDTO.setData(genericDTOs);

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
        genericResponse.setModalDTO(modalDTO);
        Notification notification = new Notification();
        notification.setNoificationCode("200");
        notification.setNotificationDescription("Organization signed up successfully");
        notifications.add(notification);
        EmailDetails edetails = new EmailDetails();
        edetails.setRecipient(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getEmailAddress());
        edetails.setSubject("VidyalayBox email verification");
        edetails.setMsgBody("Click to verify mail http://localhost:5173/sign-in?token="+token);
        emailService.sendSimpleMail(edetails);  // If email send fails then put that into failed queue and retry attempt

        return genericResponse;
    }

    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        boolean isVerified = _userService.verifyUser(token);
        return isVerified
                ? ResponseEntity.ok("User verified successfully!")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
    }

    @GetMapping("/initiatepwdreset")
    public ResponseEntity<String> initiatePWDReset(@RequestParam String email) {

        _userService.initiatePWDReset(email);

        return ResponseEntity.ok("Password reset initiated successfully!");
    }

    @PostMapping("/resetpwd")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        if (_userService.validateResetToken(token)) {
            _userService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password reset successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) throws FatalException {
        Authentication authentication = _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        if (userDetails.isEnabled()) { // Check whether User is active or not
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
           // System.out.println(userPrincipal.getAuthorities()+"--"+userPrincipal.getPassword()+"--"+userPrincipal.getId()+"userPrincipal  ------"+userPrincipal.getUsername());
            String jwtToken = _jwtTokenProvider.generateToken(userPrincipal.getUsername());
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            return ResponseEntity.ok(new LoginResponseDTO(jwtToken, userDetails.getId(),userDetails.getOrgId(), roles, userDetails.isEnabled(),
                    userDetails.isAccountNonLocked(), userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), _jwtTokenProvider.getExpiryDuration(), StringUtils.EMPTY));
        }

        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().message("User has been deactivated/locked !!").build();
        return ResponseEntity.badRequest().body(loginResponseDTO);
    }

  /*  @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String tokenHeader) {
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String token = tokenHeader.substring(7);
        _tokenBlacklistService.blacklistToken(token);
        return ResponseEntity.ok("Successfully logged out");
    }*/
}
