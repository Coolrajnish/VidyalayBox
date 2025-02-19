package com.ms.vidhyalebox.medium;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface MediumService extends IGenericService<GenericEntity, Long> {

	public Page<MediumEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public MediumEntity save(MediumDTO dto);
	public MediumEntity modify(MediumDTO dto);
}
