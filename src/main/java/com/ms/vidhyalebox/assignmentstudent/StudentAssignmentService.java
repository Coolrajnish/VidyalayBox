package com.ms.vidhyalebox.assignmentstudent;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface StudentAssignmentService extends IGenericService<GenericEntity, Long> {
	public Page<StudentAssignmentEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public StudentAssignmentEntity save(StudentAssignmentDTO dto);

	public StudentAssignmentEntity modify(StudentAssignmentDTO saDTO);
}
