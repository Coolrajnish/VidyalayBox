package com.ms.vidhyalebox.fees;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface FeesService extends IGenericService<GenericEntity, Long> {

	public Page<FeesEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public FeesEntity save(FeesDTO dto);

	public FeesEntity modify(FeesDTO dto);
}
