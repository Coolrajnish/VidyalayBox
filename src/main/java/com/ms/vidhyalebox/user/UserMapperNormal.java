package com.ms.vidhyalebox.user;

import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.sharedapi.SignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.SignupResponseDTO;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.TILServiceUtil;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.utility.VidhyaleBoxUtil;

@Service
public class UserMapperNormal implements IMapperNormal {

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {

		UserEntity entity = genericEntity == null ? new UserEntity() : (UserEntity) genericEntity;

		SignupRequestDTO signupRequestDTO = (SignupRequestDTO) genericDto;
		entity.setBirthDate(signupRequestDTO.getDateOfBirth());
		//entity.setEmail(signupRequestDTO.);
		entity.setFirstName(signupRequestDTO.getFirstName());
		entity.setGender(signupRequestDTO.getGender());
		entity.setLastName(signupRequestDTO.getLastName());
		//entity.setMiddleName(signupRequestDTO.getMiddleName());
		entity.setPassword(signupRequestDTO.getPassword() == null ? entity.getPassword() : signupRequestDTO.getPassword());
		//entity.setMobileNumber(signupRequestDTO.getIsdCode().concat( signupRequestDTO.getMobileNumber()));
		//TODO - following 3 field set to True temporary
		entity.setAccountNonLocked(true);
		entity.setAccountNonExpired(true);
		entity.setCredentialsNonExpired(true);
		//entity.set(signupRequestDTO.getsch);
		entity.setActive(true);     //TODO - Temporary enabled but will be active after verify Phone Number - JIRA to be created
		//entity.setEmailVerified(true); //TODO - Temporary enabled but will be true after email - JIRA to be created
		//entity.setPhoneNumberVerified(true); //TODO - Temporary enabled but will be true after verify Phone Number - JIRA to be created

		return entity;
	}
//
	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {

		UserEntity userEntity = (UserEntity) genericEntity;

		SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
		//signupResponseDTO.setEmail(userEntity.getEmailAddress());
		//signupResponseDTO.setMobileNumber(userEntity.getMobileNumber());
		signupResponseDTO.setFirstName(userEntity.getFirstName());
		signupResponseDTO.setLastName(userEntity.getLastName());
		signupResponseDTO.setGender(userEntity.getGender());
		signupResponseDTO.setDateOfBirth(TILServiceUtil.convertDateToString(userEntity.getBirthDate(), VidhyaleBoxUtil.DATE_FORMAT));
		//signupResponseDTO.setEmailVerified(userEntity.isEmailVerified());
		signupResponseDTO.setAdmissionId(userEntity.getIdentityProvider());
		signupResponseDTO.setId(userEntity.getId());

		return signupResponseDTO;
	}

	@Override
	public GenericDTO entityToDtoForAdmin(GenericEntity genericEntity) {

		UserEntity userEntity = (UserEntity) genericEntity;

		SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
		signupResponseDTO.setId(userEntity.getId());
	//	signupResponseDTO.setEmail(userEntity.getEmailAddress());
	//	signupResponseDTO.setMobileNumber(userEntity.getMobileNumber());
		signupResponseDTO.setFirstName(userEntity.getFirstName());
		signupResponseDTO.setLastName(userEntity.getLastName());

		return signupResponseDTO;
	}

	public GenericDTO mapUserDetailsDto(GenericEntity genericEntity) {
		UserEntity userEntity = (UserEntity) genericEntity;
		SignupResponseDTO userResponseDTO = new SignupResponseDTO();
		userResponseDTO.setId(String.valueOf(userEntity.getId()));
		if (userEntity.getBirthDate() != null) {
			userResponseDTO.setDateOfBirth(TILServiceUtil.convertDateToString(userEntity.getBirthDate(), VidhyaleBoxUtil.DATE_FORMAT));

		}
		userResponseDTO.setId(userEntity.getId());
	//	userResponseDTO.setEmail(userEntity.getEmailAddress());
		userResponseDTO.setFirstName(userEntity.getFirstName());
		userResponseDTO.setGender(userEntity.getGender());
		userResponseDTO.setLastName(userEntity.getLastName());
		//userResponseDTO.setMiddleName(userEntity.getMiddleName());
	//	userResponseDTO.setMobileNumber(userEntity.getMobileNumber());

		return userResponseDTO;
	}
//
//	public GenericEntity federatedDtoToEntity(FederatedUserDTO federatedUserDTO, IdentityProvider identityProvider) {
//
//		UserEntity userEntity = new UserEntity();
//
//		FederatedUserInfo googleUserInfo = (FederatedUserInfo) federatedUserDTO;
//		userEntity.setEmailAddress(googleUserInfo.getEmail());
//		userEntity.setFirstName(googleUserInfo.getFirstName());
//		userEntity.setLastName(googleUserInfo.getLastName());
//		userEntity.setPassword(UUID.randomUUID().toString());
//		//entity.setPhoneNumber(googleUserInfo.getPhoneNumber()); //TODO - need to get it from Google
//		userEntity.setAccountNonLocked(true);
//		userEntity.setAccountNonExpired(true);
//		userEntity.setCredentialsNonExpired(true);
//		userEntity.setActive(googleUserInfo.isVerifiedEmail());
//		userEntity.setEmailVerified(googleUserInfo.isVerifiedEmail());
//		userEntity.setFederatedUserId(googleUserInfo.getId());
//		userEntity.setIdentityProvider(identityProvider.toString());
//		//entity.setPhoneNumberVerified(true); //TODO - need to get it from Google
//
//		UserProfileEntity userProfileEntity = new UserProfileEntity();
//		userProfileEntity.setImageLink(googleUserInfo.getPicture());
//		userProfileEntity.setLanguage(googleUserInfo.getLocale());
//		userEntity.setUserProfile(userProfileEntity);
//
//		return userEntity;
//	}
}
