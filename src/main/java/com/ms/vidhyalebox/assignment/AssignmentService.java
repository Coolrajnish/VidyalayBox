package com.ms.vidhyalebox.assignment;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface AssignmentService extends IGenericService<GenericEntity, Long> {
	public Page<AssignmentEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
