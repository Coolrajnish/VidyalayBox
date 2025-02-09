package com.ms.vidhyalebox.expense;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface ExpenseService extends IGenericService<GenericEntity, Long> {
	public Page<ExpenseEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
