package com.ms.vidhyalebox.expensecategory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ExpenseCMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Autowired
	public ExpenseCMapperNormal(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		ExpenseCategoryEntity entity = genericEntity == null ? new ExpenseCategoryEntity()
				: (ExpenseCategoryEntity) genericEntity;

		ExpenseCategoryDTO esxpenseCDTO = (ExpenseCategoryDTO) genericDto;
		if (esxpenseCDTO.getOrgUniqId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(esxpenseCDTO.getOrgUniqId());
			if (orgOpt != null && orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			}
		}

		if (esxpenseCDTO.getExpenseDescr() != null) {
			entity.setExpenseDescr(esxpenseCDTO.getExpenseDescr());
		}

		if (esxpenseCDTO.getExpenseName() != null) {
			entity.setExpenseName(esxpenseCDTO.getExpenseName());
		}

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		ExpenseCategoryEntity entity = (ExpenseCategoryEntity) genericEntity;

		ExpenseCategoryDTO expenseCDTO = new ExpenseCategoryDTO();
		expenseCDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		expenseCDTO.setExpenseDescr(entity.getExpenseDescr());
		expenseCDTO.setExpenseName(entity.getExpenseName());

		return expenseCDTO;
	}

}
