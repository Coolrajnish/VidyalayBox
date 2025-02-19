package com.ms.vidhyalebox.subject;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface SubjectService extends IGenericService<GenericEntity, Long> {
	public Page<SubjectEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public SubjectEntity save(SubjectDTO dto);

	public SubjectEntity modify(SubjectDTO subDTO);
}
