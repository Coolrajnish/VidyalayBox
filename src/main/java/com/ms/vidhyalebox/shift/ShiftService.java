package com.ms.vidhyalebox.shift;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface ShiftService extends IGenericService<GenericEntity, Long> {
	public Page<ShiftEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
