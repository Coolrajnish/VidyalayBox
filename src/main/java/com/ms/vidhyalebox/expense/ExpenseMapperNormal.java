package com.ms.vidhyalebox.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.expensecategory.ExpenseCategoryDTO;
import com.ms.vidhyalebox.expensecategory.ExpenseCategoryEntity;
import com.ms.vidhyalebox.expensecategory.ExpenseCategoryRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ExpenseMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private ExpenseCategoryRepo repo;
	private SessionRepo session;

	@Autowired
	public ExpenseMapperNormal(IOrgClientRepo orgRepo, ExpenseCategoryRepo repo, SessionRepo session) {
		this.orgRepo = orgRepo;
		this.repo = repo;
		this.session = session;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		ExpenseEntity entity = genericEntity == null ? new ExpenseEntity() : (ExpenseEntity) genericEntity;

		ExpenseDTO esxpenseCDTO = (ExpenseDTO) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(esxpenseCDTO.getOrgUniqId()).get());
		entity.setExpensecategory(repo.findById(Long.valueOf(esxpenseCDTO.getExpensecategoryId())).get());
		entity.setAmount(esxpenseCDTO.getAmount());
		entity.setDescr(esxpenseCDTO.getDescr());
		entity.setTitle(esxpenseCDTO.getTitle());
		entity.setSession(session.findById(Long.valueOf(esxpenseCDTO.getSessionId())).get());
		entity.setDate(esxpenseCDTO.getDate());
		
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		ExpenseEntity entity = (ExpenseEntity) genericEntity;

		ExpenseDTO expenseCDTO = new ExpenseDTO();
		expenseCDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		expenseCDTO.setAmount(entity.getAmount());
		expenseCDTO.setDescr(entity.getDescr());
		expenseCDTO.setTitle(entity.getTitle());
		expenseCDTO.setId(entity.getId());
		
		return expenseCDTO;
	}

}
