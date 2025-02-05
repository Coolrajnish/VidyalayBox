package com.ms.vidhyalebox.student;

import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.auth.studentDTO.StudentDTO;
import com.ms.shared.api.auth.studentDTO.StudentTransferDTO;
import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.repo.GenericRepo;
import com.ms.shared.util.util.rest.InvalidItemException;
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentServiceImpl extends GenericService<GenericEntity, Long> implements StudentService {

    private StudentMapperNormal studentMapperNormal;
    private StudentRepo studentRepo;
    private IUserRepo userRepo;
    private ClassRepo classRepo;
    private IOrgClientRepo orgClientRepo;
    private IParentRepo parentRepo;
    private SessionRepo sessionRepo;
    private ParentMapperNormal parentMapper;
    private PasswordEncoder encode;
    private IUserService userService;


    @Autowired
    public StudentServiceImpl (StudentRepo studentRepo,StudentMapperNormal studentMapperNormal,IUserService userService,PasswordEncoder encode, ParentMapperNormal parentMapper,SessionRepo sessionRepo,IParentRepo parentRepo,IOrgClientRepo orgClientRepo,IUserRepo userRepo,ClassRepo classRepo){
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
        this.studentMapperNormal = studentMapperNormal;
        this.parentRepo = parentRepo;
        this.orgClientRepo = orgClientRepo;
        this.userRepo = userRepo;
        this.classRepo = classRepo;
        this.parentMapper = parentMapper;
        this.encode = encode;
        this.userService = userService;
    }

    @Override
    public JpaRepository getRepo() {
        return studentRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return studentMapperNormal;
    }

    @Transactional
    @Override
    public boolean transferStudent(List<StudentTransferDTO> studentTransferDTO) {
        boolean bool = false;
        for(StudentTransferDTO stu : studentTransferDTO){
            studentRepo.transferStudent(stu.getOrgId(), stu.getStuId(), stu.getClassId());
        }
        return bool;
    }

    @Transactional
    @Override
    public String addStudent(StudentDTO studentDTO, MultipartFile image) {

        StudentEntity entity =  new StudentEntity() ;
        boolean userval = userRepo.existsByIdentityProvider(studentDTO.getIdentity());
        if(userval){
            throw new InvalidItemException("Please contact support student identity already exists");
        }
        ParentSignupRequestDTO parentSignupRequestDTO = studentDTO.getParentSignupRequestDTO();
        ParentEntity parent = (ParentEntity) parentMapper.dtoToEntity(parentSignupRequestDTO);
        parent =  parentRepo.save(parent);
        UserEntity user = new UserEntity();
        user.setAddress(studentDTO.getCurrentAddr());
        user.setEmail(studentDTO.getStudentEmail() != null ? studentDTO.getStudentEmail() : studentDTO.getParentSignupRequestDTO().getParentEmail());
        user.setFirstName(studentDTO.getFirstName());
        user.setLastName(studentDTO.getLastName());
        user.setMobileNumber(studentDTO.getStudentMobile());
        user.setPassword(studentDTO.getStudentPWD() != null ?encode.encode( studentDTO.getStudentPWD() ): encode.encode( studentDTO.getParentSignupRequestDTO().getParentMobile()));
        user.setSchool(orgClientRepo.findByOrgUniqId(studentDTO.getOrgUniqId()).get());
        user.setRole("ROLE_STUDENT");
        user.setIdentityProvider(studentDTO.getIdentity());
        user.setImage(userService.saveImage(image, studentDTO.getOrgUniqId() +"_student"));

        UserEntity userEntity = userRepo.save(user);
        entity.setBloodGroup(studentDTO.getBloodgroup());
        entity.setEmergencyContact(studentDTO.getEmergencyContact());
        entity.setActive(studentDTO.isActive());
        entity.setClassEntity(classRepo.findById(Long.valueOf(studentDTO.getClassSection())).get());
        entity.setParentEntity(parent);
        entity.setUser(userEntity);
        entity.setSessionEntity(sessionRepo.findById(Long.valueOf(studentDTO.getSessionYear())).get());
        entity.setSchool(orgClientRepo.findByOrgUniqId(studentDTO.getOrgUniqId()).get());
        entity.setAdmissionDate(studentDTO.getAdmissionDate());
        entity.setPermanentAddress(studentDTO.getPermanentAddr());
        studentRepo.save(entity);
        return "Student added";
    }


}
