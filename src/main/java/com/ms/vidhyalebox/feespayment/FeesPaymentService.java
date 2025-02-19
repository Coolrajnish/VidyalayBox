package com.ms.vidhyalebox.feespayment;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface FeesPaymentService extends IGenericService<GenericEntity, Long> {

//	public Page<FeesEntity> search(String orgId, String searchText, int page, int size, String sortBy,
//			String sortOrder);

	public FeesPaymentEntity save(FeesPaymentDTO dto);
}
