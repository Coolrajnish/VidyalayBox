package com.ms.vidhyalebox.teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.leavesettings.LeaveSettingsEntity;
import com.ms.vidhyalebox.leavesettings.LeaveSettingsRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollRepo;
import com.ms.vidhyalebox.salary.SalaryEntity;
import com.ms.vidhyalebox.salary.SalaryRepo;
//
//import com.ms.shared.api.auth.TeacherSignupRequestDTO;
//import com.ms.shared.api.auth.TeacherSignupResponseDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.vidhyalebox.utility.VidhyaleBoxUtil;
//import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.EntityNotFoundException;

//
@Service
public class TeacherMapperNormal implements IMapperNormal {

	private static final Logger logger = LoggerFactory.getLogger(TeacherMapperNormal.class);
	@Autowired
	ITeacherRepo _iTeacherRepo;
	@Autowired
	LeaveSettingsRepo leave;
	@Autowired
	IOrgClientRepo orgClientRepo;
	@Autowired
	IUserRepo userRepo;
	@Autowired
	IUserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	PayrollRepo payrollRepo;
	@Autowired
	SalaryRepo salaryrepo;

	private static MultipartFile image;

	public static MultipartFile getImage() {
		return image;
	}

	public static void setImage(MultipartFile image) {
		TeacherMapperNormal.image = image;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {

		TeacherEntity entity = null;
		try {
			entity = genericEntity == null ? new TeacherEntity() : (TeacherEntity) genericEntity;
			TeacherDTO teacherDTO = (TeacherDTO) genericDto;
			var salary = genericEntity == null ? new SalaryEntity() : entity.getSalary();

			// Null check for salary
			if (teacherDTO.getSalary() != null) {
				salary.setBasicSalary(new BigDecimal(teacherDTO.getSalary()));
			}

			if (entity.getSalary().getPaymentDate() != null) {
				salary.setPaymentDate(LocalDate.now().plusDays(30).toString());
			}
			if (teacherDTO.getStatus() != null) {
				salary.setPaymentStatus(teacherDTO.getStatus());
			}

			// Null check for school
			if (teacherDTO.getSchool() != null) {
				salary.setSchool(orgClientRepo.findByOrgUniqId(teacherDTO.getSchool())
						.orElseThrow(() -> new EntityNotFoundException("School not found")));
			}

			List<PayrollEntity> payrolls = entity.getSalary().getPayrolls() != null ? entity.getSalary().getPayrolls()
					: new ArrayList<>();
			if (teacherDTO.getPayroll() != null) {
				for (Long id : teacherDTO.getPayroll()) {
					PayrollEntity payroll = payrollRepo.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("Payroll not found for id " + id));
					payrolls.add(payroll);
				}
			}
			if (!payrolls.isEmpty()) {
				salary.setPayrolls(payrolls);
			}

			long total = (teacherDTO.getSalary() != null) ? Long.valueOf(teacherDTO.getSalary()) : 0;
			if (total != 0) {
				for (PayrollEntity pay : payrolls) {
					long amount = 0;
					if (pay.getAmount() != null && !pay.getAmount().isEmpty()) {
						amount = Long.parseLong(pay.getAmount());
					} else if (pay.getPercentage() != null && teacherDTO.getSalary() != null) {
						amount = (Long.parseLong(pay.getPercentage()) * Long.parseLong(teacherDTO.getSalary())) / 100;
					}

					if ("ALLOWANCE".equals(pay.getPayrollType())) {
						total += amount;
					} else {
						total -= amount;
					}
				}
				salary.setNetSalary(BigDecimal.valueOf(total));
			}

			// User details
			var user = genericEntity == null ? new UserEntity() : entity.getUser();
			if (teacherDTO.getEmail() != null) {
				user.setEmail(teacherDTO.getEmail());
			}

			if (teacherDTO.getFirstName() != null) {
				user.setFirstName(teacherDTO.getFirstName());
			}

			if (teacherDTO.getLastName() != null) {
				user.setLastName(teacherDTO.getLastName());
			}

			if (teacherDTO.getPhoneNo() != null) {
				user.setMobileNumber(teacherDTO.getPhoneNo());
				user.setPassword(passwordEncoder.encode(teacherDTO.getPhoneNo())); // Assuming phone number is used as
																					// the
																					// password
			}

			// Null check for school and identity provider
			if (teacherDTO.getSchool() != null) {
				user.setSchool(orgClientRepo.findByOrgUniqId(teacherDTO.getSchool())
						.orElseThrow(() -> new EntityNotFoundException("School not found")));
			}

			if (teacherDTO.getIdentity() != null) {
				user.setIdentityProvider(teacherDTO.getIdentity());
			}

			// Image saving logic
			if (image != null) {
				user.setImage(userService.saveImage(image, entity.getSchool().getOrgUniqId() + "_teacher"));
			}
			UserEntity userEntity = user;
			if (!user.equals(entity.getUser())) {
				userEntity = userRepo.save(user);
			}

			salary.setUser(userEntity);

			entity.setUser(userEntity);

			// Null check for school (already checked above)
			OrgClientEntity school = orgClientRepo.findByOrgUniqId(teacherDTO.getSchool())
					.orElseThrow(() -> new EntityNotFoundException("School not found"));
			entity.setSchool(school);

			LeaveSettingsEntity leavesettings = genericEntity != null ? entity.getLeavesettings() 
					:  leave.getLeaveSettings(String.valueOf(school.getId())).get();
			logger.info(salary+"----Leave ----->>>>>"+leavesettings);
			if (salary != null) {
				salary = salaryrepo.save(salary);
			}

			if (leavesettings != null) {
				entity.setLeavesettings(leavesettings);
			}

			if (salary != null) {
				entity.setSalary(salary);
			}

			if (teacherDTO.getQualification() != null) {
				entity.setQualification(teacherDTO.getQualification());
			}

			if (teacherDTO.getCurrentAddr() != null) {
				entity.setCurrentAddr(teacherDTO.getCurrentAddr());
			}

			if (teacherDTO.getPermanentAddr() != null) {
				entity.setPermanentAddr(teacherDTO.getPermanentAddr());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;

	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		TeacherEntity entity = (TeacherEntity) genericEntity;
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(entity.getId());
		teacherDTO.setFirstName(entity.getUser().getFirstName());
		return teacherDTO;
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
