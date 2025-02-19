package com.ms.vidhyalebox.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.parent.IParentRepo;
import com.ms.vidhyalebox.parent.ParentEntity;
import com.ms.vidhyalebox.parent.ParentMapperNormal;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.ParentSignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.InvalidItemException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentMapperNormal implements IMapperNormal {

	private ClassRepo classRepo;
	private IUserRepo userRepo;
	private IOrgClientRepo orgClientRepo;
	private IParentRepo parentRepo;
	private SessionRepo sessionRepo;
	private ParentMapperNormal parentMapper;
	private PasswordEncoder encode;
	private IUserService userService;

	@Autowired
	public StudentMapperNormal(IUserService userService, PasswordEncoder encode, ParentMapperNormal parentMapper,
			SessionRepo sessionRepo, IParentRepo parentRepo, IOrgClientRepo orgClientRepo, IUserRepo userRepo,
			ClassRepo classRepo) {
		this.sessionRepo = sessionRepo;
		this.parentRepo = parentRepo;
		this.orgClientRepo = orgClientRepo;
		this.userRepo = userRepo;
		this.classRepo = classRepo;
		this.parentMapper = parentMapper;
		this.encode = encode;
		this.userService = userService;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {

		StudentEntity entity = genericEntity == null ? new StudentEntity() : (StudentEntity) genericEntity;
		StudentDTO studentDTO = (StudentDTO) genericDto;

		if (userRepo.existsByIdentityProvider(studentDTO.getIdentity())) {
			throw new InvalidItemException("Please contact support, student identity already exists.");
		}
		ParentSignupRequestDTO parentSignupRequestDTO = null;
		ParentEntity parent = null;

		if (studentDTO.getParentSignupRequestDTO() != null) {
			parentSignupRequestDTO = studentDTO.getParentSignupRequestDTO();
			parent = (ParentEntity) parentMapper.dtoToEntity(parentSignupRequestDTO);
			parent = parentRepo.save(parent);
		}

		var user = genericEntity == null ?  new UserEntity() : entity.getUser();

		// Add null checks for partial updates
		if (studentDTO.getCurrentAddr() != null) {
			user.setAddress(studentDTO.getCurrentAddr());
		}
		if (studentDTO.getStudentEmail() != null) {
			user.setEmail(studentDTO.getStudentEmail());
		} else if (parentSignupRequestDTO.getParentEmail() != null) {
			user.setEmail(parentSignupRequestDTO.getParentEmail());
		}
		if (studentDTO.getFirstName() != null) {
			user.setFirstName(studentDTO.getFirstName());
		}
		if (studentDTO.getLastName() != null) {
			user.setLastName(studentDTO.getLastName());
		}
		if (studentDTO.getStudentMobile() != null) {
			user.setMobileNumber(studentDTO.getStudentMobile());
		}
		if (studentDTO.getStudentPWD() != null) {
			user.setPassword(encode.encode(studentDTO.getStudentPWD()));
		} else if (parentSignupRequestDTO.getParentMobile() != null) {
			user.setPassword(encode.encode(parentSignupRequestDTO.getParentMobile()));
		}
		if (studentDTO.getOrgUniqId() != null) {
			user.setSchool(orgClientRepo.findByOrgUniqId(studentDTO.getOrgUniqId())
					.orElseThrow(() -> new EntityNotFoundException("School not found")));
		}
		if(user.getRole() != null) {
			user.setRole("ROLE_STUDENT");
		}
		if(studentDTO.getIdentity() != null ) {
			user.setIdentityProvider(studentDTO.getIdentity());
		}

		if (studentDTO.getFile() != null) {
			user.setImage(userService.saveImage(studentDTO.getFile(), studentDTO.getOrgUniqId() + "_student"));
		}

		var userEntity = userRepo.save(user);

		if(studentDTO.getBloodgroup() != null) {
			entity.setBloodGroup(studentDTO.getBloodgroup());
		}
		
		if (studentDTO.getEmergencyContact() != null) {
			entity.setEmergencyContact(studentDTO.getEmergencyContact());
		}
		
		entity.setActive(studentDTO.isActive());

		// Null check for class
		if (studentDTO.getClassSection() != null) {
			entity.setClassEntity(classRepo.findById(Long.valueOf(studentDTO.getClassSection()))
					.orElseThrow(() -> new EntityNotFoundException("Class not found")));
		}

		// Setting parent and user association
		if(studentDTO.getParentSignupRequestDTO() != null ) {
			entity.setParentEntity(parent);
		}
		entity.setUser(userEntity);

		// Null check for session
		if (studentDTO.getSessionYear() != null) {
			entity.setSessionEntity(sessionRepo.findById(Long.valueOf(studentDTO.getSessionYear()))
					.orElseThrow(() -> new EntityNotFoundException("Session not found")));
		}

		if (studentDTO.getOrgUniqId() != null) {
			entity.setSchool(orgClientRepo.findByOrgUniqId(studentDTO.getOrgUniqId())
					.orElseThrow(() -> new EntityNotFoundException("School not found")));
		}

		// Null checks for dates
		if (studentDTO.getAdmissionDate() != null) {
			entity.setAdmissionDate(studentDTO.getAdmissionDate());
		}
		if (studentDTO.getPermanentAddr() != null) {
			entity.setPermanentAddress(studentDTO.getPermanentAddr());
		}

		// studentRepo.save(entity);

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
//        ShiftEntity entity = (ShiftEntity) genericEntity;
//
//        ShiftDTO shiftDTO = new ShiftDTO();
//        shiftDTO.setOrgUniqId(entity.getOrgUniqId());
//        shiftDTO.setShiftName(entity.getShiftName());
//        shiftDTO.setStartTime(entity.getStartTime());
//        shiftDTO.setEndTime(entity.getEndTime());
//        shiftDTO.setActive(entity.isActive());

		return null;
	}
}
