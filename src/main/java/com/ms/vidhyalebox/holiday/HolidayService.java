package com.ms.vidhyalebox.holiday;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface HolidayService extends IGenericService<GenericEntity, Long> {
	public Page<HolidayEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
