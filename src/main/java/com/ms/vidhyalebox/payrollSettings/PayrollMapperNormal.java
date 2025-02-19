package com.ms.vidhyalebox.payrollSettings;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
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
		// Check if orgUniqueId is provided and not null before attempting to update
		if (payroll.getOrgUniqueId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(payroll.getOrgUniqueId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			}
		}

		// Check if payrollName is not null before updating
		if (payroll.getPayrollName() != null) {
			entity.setPayrollName(payroll.getPayrollName());
		}

		// Check if payrollType is not null before updating
		if (payroll.getPayrollType() != null) {
			entity.setPayrollType(payroll.getPayrollType());
		}

		// Check if amount is not null before updating
		if (payroll.getAmount() != null) {
			entity.setAmount(payroll.getAmount());
		}

		// Check if percentage is not null before updating
		if (payroll.getPercentage() != null) {
			entity.setPercentage(payroll.getPercentage());
		}

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		PayrollEntity entity = (PayrollEntity) genericEntity;
		return new PayrollDTOs(entity.getSchool().getOrgUniqId(), entity.getPayrollType(), entity.getPayrollName(),
				entity.getAmount(), entity.getPercentage());

	}

}
