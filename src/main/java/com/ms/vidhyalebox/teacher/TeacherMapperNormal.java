package com.ms.vidhyalebox.teacher;
//
//import com.ms.shared.api.auth.TeacherSignupRequestDTO;
//import com.ms.shared.api.auth.TeacherSignupResponseDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.vidhyalebox.utility.VidhyaleBoxUtil;
//import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import org.springframework.stereotype.Service;
//
@Service
public class TeacherMapperNormal implements IMapperNormal {
	
	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		
		TeacherEntity entity = genericEntity == null ? new TeacherEntity() : (TeacherEntity)genericEntity;
		
		return entity;
		
	}
	
	@Override
	  public GenericDTO entityToDto(GenericEntity genericEntity) { 
		TeacherEntity entity = (TeacherEntity)genericEntity;
		
		return null;
	}
	
	
}
//
//	@Override
//	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
//
//		TeacherEntity entity = genericEntity == null ? new TeacherEntity() : (TeacherEntity) genericEntity;
//
//		TeacherSignupRequestDTO teacherSignupRequestDTO = (TeacherSignupRequestDTO) genericDto;
//
//		entity.setBirthDate(teacherSignupRequestDTO.getDateOfBirth());
//		entity.setEmail(teacherSignupRequestDTO.getEmailAddress());
//		entity.setFirstName(teacherSignupRequestDTO.getFirstname());
//		entity.setGender(teacherSignupRequestDTO.getGender());
//		entity.setLastName(teacherSignupRequestDTO.getLastname());
//		//entity.setMiddleName(signupRequestDTO.getMiddleName());
//		entity.setPassword(teacherSignupRequestDTO.getPassword() == null ? entity.getPassword() : teacherSignupRequestDTO.getPassword());
//		entity.setMobileNumber(teacherSignupRequestDTO.getIsdCode().concat( teacherSignupRequestDTO.getMobileNumber()));
//		//TODO - following 3 field set to True temporary
//		entity.setAccountNonLocked(true);
//		entity.setAccountNonExpired(true);
//		entity.setCredentialsNonExpired(true);
//		//entity.setActive(true);     //TODO - Temporary enabled but will be active after verify Phone Number - JIRA to be created
//		//entity.setEmailVerified(true); //TODO - Temporary enabled but will be true after email - JIRA to be created
//		//entity.setPhoneNumberVerified(true); //TODO - Temporary enabled but will be true after verify Phone Number - JIRA to be created
//
//		return entity;
//	}
////
//	@Override
//	public GenericDTO entityToDto(GenericEntity genericEntity) {
//
//		TeacherEntity teacherEntity = (TeacherEntity) genericEntity;
//
//		TeacherSignupResponseDTO teacherSignupResponseDTO = new TeacherSignupResponseDTO();
//
//		teacherSignupResponseDTO.setEmailAddress(teacherEntity.getEmail());
//		teacherSignupResponseDTO.setMobileNumber(teacherEntity.getMobileNumber());
//		teacherSignupResponseDTO.setFirstName(teacherEntity.getFirstName());
//		teacherSignupResponseDTO.setLastName(teacherEntity.getLastName());
//		teacherSignupResponseDTO.setGender(teacherEntity.getGender());
//		teacherSignupResponseDTO.setDateOfBirth(TILServiceUtil.convertDateToString(teacherEntity.getBirthDate(), VidhyaleBoxUtil.DATE_FORMAT));
//		teacherSignupResponseDTO.setEmailVerified(teacherEntity.isEmailVerified());
//		teacherSignupResponseDTO.setPhoneVerified(teacherEntity.isPhoneNumberVerified());
//		teacherSignupResponseDTO.setIdentityProvider(teacherEntity.getIdentityProvider());
//
//		return teacherSignupResponseDTO;
//	}
//
//	@Override
//	public GenericDTO entityToDtoForAdmin(GenericEntity genericEntity) {
//
//		TeacherEntity teacherEntity = (TeacherEntity) genericEntity;
//
//		TeacherSignupResponseDTO teacherSignupResponseDTO = new TeacherSignupResponseDTO();
//		teacherSignupResponseDTO.setId(teacherEntity.getId());
//		teacherSignupResponseDTO.setEmailAddress(teacherEntity.getEmail());
//		teacherSignupResponseDTO.setMobileNumber(teacherEntity.getMobileNumber());
//		teacherSignupResponseDTO.setFirstName(teacherEntity.getFirstName());
//		teacherSignupResponseDTO.setLastName(teacherEntity.getLastName());
//
//		return teacherSignupResponseDTO;
//	}
//
//	public GenericDTO mapUserDetailsDto(GenericEntity genericEntity) {
//		TeacherEntity teacherEntity = (TeacherEntity) genericEntity;
//		TeacherSignupResponseDTO teacherSignupResponseDTO = new TeacherSignupResponseDTO();
//		teacherSignupResponseDTO.setId(String.valueOf(teacherEntity.getId()));
//		if (teacherEntity.getBirthDate() != null) {
//			teacherSignupResponseDTO.setDateOfBirth(TILServiceUtil.convertDateToString(teacherEntity.getBirthDate(), VidhyaleBoxUtil.DATE_FORMAT));
//		}
//	//	teacherSignupResponseDTO.setId(TeacherEntity.getId());
//		teacherSignupResponseDTO.setEmailAddress(teacherEntity.getEmail());
//		teacherSignupResponseDTO.setFirstName(teacherEntity.getFirstName());
//		teacherSignupResponseDTO.setGender(teacherEntity.getGender());
//		teacherSignupResponseDTO.setLastName(teacherEntity.getLastName());
//		//userResponseDTO.setMiddleName(userEntity.getMiddleName());
//		teacherSignupResponseDTO.setMobileNumber(teacherEntity.getMobileNumber());
//
//		return teacherSignupResponseDTO;
//	}
////
////	public GenericEntity federatedDtoToEntity(FederatedUserDTO federatedUserDTO, IdentityProvider identityProvider) {
////
////		UserEntity userEntity = new UserEntity();
////
////		FederatedUserInfo googleUserInfo = (FederatedUserInfo) federatedUserDTO;
////		userEntity.setEmailAddress(googleUserInfo.getEmail());
////		userEntity.setFirstName(googleUserInfo.getFirstName());
////		userEntity.setLastName(googleUserInfo.getLastName());
////		userEntity.setPassword(UUID.randomUUID().toString());
////		//entity.setPhoneNumber(googleUserInfo.getPhoneNumber()); //TODO - need to get it from Google
////		userEntity.setAccountNonLocked(true);
////		userEntity.setAccountNonExpired(true);
////		userEntity.setCredentialsNonExpired(true);
////		userEntity.setActive(googleUserInfo.isVerifiedEmail());
////		userEntity.setEmailVerified(googleUserInfo.isVerifiedEmail());
////		userEntity.setFederatedUserId(googleUserInfo.getId());
////		userEntity.setIdentityProvider(identityProvider.toString());
////		//entity.setPhoneNumberVerified(true); //TODO - need to get it from Google
////
////		UserProfileEntity userProfileEntity = new UserProfileEntity();
////		userProfileEntity.setImageLink(googleUserInfo.getPicture());
////		userProfileEntity.setLanguage(googleUserInfo.getLocale());
////		userEntity.setUserProfile(userProfileEntity);
////
////		return userEntity;
////	}
//}
