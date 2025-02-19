package com.ms.vidhyalebox.expensecategory;

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
public class ExpenseCategoryserviceImpl extends GenericService<GenericEntity, Long> implements ExpenseCategoryService {

    private final ExpenseCategoryRepo expenseCRepo;
    private final ExpenseCMapperNormal  expenseCMapperNormal;

    public ExpenseCategoryserviceImpl(ExpenseCategoryRepo expenseCRepo, ExpenseCMapperNormal expenseCMapperNormal) {
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

	@Override
	public Page<ExpenseCategoryEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		System.out.println(orgId + "--"+searchText+"--"+page+"--"+size);
		// TODO Auto-generated method stub
		try {
			Pageable pageable = null;
			if(sortBy.isEmpty()) {
				pageable = PageRequest.of(page, size);
			}else {
				pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc")?
						Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());	
			}
			if(!orgId.isEmpty()) {
				return expenseCRepo.search(orgId, searchText, pageable);
			}else {
				return expenseCRepo.findAll(pageable);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    @Override
    @Transactional
    public ExpenseCategoryEntity saveExpenceCategory(ExpenseCategoryDTO dto) {
    	
    	ExpenseCategoryEntity entity =(ExpenseCategoryEntity) expenseCMapperNormal.dtoToEntity(dto);
    	entity = expenseCRepo.save(entity);
    	
		return entity;
    	
    }
    
    @Transactional
	@Override
	public ExpenseCategoryEntity modify(ExpenseCategoryDTO eDTO) {
		ExpenseCategoryEntity entity = null;
		try {
			entity = expenseCRepo.findById((Long) eDTO.getId()).get();
			entity = (ExpenseCategoryEntity) expenseCMapperNormal.dtoToEntity(eDTO, entity);
			entity =  expenseCRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
