package com.ms.vidhyalebox.feesamount;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface FeesAmountService extends IGenericService<GenericEntity, Long> {

//	public Page<FeesEntity> search(String orgId, String searchText, int page, int size, String sortBy,
//			String sortOrder);

	public FeesAmountEntity save(FeesAmountDTO dto);
}
