package com.ms.vidhyalebox.feestype;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface FeesTypeService extends IGenericService<GenericEntity, Long> {

//	public Page<FeesTypeEntity> search(String orgId, String searchText, int page, int size, String sortBy,
//			String sortOrder);

	public FeesTypeEntity save(FeesTypeDTO dto);
}
