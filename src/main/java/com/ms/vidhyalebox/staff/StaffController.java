package com.ms.vidhyalebox.staff;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/staff")
public class StaffController extends GenericController<StaffDTO, Long> {

	private final StaffServiceImpl _iStaffService;

	public StaffController(final StaffServiceImpl staffService) {
		_iStaffService = staffService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		_iStaffService.setAuthToken(getAuthToken());
		return _iStaffService;
	}

	@PostMapping(path = "/addstaff", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<APiResponse<Object>> addStaff(@RequestPart("staffDTO") StaffDTO staffDto,
			@RequestParam("image") MultipartFile image) {

		try {
			_iStaffService.addStaff(staffDto, image);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APiResponse<>("error", "Staff regestration failed - " + e.getLocalizedMessage(), Map
							.of("staff FirstName", staffDto.getFirstName(), "staff LastName", staffDto.getLastName()),
							null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Staff registered successfully",
				Map.of("staff FirstName", staffDto.getFirstName(), "staff LastName", staffDto.getLastName()), null));
	}
	
	   @GetMapping("/pagination")
	    public ResponseEntity<APiResponse<List<StaffEntity>>> filterStaff(
	            @RequestParam String orgId,
	            @RequestParam(defaultValue = "") String searchText,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(defaultValue = "staff_name") String sortBy,
	            @RequestParam(defaultValue = "asc") String sortOrder
	    ) {

	        Page<StaffEntity> val  =   _iStaffService.search(orgId, searchText, page, size, sortBy, sortOrder);
	        return ResponseEntity.ok(
	                new APiResponse<>(
	                        "success" ,
	                        "Data fetched successfully" ,
	                        _iStaffService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
	                        Map.of(
	                                "currentPage", val.getNumber(),
	                                "totalPages", val.getTotalPages(),
	                                "totalItems", val.getTotalElements()
	                        )));
	    }

	/*
	 * @PostMapping("/logout") public ResponseEntity<Void> logout() {
	 * _userService.logout(); return ResponseEntity.ok().build(); }
	 */

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
//
//
//
//	@PutMapping("/password")
//	public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal currentUser, @RequestBody ChangePasswordRequestDTO changePasswordRequest) {
//
//		Reset the password
//		boolean isPasswordUpdated = _userService.changePassword(currentUser.getId(), changePasswordRequest);
//
//		if (isPasswordUpdated) {
//			_userService.invalidateUsersAllSession(currentUser.getId()); invalidate session
//		}
//
//		return ResponseEntity.ok(new ChangePasswordResponseDTO(isPasswordUpdated));
//	}

	/*
	 * @GetMapping("/detail/{id}") TODO public GenericResponse
	 * getUserById(@RequestHeader("Authorization") String token, @PathVariable Long
	 * id) { GenericDTO genericDTO = _userService.getUserById(id); List<GenericDTO>
	 * genericDTOs = new ArrayList<>(); genericDTOs.add(genericDTO); return
	 * getResponse(genericDTOs); }
	 * 
	 * @PostMapping(value = "/send/otp") public ResponseEntity<SendOTPResponseDTO>
	 * sendVerificationOTP(@RequestHeader("Authorization") String
	 * token, @Valid @RequestBody SendOTPRequestDTO userVerificationRequest) {
	 * 
	 * UserPrincipal currentUser = (UserPrincipal)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); final
	 * Long verificationRequestId =
	 * _userService.processSendOtpRequest(currentUser.getId(),
	 * userVerificationRequest.getTarget(), userVerificationRequest.getType(),
	 * userVerificationRequest.getPurpose()); boolean isVerificationRequestProcessed
	 * = Objects.nonNull(verificationRequestId);
	 * 
	 * SendOTPResponseDTO userVerificationResponseDTO = new
	 * SendOTPResponseDTO(userVerificationRequest.getTarget(), false,
	 * isVerificationRequestProcessed);
	 * userVerificationResponseDTO.setId(verificationRequestId);
	 * 
	 * return ResponseEntity.ok(userVerificationResponseDTO); }
	 * 
	 * 
	 * @PostMapping(value = "/verify/otp") public ResponseEntity<SendOTPResponseDTO>
	 * verifyOTP(@RequestHeader("Authorization") String token, @Valid @RequestBody
	 * VerifyOTPRequestDTO verifyOTPRequestDTO) { final boolean isVerified =
	 * _userService.processOTPVerification(verifyOTPRequestDTO); SendOTPResponseDTO
	 * userVerificationResponseDTO = new
	 * SendOTPResponseDTO(verifyOTPRequestDTO.getTarget(), isVerified, true);
	 * userVerificationResponseDTO.setId(verifyOTPRequestDTO.getId()); return
	 * ResponseEntity.ok(userVerificationResponseDTO); }
	 */

}
