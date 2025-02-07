package com.ms.vidhyalebox.user;


import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.sharedapi.SignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface IUserService extends IGenericService<GenericEntity, Long> {

    //boolean isEmailAlreadyExist(final String emailAddress);
    public APiResponse<Object> resetPassword(String token, String newPassword);
    public GenericDTO signup(@Valid SignupRequestDTO SignupRequestDTO);
    public boolean isMobileNumberExist(final String MobileNumber);
    public boolean verifyUser(String token);
    public ResponseEntity<APiResponse<Object>> initiatePWDReset(String email);
    public String saveImage(MultipartFile file, String foldername);
    public Resource getImage(Long userId);
//	public boolean logout(LoginRequestDTO userDTO);
//
//	public GenericDTO add(SignupRequestDTO request);
//
//	public GenericDTO getUserById(final Long id);
//
////	boolean isEmailAlreadyExist(final String emailAddress);
//
//	GenericDTO findByEmailAddress(String input);
//
//	boolean isPhoneNumberExist(final String phoneNumber);
//
//	Long processSendOtpRequest(final Long userId, final String target, final String type, final String purpose);
//
//	public boolean changePassword(final Long id, ChangePasswordRequestDTO userPasswordResetDTO);
//
//	void invalidateUsersAllSession(final Long userId);
//
//	void invalidateSession(final String emailAddress, final String token);
//	public Long verifyUserAndSendOtp(String phoneNumber, String emailAddress);

//	@Transactional()
//	GenericDTO processUserInfoProvidedByFederatedIdentities(@Valid FederatedUserInfo federatedUserInfo, IdentityProvider identityProvider,
//															@Valid @NotNull DeviceInfoDTO deviceInfo, String jwtToken, Long expiryDuration,
//															String referralCode);

//	public void resetPassword(ResetPasswordDTO resetPasswordDTO);
}
