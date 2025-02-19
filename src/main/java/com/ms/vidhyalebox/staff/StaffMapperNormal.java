package com.ms.vidhyalebox.staff;

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
import com.ms.vidhyalebox.leavesettings.LeaveSettingsserviceImpl;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollRepo;
import com.ms.vidhyalebox.salary.SalaryEntity;
import com.ms.vidhyalebox.salary.SalaryRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.teacher.TeacherDTO;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffMapperNormal implements IMapperNormal {

	private static final Logger logger = LoggerFactory.getLogger(StaffMapperNormal.class);

	@Autowired
	IStaffRepo _iStaffRepo;
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
	@Autowired
	LeaveSettingsserviceImpl leaves;
	
	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDTO, GenericEntity genericEntity) {
		StaffEntity entity = null;

		try {
			entity = genericEntity == null ? new StaffEntity() : (StaffEntity) genericEntity;
			StaffDTO staffDTO = (StaffDTO) genericDTO;
			var salary = genericEntity == null ? new SalaryEntity() : entity.getSalary();

			// Null check for staff salary
			if (staffDTO.getSalary() != null) {
				salary.setBasicSalary(new BigDecimal(staffDTO.getSalary()));
			}

			if (entity.getSalary().getPaymentDate() != null) {
				salary.setPaymentDate(LocalDate.now().plusDays(30).toString());
			}

			if (staffDTO.getStatus() != null) {
				salary.setPaymentStatus(staffDTO.getStatus());
			}

			// Null check for staff school
			if (staffDTO.getSchool() != null) {
				salary.setSchool(orgClientRepo.findByOrgUniqId(staffDTO.getSchool())
						.orElseThrow(() -> new EntityNotFoundException("School not found")));
			}

			List<PayrollEntity> payrolls = entity.getSalary().getPayrolls() != null ? entity.getSalary().getPayrolls()
					: new ArrayList<>();

			if (staffDTO.getPayroll() != null) {
				for (Long id : staffDTO.getPayroll()) {
					PayrollEntity payroll = payrollRepo.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("Payroll not found for id " + id));
					payrolls.add(payroll);
				}
			}
			if (!payrolls.isEmpty()) {
				salary.setPayrolls(payrolls);
			}

			long total = (staffDTO.getSalary() != null) ? Long.valueOf(staffDTO.getSalary()) : 0;
			if (total != 0) {
				for (PayrollEntity pay : payrolls) {
					long amount = 0;
					if (pay.getAmount() != null && !pay.getAmount().isEmpty()) {
						amount = Long.parseLong(pay.getAmount());
					} else if (pay.getPercentage() != null && staffDTO.getSalary() != null) {
						amount = (Long.parseLong(pay.getPercentage()) * Long.parseLong(staffDTO.getSalary())) / 100;
					}
					if ("ALLOWANCE".equals(pay.getPayrollType())) {
						total += amount;
					} else {
						total -= amount;
					}
				}
				salary.setNetSalary(BigDecimal.valueOf(total));
			}
			var user = genericEntity == null ? new UserEntity() : entity.getUser();
			if (staffDTO.getEmail() != null) {
				user.setEmail(staffDTO.getEmail());
			}

			if (staffDTO.getFirstName() != null) {
				user.setFirstName(staffDTO.getFirstName());
			}

			if (staffDTO.getLastName() != null) {
				user.setLastName(staffDTO.getLastName());
			}

			if (staffDTO.getPhoneNo() != null) {
				user.setMobileNumber(staffDTO.getPhoneNo());
				user.setPassword(passwordEncoder.encode(staffDTO.getPhoneNo())); // Assuming phone number is used as //
																					// the
																					// password
			}

			// Null check for school and identity provider
			if (staffDTO.getSchool() != null) {
				user.setSchool(orgClientRepo.findByOrgUniqId(staffDTO.getSchool())
						.orElseThrow(() -> new EntityNotFoundException("School not found")));
			}

			if (staffDTO.getIdentity() != null) {
				user.setIdentityProvider(staffDTO.getIdentity());
			}

			// Image saving
			if (staffDTO.getFile() != null) {
				user.setImage(userService.saveImage(staffDTO.getFile(), entity.getSchool().getOrgUniqId() + "_staff"));
			}
			UserEntity userEntity = user;
			if (!user.equals(entity.getUser())) {
				userEntity = userRepo.save(user);
			}

			salary.setUser(userEntity);

			entity.setUser(userEntity);

			// Null check for school (already checked above)
			OrgClientEntity school = orgClientRepo.findByOrgUniqId(staffDTO.getSchool())
					.orElseThrow(() -> new EntityNotFoundException("School not found"));
			entity.setSchool(school);

			LeaveSettingsEntity leavesettings = genericEntity != null ? entity.getLeavesettings()
					: leave.findById(leaves.getLeaveSettings(String.valueOf(school.getId())).getId()).get();

			if (salary != null) {
				salary = salaryrepo.save(salary);
			}

			if (leavesettings != null) {
				entity.setLeavesettings(leavesettings);
			}

			if (salary != null) {
				entity.setSalary(salary);
			}

			if (staffDTO.getCurrentAddr() != null) {
				entity.setCurrentAddr(staffDTO.getCurrentAddr());
			}

			if (staffDTO.getPermanentAddr() != null) {
				entity.setPermanentAddr(staffDTO.getPermanentAddr());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		StaffEntity entity = (StaffEntity) genericEntity;
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setId(entity.getId());
		staffDTO.setFirstName(entity.getUser().getFirstName());
		return staffDTO;
	}
}