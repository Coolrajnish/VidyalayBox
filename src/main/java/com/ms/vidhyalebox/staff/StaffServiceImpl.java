package com.ms.vidhyalebox.staff;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ms.vidhyalebox.leavesettings.LeaveSettingsEntity;
import com.ms.vidhyalebox.leavesettings.LeaveSettingsRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollRepo;
import com.ms.vidhyalebox.salary.SalaryEntity;
import com.ms.vidhyalebox.salary.SalaryRepo;
import com.ms.vidhyalebox.teacher.TeacherDTO;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.teacher.TeacherServiceImpl;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.InvalidItemException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffServiceImpl extends GenericService<GenericEntity, Long> implements IStaffService {
	
	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	private final IStaffRepo _iStaffRepo;
	private final LeaveSettingsRepo leave;
	private IOrgClientRepo orgClientRepo;
	private IUserRepo userRepo;
	private IUserService userService;
	private final PasswordEncoder passwordEncoder;
	private final PayrollRepo payrollRepo;
	private final SalaryRepo salaryrepo;
	private final StaffMapperNormal _staffMapper;

	@Autowired
	public StaffServiceImpl( StaffMapperNormal _staffMapperNormal,IStaffRepo iStaffRepo, PasswordEncoder passwordEncoder,
			 IUserService userService, IOrgClientRepo orgClientRepo,
			 LeaveSettingsRepo leave, IUserRepo userRepo, PayrollRepo payrollRepo, SalaryRepo salaryrepo) {
		this._iStaffRepo = iStaffRepo;
		this.passwordEncoder = passwordEncoder;
		this.leave = leave;
		this.orgClientRepo = orgClientRepo;
		this.userRepo = userRepo;
		this.userService = userService;
		this.payrollRepo = payrollRepo;
		this.salaryrepo = salaryrepo;
		this._staffMapper = _staffMapperNormal;
	}

	@Transactional
	@Override
	public String addStaff(StaffDTO staffDTO, MultipartFile image) {
		try {
			if (userRepo.existsByIdentityProvider(staffDTO.getIdentity())) {
				throw new InvalidItemException("Please contact support, staff identity already exists.");
			}
			// Retaining casting for ParentEntity
			var salary = new SalaryEntity();
			salary.setBasicSalary(new BigDecimal(staffDTO.getSalary()));
			salary.setPaymentDate(LocalDate.now().plusDays(30).toString());
			salary.setPaymentStatus("PENDING");
			salary.setSchool(orgClientRepo.findByOrgUniqId(staffDTO.getSchool()).get());
			List<PayrollEntity> payrolls = new ArrayList<PayrollEntity>(); 
			for(Long id : staffDTO.getPayroll()) {
				payrolls.add(payrollRepo.findById(id).get());
			}
			salary.setPayrolls(payrolls);
			long total = Long.valueOf(staffDTO.getSalary());
			for (PayrollEntity pay : payrolls) {
				long amount = 0;
				if (!pay.getAmount().isEmpty()) {
					amount = Long.parseLong(pay.getAmount());
				} else {
					amount = (Long.parseLong(pay.getPercentage()) * Long.parseLong(staffDTO.getSalary())) / 100;
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
			user.setEmail(staffDTO.getEmail());

			user.setFirstName(staffDTO.getFirstName());
			user.setLastName(staffDTO.getLastName());

			user.setMobileNumber(staffDTO.getPhoneNo());
			user.setPassword(passwordEncoder.encode(staffDTO.getPhoneNo()));
			user.setSchool(orgClientRepo.findByOrgUniqId(staffDTO.getSchool())
					.orElseThrow(() -> new EntityNotFoundException("School not found")));
			user.setRole("ROLE_STAFF");
			user.setIdentityProvider(staffDTO.getIdentity());
			user.setImage(userService.saveImage(image, staffDTO.getSchool() + "_staff"));
			var userEntity = userRepo.save(user);
			salary.setUser(userEntity);
			// Retaining casting for StudentEntity
			var entity = new StaffEntity();
			entity.setUser(userEntity);
			OrgClientEntity school = orgClientRepo.findByOrgUniqId(staffDTO.getSchool()).get();
			entity.setSchool(school);
			LeaveSettingsEntity leavesettings = leave.getLeaveSettings(String.valueOf(school.getId())).get();
			salary =  salaryrepo.save(salary);
			entity.setLeavesettings(leavesettings);
			entity.setSalary(salary);
			entity.setCurrentAddr(staffDTO.getCurrentAddr());
			entity.setPermanentAddr(staffDTO.getPermanentAddr());
			_iStaffRepo.save(entity);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Staff added";
	}
	@Transactional
	@Override
	public String modifyStaff(StaffDTO staffDTO, MultipartFile image) {

		try {
			StaffEntity entity = _iStaffRepo.findById((Long) staffDTO.getId()).get();
			entity = (StaffEntity) _staffMapper.dtoToEntity(staffDTO, entity);
			_iStaffRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error -->", e.getStackTrace());
		}

		return null;
	}

	@Override
	public JpaRepository <GenericEntity, Object> getRepo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMapperNormal getMapper() {
		// TODO Auto-generated method stub
		return  _staffMapper;
	}
	
    @Transactional
    @Override
    public Page<StaffEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = null;
        if(sortBy.isEmpty()){
             pageable = PageRequest.of(page, size);
        } else {
             pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        }
        if(!orgId.isEmpty() ){
            return _iStaffRepo.search(orgId, searchText, pageable);
        } else {
            return _iStaffRepo.findAll(pageable);
        }
    }

}
