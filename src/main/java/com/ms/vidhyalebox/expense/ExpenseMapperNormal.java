package com.ms.vidhyalebox.expense;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.expensecategory.ExpenseCategoryEntity;
import com.ms.vidhyalebox.expensecategory.ExpenseCategoryRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
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
		// Check if orgUniqId is provided and not null before attempting to update
		if (esxpenseCDTO.getOrgUniqId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(esxpenseCDTO.getOrgUniqId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			} 
		}

		// Check if expensecategoryId is provided and valid before updating
		if (esxpenseCDTO.getExpensecategoryId() != null) {
			Optional<ExpenseCategoryEntity> expenseCategoryOpt = repo
					.findById(Long.valueOf(esxpenseCDTO.getExpensecategoryId()));
			if (expenseCategoryOpt.isPresent()) {
				entity.setExpensecategory(expenseCategoryOpt.get());
			} 
		}

		// Check if amount is not null before setting it
		if (esxpenseCDTO.getAmount() != null) {
			entity.setAmount(esxpenseCDTO.getAmount());
		}

		// Check if descr is not null before updating
		if (esxpenseCDTO.getDescr() != null) {
			entity.setDescr(esxpenseCDTO.getDescr());
		}

		// Check if title is not null before setting it
		if (esxpenseCDTO.getTitle() != null) {
			entity.setTitle(esxpenseCDTO.getTitle());
		}

		// Check if sessionId is provided and valid before updating
		if (esxpenseCDTO.getSessionId() != null) {
			Optional<SessionEntity> sessionOpt = session.findById(Long.valueOf(esxpenseCDTO.getSessionId()));
			if (sessionOpt.isPresent()) {
				entity.setSession(sessionOpt.get());
			} 
		}

		// Check if date is not null before updating
		if (esxpenseCDTO.getDate() != null) {
			entity.setDate(esxpenseCDTO.getDate());
		}

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
