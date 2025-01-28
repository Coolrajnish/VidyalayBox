package com.ms.vidhyalebox.orgclient;

import com.ms.shared.api.auth.LoginRequestDTO;
import com.ms.shared.api.auth.LoginResponseDTO;
import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.ModalDTO;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.FatalException;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import com.ms.vidhyalebox.auth.JwtTokenProvider;
import com.ms.vidhyalebox.auth.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/org")
public class OrgClientController extends GenericController< OrgSignupRequestDTO ,Long> {

    private final IOrgClientService _orgClientService;

    public OrgClientController(IOrgClientService orgClientService) {
        _orgClientService = orgClientService;
    }


//    @PostMapping("/signup")
//    public ResponseEntity<OrgClientEntity> signup(@RequestBody OrgClientEntity orgClientEntity) {
//        OrgClientEntity savedOrgClient = orgClientService.signup(orgClientEntity);
//        return ResponseEntity.ok(savedOrgClient);
//    }

//
//    @PostMapping("/signup")
//    public GenericResponse registerUser(@Valid @RequestBody OrgSignupRequestDTO orgSignupRequestDTO) {
//
//        List<Notification> notifications = new ArrayList<>();
//
//        if (orgClientService.isEmailAlreadyExist(orgSignupRequestDTO.getEmailAddress())) {
//            Notification notification = new Notification();
//            notification.setNoificationCode("401");
//            notification.setNotificationDescription("User's email address already exists");
//            notifications.add(notification);
//        }
//
//        if (orgClientService.isMobileNumberExist(orgSignupRequestDTO.getIsdCode().concat(orgSignupRequestDTO.getMobileNumber()))) {
//            Notification notification = new Notification();
//            notification.setNoificationCode("401");
//            notification.setNotificationDescription("User's Phone Number already exists");
//            notifications.add(notification);
//        }
//
//        if (!notifications.isEmpty()) {
//
//            GenericResponse genericResponse = new GenericResponse();
//            genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//            genericResponse.setNotifications(notifications);
//
//            return genericResponse;
//        }
//
//        GenericDTO genericDTO = orgClientService.signup(orgSignupRequestDTO);
//
//        List<GenericDTO> genericDTOs = new ArrayList<>();
//        genericDTOs.add(genericDTO);
//
//        ModalDTO modalDTO = new ModalDTO();
//        modalDTO.setData(genericDTOs);
//
//        GenericResponse genericResponse = new GenericResponse();
//        genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
//        genericResponse.setModalDTO(modalDTO);
//
//        return genericResponse;
//    }
//
//
//
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestParam String emailAddress, @RequestParam String password) {
////        String token = orgClientService.login(emailAddress, password);
////        return ResponseEntity.ok(token);
////    }
//
//
//
//    @PostMapping("/signin")
//    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) throws FatalException {
//
//        Authentication authentication = _authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
//
//        if (userDetails.isEnabled()) { // Check whether User is active or not
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//            String jwtToken = _jwtUtils.generateToken(userPrincipal.getUsername());
//
//            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//            return ResponseEntity.ok(new LoginResponseDTO(jwtToken, userDetails.getId(), roles, userDetails.isEnabled(),
//                    userDetails.isAccountNonLocked(), userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), _jwtUtils.getExpiryDuration(), StringUtils.EMPTY));
//        }
//
//        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().message("User has been deactivated/locked !!").build();
//        return ResponseEntity.badRequest().body(loginResponseDTO);
//    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        _orgClientService.logout();
        return ResponseEntity.ok().build();
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        _orgClientService.setAuthToken(getAuthToken());
        return _orgClientService;
    }

}
