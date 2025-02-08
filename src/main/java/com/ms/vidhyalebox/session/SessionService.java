package com.ms.vidhyalebox.session;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.section.SectionEntity;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface SessionService extends IGenericService<GenericEntity, Long> {
	 public Page<SessionEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
