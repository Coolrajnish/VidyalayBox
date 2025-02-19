package com.ms.vidhyalebox.Class;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface ClassService extends IGenericService<GenericEntity, Long> {

	public Page<ClassEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public ClassEntity save(ClassDTO dto);

	public ClassEntity modify(ClassDTO classDTO);
}
