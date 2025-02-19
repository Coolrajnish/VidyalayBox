package com.ms.vidhyalebox.teacher;

//
//import com.ms.shared.api.auth.SignupRequestDTO;
//import com.ms.shared.api.auth.TeacherSignupRequestDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.shared.util.util.bl.GenericService;
//import com.ms.shared.util.util.bl.IMapperNormal;
//import com.ms.shared.util.util.domain.GenericEntity;
//import com.ms.vidhyalebox.auth.JwtTokenProvider;
//import com.ms.vidhyalebox.role.RoleEntity;
//import com.ms.vidhyalebox.role.RoleRepo;
//import com.ms.vidhyalebox.user.IUserRepo;
//import com.ms.vidhyalebox.user.IUserService;
//import com.ms.vidhyalebox.user.UserEntity;
//import com.ms.vidhyalebox.user.UserMapperNormal;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.auth.AuthController;
import com.ms.vidhyalebox.holiday.HolidayEntity;
import com.ms.vidhyalebox.leavesettings.LeaveSettingsEntity;
import com.ms.vidhyalebox.leavesettings.LeaveSettingsRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollRepo;
import com.ms.vidhyalebox.salary.SalaryEntity;
import com.ms.vidhyalebox.salary.SalaryRepo;
import com.ms.vidhyalebox.staff.StaffEntity;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.InvalidItemException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeacherServiceImpl extends GenericService<GenericEntity, Long> implements ITeacherService {

	private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

	private final ITeacherRepo _iTeacherRepo;
	private final LeaveSettingsRepo leave;
	private IOrgClientRepo orgClientRepo;
	private IUserRepo userRepo;
	private IUserService userService;
	private final PasswordEncoder passwordEncoder;
	private final PayrollRepo payrollRepo;
	private final SalaryRepo salaryrepo;
	private final TeacherMapperNormal teacherMapper;

	@Autowired
	public TeacherServiceImpl(TeacherMapperNormal teacherMapper, ITeacherRepo iTeacherRepo,
			PasswordEncoder passwordEncoder, IUserService userService, IOrgClientRepo orgClientRepo,
			LeaveSettingsRepo leave, IUserRepo userRepo, PayrollRepo payrollRepo, SalaryRepo salaryrepo) {

		this._iTeacherRepo = iTeacherRepo;
		this.passwordEncoder = passwordEncoder;
		this.leave = leave;
		this.orgClientRepo = orgClientRepo;
		this.userRepo = userRepo;
		this.userService = userService;
		this.payrollRepo = payrollRepo;
		this.salaryrepo = salaryrepo;
		this.teacherMapper = teacherMapper;

	}

	@Transactional
	@Override
	public String addTeacher(TeacherDTO teacherDTO, MultipartFile image) {
		// TODO Auto-generated method stub
		try {
			if (userRepo.existsByIdentityProvider(teacherDTO.getIdentity())) {
				throw new InvalidItemException("Please contact support, teacher identity already exists.");
			}
			var salary = new SalaryEntity();
			salary.setBasicSalary(new BigDecimal(teacherDTO.getSalary()));
			salary.setPaymentDate(LocalDate.now().plusDays(30).toString());
			salary.setPaymentStatus("PENDING");
			salary.setSchool(orgClientRepo.findByOrgUniqId(teacherDTO.getSchool()).get());
			List<PayrollEntity> payrolls = new ArrayList<PayrollEntity>();
			for (Long id : teacherDTO.getPayroll()) {
				payrolls.add(payrollRepo.findById(id).get());
			}
			salary.setPayrolls(payrolls);
			long total = Long.valueOf(teacherDTO.getSalary());
			for (PayrollEntity pay : payrolls) {
				long amount = 0;
				if (!pay.getAmount().isEmpty()) {
					amount = Long.parseLong(pay.getAmount());
				} else {
					amount = (Long.parseLong(pay.getPercentage()) * Long.parseLong(teacherDTO.getSalary())) / 100;
				}

				// Conditionally add or subtract based on payroll type
				if ("ALLOWANCE".equals(pay.getPayrollType())) {
					total += amount;
				} else {
					total -= amount;
				}

			}

			salary.setNetSalary(BigDecimal.valueOf(total));
			var user = new UserEntity();
			user.setEmail(teacherDTO.getEmail());

			user.setFirstName(teacherDTO.getFirstName());
			user.setLastName(teacherDTO.getLastName());

			user.setMobileNumber(teacherDTO.getPhoneNo());
			user.setPassword(passwordEncoder.encode(teacherDTO.getPhoneNo()));
			user.setSchool(orgClientRepo.findByOrgUniqId(teacherDTO.getSchool())
					.orElseThrow(() -> new EntityNotFoundException("School not found")));
			user.setRole("ROLE_TEACHER");
			user.setIdentityProvider(teacherDTO.getIdentity());
			user.setImage(userService.saveImage(image, teacherDTO.getSchool() + "_teacher"));
			var userEntity = userRepo.save(user);
			salary.setUser(userEntity);

			var entity = new TeacherEntity();
			entity.setUser(userEntity);
			OrgClientEntity school = orgClientRepo.findByOrgUniqId(teacherDTO.getSchool()).get();
			entity.setSchool(school);
			LeaveSettingsEntity leavesettings = leave.getLeaveSettings(String.valueOf(school.getId())).get();
			salary = salaryrepo.save(salary);
			entity.setLeavesettings(leavesettings);
			salary = salaryrepo.save(salary);
			entity.setSalary(salary);
			entity.setQualification(teacherDTO.getQualification());
			entity.setCurrentAddr(teacherDTO.getCurrentAddr());
			entity.setPermanentAddr(teacherDTO.getPermanentAddr());
			_iTeacherRepo.save(entity);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Teacher added";
	}

	@Transactional
	@Override
	public String modifyTeacher(TeacherDTO teacherDTO, MultipartFile image) {

		try {
			TeacherEntity entity = _iTeacherRepo.findById((Long) teacherDTO.getId()).get();
			TeacherMapperNormal.setImage(image);
			entity = (TeacherEntity) teacherMapper.dtoToEntity(teacherDTO, entity);
			_iTeacherRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error -->", e.getStackTrace());
		}

		return null;
	}

	@Transactional
	@Override
	public Page<TeacherEntity> search(String orgId, String searchText, int page, int size, String sortBy,
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
			return _iTeacherRepo.search(orgId, searchText, pageable);
		} else {
			return _iTeacherRepo.findAll(pageable);
		}
	}

	@Override
	public JpaRepository<GenericEntity, Object> getRepo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMapperNormal getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
//
//	private final ITeacherRepo teacherRepo;
//	private final AuthenticationManager authenticationManager;
//	private final JwtTokenProvider jwtTokenProvider;
//	private final PasswordEncoder passwordEncoder;
//
//	private final TeacherMapperNormal teacherMapperNormal;
//	private final RoleRepo roleRepo;
//
//
//	public TeacherServiceImpl(ITeacherRepo teacherRepo, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, TeacherMapperNormal teacherMapperNormal, RoleRepo roleRepo) {
//        this.teacherRepo = teacherRepo;
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.passwordEncoder = passwordEncoder;
//        this.teacherMapperNormal = teacherMapperNormal;
//		this.roleRepo = roleRepo;
//    }
//
//   /* public UserEntity signup(UserEntity userEntity) {
//		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//		return userRepository.save(userEntity);
//	}*/
//
//	@Override
//	public boolean isEmailAlreadyExist(String email) {
//		return teacherRepo.existsByEmail(email);
//	}
//
//	public GenericDTO signup(TeacherSignupRequestDTO teacherSignupRequestDTO) {
//		// Encode the password
//		teacherSignupRequestDTO.setPassword(passwordEncoder.encode(teacherSignupRequestDTO.getPassword()));
//		// Fetch the RoleEntity by role name (e.g., "ROLE_SCHOOL_ADMIN")
//		RoleEntity role = roleRepo.findByName(teacherSignupRequestDTO.getRole())
//				.orElseThrow(() -> new IllegalArgumentException("Invalid role specified"));
//
//		TeacherEntity teacherEntity = (TeacherEntity) teacherMapperNormal.dtoToEntity(teacherSignupRequestDTO);
//
//		// Set the role in the OrgClientEntity
//		teacherEntity.setRoleEntity(role);
//
//		// Save the OrgClientEntity with the assigned role
//		TeacherEntity saveEntity = teacherRepo.save(teacherEntity);
//		GenericDTO genericDTO = teacherMapperNormal.entityToDto(saveEntity);
//		return genericDTO;
//	}
//
//	public String login(String phoneNumber, String password) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(phoneNumber, password)
//		);
//		return jwtTokenProvider.generateToken(phoneNumber);
//	}
//
//	public void logout() {
//		// Handle logout if needed (e.g., invalidate tokens on client-side).
//	}
//
//	public UserDetails loadUserByUsername(String username) {
//
//        return null;
//    }
//
//	public boolean isMobileNumberExist(final String mobileNumber) {
//		return teacherRepo.existsByMobileNumber(mobileNumber);
//	}
//
//	@Override
//	public JpaRepository getRepo() {
//		return teacherRepo;
//	}
//
//	@Override
//	public IMapperNormal getMapper() {
//		return teacherMapperNormal;
//	}
//}
