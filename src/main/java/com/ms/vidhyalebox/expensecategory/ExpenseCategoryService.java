package com.ms.vidhyalebox.expensecategory;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface ExpenseCategoryService extends IGenericService<GenericEntity, Long> {
	public Page<ExpenseCategoryEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
	public ExpenseCategoryEntity saveExpenceCategory(ExpenseCategoryDTO dto);
	public ExpenseCategoryEntity modify(ExpenseCategoryDTO eDTO);
}
