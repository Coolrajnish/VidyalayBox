package com.ms.vidhyalebox.stream;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface StreamService extends IGenericService<GenericEntity, Long> {
	public Page<StreamEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
