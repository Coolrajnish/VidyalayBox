package com.ms.vidhyalebox.payrollSettings;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.section.SectionDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface PayrollService extends IGenericService<GenericEntity, Long> {
	public Page<PayrollEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public PayrollEntity save(SectionDTO dto);

	public PayrollEntity modify(PayrollDTOs pDTO);
}
