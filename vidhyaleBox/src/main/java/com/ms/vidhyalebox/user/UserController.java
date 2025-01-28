package com.ms.vidhyalebox.user;

import com.ms.shared.api.auth.SignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.ModalDTO;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/user")
public class UserController extends GenericController<SignupRequestDTO, Long> {

	private final UserServiceImpl _userService;

	
	public UserController(final UserServiceImpl userService
                          ) {
		_userService = userService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		_userService.setAuthToken(getAuthToken());
		return _userService;
	}


	/*@PostMapping("/signup")
	public ResponseEntity<UserEntity> signup(@RequestBody UserEntity userEntity) {
		UserEntity savedUser = _userService.signup(userEntity);
		return ResponseEntity.ok(savedUser);
	}*/

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String phoneNumber, @RequestParam String password) {
		String token = _userService.login(phoneNumber, password);
		return ResponseEntity.ok(token);
	}

	/*@PostMapping("/logout")
	public ResponseEntity<Void> logout() {
		_userService.logout();
		return ResponseEntity.ok().build();
	}*/


//	@PostMapping("/logout")
//	@RolesAllowed({"CLIENT", "ADMIN"})
//	public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token, @RequestBody LogoutRequestDTO logoutRequestDTO) throws FatalException {
//
//		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String deviceId = logoutRequestDTO.getDeviceInfoDTO().getDeviceId();
//
//		UserDeviceDTO userDeviceDTO = _userDeviceService.getUserDevice(userPrincipal.getId(), deviceId);
//		userDeviceDTO.setSessionLogout(true);
//		_userDeviceService.update(userDeviceDTO);
//		_refreshTokenService.deactivateRefreshToken(userDeviceDTO.getRefreshToken(), userPrincipal.getId(), deviceId);
//
//		SignupRequestDTO userRequestDTO = (SignupRequestDTO) _userService.findByEmailAddress(userPrincipal.getUsername());
//		_userService.invalidateSession(userRequestDTO.getEmail(), token);
//		return ResponseEntity.ok(new LogoutResponseDTO(true, "User has successfully logged out from the system!"));
//	}


	
//	@PutMapping("/password")
//	public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal currentUser, @RequestBody ChangePasswordRequestDTO changePasswordRequest) {
//
//		//Reset the password
//		boolean isPasswordUpdated = _userService.changePassword(currentUser.getId(), changePasswordRequest);
//
//		if (isPasswordUpdated) {
//			_userService.invalidateUsersAllSession(currentUser.getId()); //invalidate session
//		}
//
//		return ResponseEntity.ok(new ChangePasswordResponseDTO(isPasswordUpdated));
//	}

/*	@GetMapping("/detail/{id}") //TODO
	public GenericResponse getUserById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		GenericDTO genericDTO = _userService.getUserById(id);
        List<GenericDTO> genericDTOs = new ArrayList<>();
        genericDTOs.add(genericDTO);
        return getResponse(genericDTOs);
	}
	
	@PostMapping(value = "/send/otp")
	public  ResponseEntity<SendOTPResponseDTO> sendVerificationOTP(@RequestHeader("Authorization") String token, @Valid @RequestBody SendOTPRequestDTO userVerificationRequest) {

		UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long verificationRequestId = _userService.processSendOtpRequest(currentUser.getId(), userVerificationRequest.getTarget(), userVerificationRequest.getType(), userVerificationRequest.getPurpose());
		boolean isVerificationRequestProcessed = Objects.nonNull(verificationRequestId);

		SendOTPResponseDTO userVerificationResponseDTO = new SendOTPResponseDTO(userVerificationRequest.getTarget(), false, isVerificationRequestProcessed);
		userVerificationResponseDTO.setId(verificationRequestId);
		
		return ResponseEntity.ok(userVerificationResponseDTO);
	}
	
	
	@PostMapping(value = "/verify/otp")
	public  ResponseEntity<SendOTPResponseDTO> verifyOTP(@RequestHeader("Authorization") String token, @Valid @RequestBody VerifyOTPRequestDTO verifyOTPRequestDTO) {
		final boolean isVerified = _userService.processOTPVerification(verifyOTPRequestDTO);
		SendOTPResponseDTO userVerificationResponseDTO = new SendOTPResponseDTO(verify OTPRequestDTO.getTarget(), isVerified, true);
		userVerificationResponseDTO.setId(verifyOTPRequestDTO.getId());
		return ResponseEntity.ok(userVerificationResponseDTO);
	}*/

	@PostMapping("/add-user")
	public GenericResponse registerUser(@Valid @RequestHeader("Authorization") @RequestBody SignupRequestDTO signupRequestDTO) {

		List<Notification> notifications = new ArrayList<>();

      /*  if (_iUserService.isEmailAlreadyExist(signupRequestDTO.getEmailAddress())) {
            Notification notification = new Notification();
            notification.setNoificationCode("401");
            notification.setNotificationDescription("User's email address already exists");
            notifications.add(notification);
        }*/

     /*   if (_iUserService.isMobileNumberExist(signupRequestDTO.getIsdCode().concat(signupRequestDTO.getMobileNumber()))) {
            Notification notification = new Notification();
            notification.setNoificationCode("401");
            notification.setNotificationDescription("User's Phone Number already exists");
            notifications.add(notification);
        }*/

		if (!notifications.isEmpty()) {

			GenericResponse genericResponse = new GenericResponse();
			genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setNotifications(notifications);

			return genericResponse;
		}

		GenericDTO genericDTO = _userService.signup(signupRequestDTO);

		List<GenericDTO> genericDTOs = new ArrayList<>();
		genericDTOs.add(genericDTO);

		ModalDTO modalDTO = new ModalDTO();
		modalDTO.setData(genericDTOs);

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
		genericResponse.setModalDTO(modalDTO);

		return genericResponse;
	}
}
