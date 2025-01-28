//package com.ms.vidhyalebox.teacher;
//
//
//import com.ms.shared.api.auth.StaffSignupRequestDTO;
//import com.ms.shared.api.auth.TeacherSignupRequestDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.shared.util.util.bl.IGenericService;
//import com.ms.shared.util.util.domain.GenericEntity;
//
//public interface ITeacherService extends IGenericService<GenericEntity, Long> {
//    boolean isEmailAlreadyExist(final String emailAddress);
//    public GenericDTO signup(TeacherSignupRequestDTO teacherSignupRequestDTO);
//    public boolean isMobileNumberExist(final String MobileNumber);
//
////	public boolean logout(LoginRequestDTO userDTO);
////
////	public GenericDTO add(SignupRequestDTO request);
////
////	public GenericDTO getUserById(final Long id);
////
//////	boolean isEmailAlreadyExist(final String emailAddress);
////
////	GenericDTO findByEmailAddress(String input);
////
////	boolean isPhoneNumberExist(final String phoneNumber);
////
////	Long processSendOtpRequest(final Long userId, final String target, final String type, final String purpose);
////
////	public boolean changePassword(final Long id, ChangePasswordRequestDTO userPasswordResetDTO);
////
////	void invalidateUsersAllSession(final Long userId);
////
////	void invalidateSession(final String emailAddress, final String token);
////	public Long verifyUserAndSendOtp(String phoneNumber, String emailAddress);
//
////	@Transactional()
////	GenericDTO processUserInfoProvidedByFederatedIdentities(@Valid FederatedUserInfo federatedUserInfo, IdentityProvider identityProvider,
////															@Valid @NotNull DeviceInfoDTO deviceInfo, String jwtToken, Long expiryDuration,
////															String referralCode);
//
////	public void resetPassword(ResetPasswordDTO resetPasswordDTO);
//}
