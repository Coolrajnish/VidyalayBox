package com.ms.vidhyalebox.section;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface SectionService extends IGenericService<GenericEntity, Long> {

	public Page<SectionEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public SectionEntity save(SectionDTO dto);

	public SectionEntity modify(SectionDTO sDTO);
}
