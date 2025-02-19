package com.ms.vidhyalebox.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ExpenseserviceImpl extends GenericService<GenericEntity, Long> implements ExpenseService {

	private final ExpenseRepo expenseCRepo;
	private final ExpenseMapperNormal expenseCMapperNormal;

	public ExpenseserviceImpl(ExpenseRepo expenseCRepo, ExpenseMapperNormal expenseCMapperNormal) {
		this.expenseCRepo = expenseCRepo;
		this.expenseCMapperNormal = expenseCMapperNormal;
	}

	@Override
	public JpaRepository getRepo() {
		return expenseCRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return expenseCMapperNormal;
	}

	@Transactional
	@Override
	public Page<ExpenseEntity> search(String orgId, String searchText, int page, int size, String sortBy,
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
			return expenseCRepo.search(orgId, searchText, pageable);
		} else {
			return expenseCRepo.findAll(pageable);
		}
	}

	@Override
	public ExpenseEntity save(ExpenseDTO dto) {

		ExpenseEntity entity = (ExpenseEntity) expenseCMapperNormal.dtoToEntity(dto);
		entity = expenseCRepo.save(entity);

		return entity;

	}
	
	@Transactional
	@Override
	public ExpenseEntity modify(ExpenseDTO eDTO) {
		ExpenseEntity entity = null;
		try {
			entity = expenseCRepo.findById((Long) eDTO.getId()).get();
			entity = (ExpenseEntity) expenseCMapperNormal.dtoToEntity(eDTO, entity);
			entity =  expenseCRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
