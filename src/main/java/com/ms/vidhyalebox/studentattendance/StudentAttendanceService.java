package com.ms.vidhyalebox.studentattendance;

import org.springframework.data.domain.Page;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface StudentAttendanceService extends IGenericService<GenericEntity, Long> {

	public Page<StudentAttendanceEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder);

	public StudentAttendanceEntity save(StudentAttendanceDTO dto);
}
