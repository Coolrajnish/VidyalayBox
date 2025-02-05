package com.ms.vidhyalebox.student;

import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.auth.studentDTO.StudentDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.parent.IParentRepo;
import com.ms.vidhyalebox.parent.ParentEntity;
import com.ms.vidhyalebox.parent.ParentMapperNormal;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public StudentMapperNormal (IUserService userService,PasswordEncoder encode, ParentMapperNormal parentMapper,SessionRepo sessionRepo,IParentRepo parentRepo,IOrgClientRepo orgClientRepo,IUserRepo userRepo,ClassRepo classRepo){
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

//        StudentEntity entity = genericEntity == null ? new StudentEntity() : (StudentEntity) genericEntity;
//        StudentDTO studentDTO = (StudentDTO) genericDto;
//        boolean userval = userRepo.existsByIdentityProvider(studentDTO.getIdentity());
//        if(userval){
//           throw new EntityNotFoundException("Please contact support");
//        }
//        ParentSignupRequestDTO parentSignupRequestDTO = studentDTO.getParentSignupRequestDTO();
//        ParentEntity parent = (ParentEntity) parentMapper.dtoToEntity(parentSignupRequestDTO);
//        parent =  parentRepo.save(parent);
//        UserEntity user = new UserEntity();
//        user.setAddress(studentDTO.getCurrentAddr());
//        user.setEmail(studentDTO.getStudentEmail() != null ? studentDTO.getStudentEmail() : studentDTO.getParentSignupRequestDTO().getParentEmail());
//        user.setFirstName(studentDTO.getFirstName());
//        user.setLastName(studentDTO.getLastName());
//        user.setMobileNumber(studentDTO.getStudentMobile());
//        user.setPassword(studentDTO.getStudentPWD() != null ?encode.encode( studentDTO.getStudentPWD() ): encode.encode( studentDTO.getParentSignupRequestDTO().getParentMobile()));
//        user.setSchool(orgClientRepo.findByOrgUniqId(studentDTO.getOrgUniqId()).get());
//        user.setRole("ROLE_STUDENT");
//        user.setIdentityProvider(studentDTO.getIdentity());
//       // user.setImage(userService.saveImage(studentDTO.getStudentImage(), studentDTO.getOrgUniqId() +"_"+studentDTO.getIdentity()));
//
//        UserEntity userEntity = userRepo.save(user);
//        entity.setBloodGroup(studentDTO.getBloodgroup());
//        entity.setEmergencyContact(studentDTO.getEmergencyContact());
//        entity.setActive(studentDTO.isActive());
//        entity.setClassEntity(classRepo.findById(Long.valueOf(studentDTO.getClassSection())).get());
//        entity.setParentEntity(parent);
//        entity.setUser(userEntity);
//        entity.setSessionEntity(sessionRepo.findById(Long.valueOf(studentDTO.getSessionYear())).get());
//        entity.setSchool(orgClientRepo.findByOrgUniqId(studentDTO.getOrgUniqId()).get());
//        entity.setAdmissionDate(studentDTO.getAdmissionDate());
//        entity.setPermanentAddress(studentDTO.getPermanentAddr());
//        return entity;
        return null;
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
