package com.ms.vidhyalebox.assignment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface AssignmentService extends IGenericService<GenericEntity, Long> {
	public Page<AssignmentEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	AssignmentEntity modify(AssignmentDTO mediumDTO);

	AssignmentEntity save(AssignmentDTO dto);
}
