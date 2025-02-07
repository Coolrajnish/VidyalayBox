package com.ms.vidhyalebox.payrollSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class PayrollMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Autowired
	public PayrollMapperNormal(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		PayrollEntity entity = genericEntity == null ? new PayrollEntity() : (PayrollEntity) genericEntity;

		PayrollDTOs payroll = (PayrollDTOs) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(payroll.getOrgUniqueId()).get());
		entity.setPayrollName(payroll.getPayrollName());
		entity.setPayrollType(payroll.getPayrollType());
		entity.setAmount(payroll.getAmount());
		entity.setPercentage(payroll.getPercentage());
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		PayrollEntity entity = (PayrollEntity) genericEntity;
		return new PayrollDTOs(entity.getSchool().getOrgUniqId(), entity.getPayrollType(), entity.getPayrollName(),
				entity.getAmount(), entity.getPercentage());

	}

}
