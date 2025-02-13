package com.ms.vidhyalebox.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.leavesettings.LeaveSettingsRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.payrollSettings.PayrollRepo;
import com.ms.vidhyalebox.salary.SalaryRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class StaffMapperNormal implements IMapperNormal {
	private final IStaffRepo _iStaffRepo;
	private final LeaveSettingsRepo leave;
	private IOrgClientRepo orgClientRepo;
	private IUserRepo userRepo;
	private IUserService userService;
	private final PasswordEncoder passwordEncoder;
	private final PayrollRepo payrollRepo;
	private final SalaryRepo salaryrepo;
	// private SessionRepo sessionRepo;
	// private final StaffMapperNormal _staffMapperNormal;

	@Autowired
	public StaffMapperNormal(IStaffRepo iStaffRepo, PasswordEncoder passwordEncoder, IUserService userService,
			IOrgClientRepo orgClientRepo, LeaveSettingsRepo leave, IUserRepo userRepo, PayrollRepo payrollRepo,
			SalaryRepo salaryrepo) {
		this._iStaffRepo = iStaffRepo;
		this.passwordEncoder = passwordEncoder;
		this.leave = leave;
		this.orgClientRepo = orgClientRepo;
		this.userRepo = userRepo;
		this.userService = userService;
		// this._staffMapperNormal = staffMapperNormal;
		// this.roleRepo = roleRepo;
		this.payrollRepo = payrollRepo;
		this.salaryrepo = salaryrepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
//        StaffEntity entity = genericEntity == null ? new StaffEntity() : (StaffEntity) genericEntity;
//
//        StaffDTO staffDTO = (StaffDTO) genericDto;
//
//        var salary = new SalaryEntity();
//		salary.setBasicSalary(new BigDecimal(staffDTO.getSalary()));
//		salary.setPaymentDate(LocalDate.now().plusDays(30).toString());
//		salary.setPaymentStatus("PENDING");
//		salary.setSchool(orgClientRepo.findByOrgUniqId(staffDTO.getSchool()).get());
//		List<PayrollEntity> payrolls = new ArrayList<PayrollEntity>(); 
//		for(Long id : staffDTO.getPayroll()) {
//			payrolls.add(payrollRepo.findById(id).get());
//		}
//		salary.setPayrolls(payrolls);
//		long total = Long.valueOf(staffDTO.getSalary());
//		for (PayrollEntity pay : payrolls) {
//			long amount = 0;
//			if (!pay.getAmount().isEmpty()) {
//				amount = Long.parseLong(pay.getAmount());
//			} else {
//				amount = (Long.parseLong(pay.getPercentage()) * Long.parseLong(staffDTO.getSalary())) / 100;
//			}
//
//			if ("ALLOWANCE".equals(pay.getPayrollType())) {
//				total += amount;
//			} else {
//				total -= amount;
//			}
//		}
//
//		salary.setNetSalary(BigDecimal.valueOf(total));
//		var user = new UserEntity();
//		user.setEmail(staffDTO.getEmail());
//
//		user.setFirstName(staffDTO.getFirstName());
//		user.setLastName(staffDTO.getLastName());
//
//		user.setMobileNumber(staffDTO.getPhoneNo());
//		user.setPassword(passwordEncoder.encode(staffDTO.getPhoneNo()));
//		user.setSchool(orgClientRepo.findByOrgUniqId(staffDTO.getSchool())
//				.orElseThrow(() -> new EntityNotFoundException("School not found")));
//		user.setRole("ROLE_STAFF");
//		user.setIdentityProvider(staffDTO.getIdentity());
//		user.setImage(userService.saveImage(image, staffDTO.getSchool() + "_staff"));
//		var userEntity = userRepo.save(user);
//		salary.setUser(userEntity);
//		entity.setUser(userEntity);
//		OrgClientEntity school = orgClientRepo.findByOrgUniqId(staffDTO.getSchool()).get();
//		entity.setSchool(school);
//		List<LeaveSettingsEntity> leavesettings = leave.getLeaveSettings( ""+school.getId()).get();
//		LeaveSettingsEntity leaveisactive = null;
//		for(LeaveSettingsEntity leaveval : leavesettings) {
//			if(leaveval.getSession().isActive()) {
//				leaveisactive = leaveval;
//			}
//		}
//		salary =  salaryrepo.save(salary);
//		entity.setLeavesettings(leaveisactive);
//		entity.setSalary(salary);

		return null;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		StaffEntity clientEntity = (StaffEntity) genericEntity;

//        StaffSignupResponseDTO signupResponseDTO = new StaffSignupResponseDTO();
//        signupResponseDTO.setName(clientEntity.getFirstName().concat(" ").concat(clientEntity.getLastName()));
//        signupResponseDTO.setEmailAddress(clientEntity.getEmail());
//        signupResponseDTO.setMobileNumber(clientEntity.getMobileNumber());
//        signupResponseDTO.setIsActive(clientEntity.isActive());
//        signupResponseDTO.setAccountNonExpired(clientEntity.isAccountNonExpired()); // If email verification logic is available, use it
//        signupResponseDTO.setAccountNonLocked(clientEntity.isAccountNonLocked());  //If phone verification logic is available, use it
//        signupResponseDTO.setOrgUniqId(clientEntity.getOrgUniqId());//  Set default or as per actual logic

		return null;
	}
}