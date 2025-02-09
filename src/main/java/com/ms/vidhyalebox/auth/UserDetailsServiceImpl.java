package com.ms.vidhyalebox.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.UserEntity;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final IUserRepo _userRepo; // Repository for UserEntity
//	private final IOrgClientRepo _IOrgClientRepo; // Repository for OrgClientEntity
//	private final IStaffRepo _iStaffRepo;
//	private final ITeacherRepo _ITeacherRepo;
//	private final IParentRepo _IParentRepo;

	@Autowired
	public UserDetailsServiceImpl(final IUserRepo userRepo ){//, IOrgClientRepo iOrgClientRepo, IStaffRepo iStaffRepo, ITeacherRepo iTeacherRepo, IParentRepo iParentRepo) {
		_userRepo = userRepo;
//		_IOrgClientRepo = iOrgClientRepo;
//		_iStaffRepo = iStaffRepo;
//		_ITeacherRepo = iTeacherRepo;
//		_IParentRepo = iParentRepo;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) {
		// First, check OrgClientEntity
//		Optional<OrgClientEntity> orgClientEntityOptional = _IOrgClientRepo.findByEmailAddress(username);
//		if (orgClientEntityOptional.isPresent()) {
//			return UserPrincipal.build(orgClientEntityOptional.get());
//		}
//
//		Optional<StaffEntity> optionalStaffEntity;
//		if (username.contains("@")) {
//			// If it's an email, search by email and null for the mobile number
//			optionalStaffEntity = _iStaffRepo.findByEmailOrMobileNumber(username, null);
//		} else {
//			// Otherwise, treat it as a mobile number and search by null for the email
//			optionalStaffEntity = _iStaffRepo.findByEmailOrMobileNumber(null, username);
//		}		if (optionalStaffEntity.isPresent()) {
//			return UserPrincipal.build(optionalStaffEntity.get());
//		}
//	//	Optional<TeacherEntity> teacherEntity = _ITeacherRepo.findByEmailOrMobileNumber(username);
//		Optional<TeacherEntity> optionalTeacherEntity;
//		if (username.contains("@")) {
//			// If it's an email, search by email and null for the mobile number
//			optionalTeacherEntity = _ITeacherRepo.findByEmailOrMobileNumber(username, null);
//		} else {
//			// Otherwise, treat it as a mobile number and search by null for the email
//			optionalTeacherEntity = _ITeacherRepo.findByEmailOrMobileNumber(null, username);
//		}
//		if (optionalTeacherEntity.isPresent()) {
//			return UserPrincipal.build(optionalTeacherEntity.get());
//		}
//
//			Optional<ParentEntity> optionalParentEntity;
//				if (username.contains("@")) {
//					// If it's an email, search by email and null for the mobile number
//					optionalParentEntity = _IParentRepo.findByEmailOrMobileNumber(username, null);
//				} else {
//					// Otherwise, treat it as a mobile number and search by null for the email
//					optionalParentEntity = _IParentRepo.findByEmailOrMobileNumber(null, username);
//				}
//				if (optionalParentEntity.isPresent()) {
//					return UserPrincipal.build(optionalParentEntity.get());
//				}

	//	String mobileNumber = getLastTenDigits(username);
		Optional<UserEntity> userEntity ;
		if (username.contains("@")) {
			// If it's an email, search by email and null for the mobile number
			userEntity = _userRepo.findByEmailOrMobileNumber(username, null);
		} else {
			// Otherwise, treat it as a mobile number and search by null for the email
			userEntity = _userRepo.findByEmailOrMobileNumber(null, username);
		}
		if (userEntity.isPresent()) {
			return UserPrincipal.build(userEntity.get());
		}
	//	UserEntity userEntity = _userRepo.findByIdentityProvider(username);
		// If found, return UserPrincipal
//		if (Objects.nonNull(userEntity)) {
//			return UserPrincipal.build(userEntity);
//		}

		throw new UsernameNotFoundException("User not found with username: " + username);
	}


	// Utility method to extract the last ten digits from a phone number
	public String getLastTenDigits(String phoneNumber) {
		String digitsOnlyPhoneNumber = phoneNumber.replaceAll("\\D", "");
		return digitsOnlyPhoneNumber.length() > 10 ? digitsOnlyPhoneNumber.substring(digitsOnlyPhoneNumber.length() - 10) : digitsOnlyPhoneNumber;
	}
}
