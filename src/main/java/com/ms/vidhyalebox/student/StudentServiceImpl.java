package com.ms.vidhyalebox.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.parent.IParentRepo;
import com.ms.vidhyalebox.parent.ParentEntity;
import com.ms.vidhyalebox.parent.ParentMapperNormal;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.ParentSignupRequestDTO;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.teacher.TeacherMapperNormal;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.InvalidItemException;

import jakarta.persistence.EntityNotFoundException;

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
	public StudentServiceImpl(StudentRepo studentRepo, StudentMapperNormal studentMapperNormal,
			IUserService userService, PasswordEncoder encode, ParentMapperNormal parentMapper, SessionRepo sessionRepo,
			IParentRepo parentRepo, IOrgClientRepo orgClientRepo, IUserRepo userRepo, ClassRepo classRepo) {
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

		boolean bool = studentTransferDTO.stream()
				.peek(stu -> studentRepo.transferStudent(stu.getOrgId(), stu.getStuId(), stu.getClassId())).count() > 0;

		return bool;

	}

	@Transactional
	@Override
	public StudentEntity save(StudentDTO studentDTO) {
		StudentEntity entity = null;
		try {
			// entity = studentRepo.findById((Long) studentDTO.getId()).get();
			entity = (StudentEntity) studentMapperNormal.dtoToEntity(studentDTO, null);
			studentRepo.save(entity);
		} catch (Exception e) {
			e.getStackTrace();
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}

		return entity;
	}

	@Transactional
	@Override
	public StudentEntity modify(StudentDTO sDTO) {
		StudentEntity entity = null ;
		try {
			 entity = studentRepo.findById((Long) sDTO.getId()).get();
			entity = (StudentEntity) studentMapperNormal.dtoToEntity(sDTO, entity);
			studentRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getStackTrace();
		//	logger.error("error -->", e.getStackTrace());
		}

		return entity;
	} 
	@Transactional
	@Override
	public Page<StudentEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		// TODO Auto-generated method stub
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return studentRepo.search(orgId, searchText, pageable);
		} else {
			return studentRepo.findAll(pageable);
		}
	}

}
