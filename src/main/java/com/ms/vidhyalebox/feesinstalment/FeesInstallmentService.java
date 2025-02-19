package com.ms.vidhyalebox.feesinstalment;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface FeesInstallmentService extends IGenericService<GenericEntity, Long> {

//	public Page<FeesInstallmentEntity> search(String orgId, String searchText, int page, int size, String sortBy,
//			String sortOrder);

	public FeesInstallmentEntity save(FeesInstallmentDTO dto);
}
