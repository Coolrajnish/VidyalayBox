package com.ms.vidhyalebox.auth;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.emailsender.EmailDetails;
import com.ms.vidhyalebox.emailsender.EmailService;
import com.ms.vidhyalebox.orgclient.IOrgClientService;
import com.ms.vidhyalebox.sharedapi.LoginRequestDTO;
import com.ms.vidhyalebox.sharedapi.LoginResponseDTO;
import com.ms.vidhyalebox.sharedapi.OrgSignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserServiceImpl;
import com.ms.vidhyalebox.util.FatalException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final EmailService emailService;
	private final UserServiceImpl _userService;
	private final AuthenticationManager _authenticationManager;
	private final JwtTokenProvider _jwtTokenProvider;
	private final TokenBlacklistService tokenService;
	private final IOrgClientService _iOrgClientService;

	public AuthController(TokenBlacklistService tokenService,AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			IOrgClientService iOrgClientService, IUserService iUserService, EmailService emailService,
			EmailService emailService1, UserServiceImpl userService) {
		_authenticationManager = authenticationManager;
		_jwtTokenProvider = jwtTokenProvider;
		this.tokenService = tokenService;
		_iOrgClientService = iOrgClientService;
		this.emailService = emailService1;
		_userService = userService;
	}

	@PostMapping("/org-signup")
	public ResponseEntity<APiResponse<GenericDTO>> registerOrg(
			@Valid @RequestBody OrgSignupRequestDTO orgSignupRequestDTO) {

		if (_iOrgClientService.isEmailAlreadyExist(orgSignupRequestDTO.getEmailAddress())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new APiResponse<>("error", "User's email address already exists", null, null));
		}

		if (_iOrgClientService.isMobileNumberExist(orgSignupRequestDTO.getMobileNumber())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new APiResponse<>("error", "User's Phone Number already exists", null, null));
		}

		GenericDTO genericDTO = null;
		try {
			String token = UUID.randomUUID().toString(); // This token can be generated using JWT having expiry
			genericDTO = _iOrgClientService.signup(orgSignupRequestDTO, token);

			EmailDetails edetails = new EmailDetails();
			edetails.setRecipient(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getEmailAddress());
			edetails.setSubject("VidyalayBox email verification");
			edetails.setMsgBody("Click to verify mail http://localhost:5173/sign-in?token=" + token);
			emailService.sendSimpleMail(edetails); // If email send fails then put that into failed queue and retry
													// attempt
		} catch (Exception e) {
			return ResponseEntity.ok(
					new APiResponse<>("error", "Organization signed up failed " + e.getLocalizedMessage(), null, null));

		}

		return ResponseEntity.ok(new APiResponse<>("success", "Organization signed up successfully", genericDTO, null));
	}

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		return emailService.sendSimpleMail(details);
	}

	@GetMapping("/verify")
	public ResponseEntity<APiResponse<Object>> verifyUser(@RequestParam String token) {

		boolean isVerified = _userService.verifyUser(token);
		APiResponse<Object> ApiResponse = new APiResponse<>(isVerified ? "success" : "error",
				isVerified ? "User verified successfully" : "User verification fails", null, null);

		return isVerified ? ResponseEntity.ok(ApiResponse)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse);
	}

	@GetMapping("/initiatepwdreset")
	public ResponseEntity<APiResponse<Object>> initiatePWDReset(@RequestParam String email) {

		return _userService.initiatePWDReset(email);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<APiResponse<Object>> resetPassword(@RequestParam String token,
			@RequestParam String newPassword) {
		if (_userService.validateResetToken(token)) {
			APiResponse<Object> resp = _userService.resetPassword(token, newPassword);
			return resp.getStatus().contains("success") ? ResponseEntity.ok(resp)
					: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new APiResponse<>("error", "Token validation fails", null, null));
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<APiResponse<LoginResponseDTO>> authenticateUser(
			@Valid @RequestBody LoginRequestDTO loginRequest) throws FatalException {
		try {
			Authentication authentication = _authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
			if (userDetails.isEnabled()) { // Check whether User is active or not
				SecurityContextHolder.getContext().setAuthentication(authentication);
				UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
				String jwtToken = _jwtTokenProvider.generateToken(userPrincipal.getUsername());
				List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList());
				return ResponseEntity.ok(new APiResponse<>("success", "User signed-in successfully",
						new LoginResponseDTO(jwtToken, userDetails.getId(), userDetails.getOrgId(), roles,
								userDetails.isEnabled(), userDetails.isAccountNonLocked(),
								userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(),
								_jwtTokenProvider.getExpiryDuration(), StringUtils.EMPTY),
						null));

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ResponseEntity.badRequest()
				.body(new APiResponse<>("error", "Signed-in failed, User has been deactivated/locked !!", null, null));
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String tokenHeader) {
		if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
			return ResponseEntity.badRequest().body("Invalid token");
		}
		String token = tokenHeader.substring(7);
		tokenService.addToBlacklist(token, Instant.now()); 
		return ResponseEntity.ok("Successfully logged out");
	}
}
