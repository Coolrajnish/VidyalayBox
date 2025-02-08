package com.ms.vidhyalebox.staff;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface IStaffService extends IGenericService<GenericEntity, Long> {
	public String addStaff(StaffDTO staffDTO, MultipartFile image);
	
	 public Page<StaffEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);

//
//	public boolean logout(LoginRequestDTO userDTO);
//
//	public GenericDTO add(SignupRequestDTO request);
//
//	public GenericDTO getUserById(final Long id);
//
//	boolean isEmailAlreadyExist(final String emailAddress);
//
////	public GenericDTO signup(StaffSignupRequestDTO staffSignupRequestDTO);
//
//	public boolean isMobileNumberExist(final String MobileNumber);
//
//	GenericDTO findByEmailAddress(String input);
//
//	boolean isPhoneNumberExist(final String phoneNumber);
//
//	Long processSendOtpRequest(final Long userId, final String target, final String type, final String purpose);
//
//	void invalidateUsersAllSession(final Long userId);
//
//	void invalidateSession(final String emailAddress, final String token);
//
//	public Long verifyUserAndSendOtp(String phoneNumber, String emailAddress);
//
//
//	public void resetPassword(ResetPasswordDTO resetPasswordDTO);
}
