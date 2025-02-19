package com.ms.vidhyalebox.feesdiscount;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface FeesDiscountService extends IGenericService<GenericEntity, Long> {

//	public Page<FeesDiscountEntity> search(String orgId, String searchText, int page, int size, String sortBy,
//			String sortOrder);

	public FeesDiscountEntity save(FeesDiscountDTO dto);
}
