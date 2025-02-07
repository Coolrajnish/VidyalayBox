package com.ms.vidhyalebox.salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SalaryMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Autowired
	public SalaryMapperNormal(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
//		SalaryEntity entity = genericEntity == null ? new SalaryEntity() : (SalaryEntity) genericEntity;
//
//		SalaryDTOs payroll = (SalaryDTOs) genericDto;
//		entity.setSchool(orgRepo.findByOrgUniqId(payroll.getOrgId()).get());
//		entity.setPayrollName(payroll.getPayrollName());
//		entity.setPayrollType(payroll.getPayrollType());
//		entity.setAmount(payroll.getAmount());
//		entity.setPercentage(payroll.getPercentage());
		return null;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
//		PayrollEntity entity = (PayrollEntity) genericEntity;
//		return new PayrollDTOs(entity.getSchool().getOrgUniqId(), entity.getPayrollType(), entity.getPayrollName(),
//				entity.getAmount(), entity.getPercentage());
		
		return null;

	}

}
