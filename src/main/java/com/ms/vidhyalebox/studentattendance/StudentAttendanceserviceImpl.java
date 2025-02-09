package com.ms.vidhyalebox.studentattendance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.transaction.Transactional;

@Service
public class StudentAttendanceserviceImpl extends GenericService<GenericEntity, Long> implements StudentAttendanceService {

    private final StudentAttendanceRepo expenseCRepo;
    private final StudentAttendanceMapperNormal  expenseCMapperNormal;

    public StudentAttendanceserviceImpl(StudentAttendanceRepo expenseCRepo, StudentAttendanceMapperNormal expenseCMapperNormal) {
        this.expenseCRepo = expenseCRepo;
        this.expenseCMapperNormal = expenseCMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return expenseCRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return expenseCMapperNormal;
    }

    @Transactional
	@Override
	public Page<StudentAttendanceEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if(sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		}else {
			pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc")?
					Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());	
		}
		if(!orgId.isEmpty()) {
			return expenseCRepo.search(orgId, searchText, pageable);
		}else {
			return expenseCRepo.findAll(pageable);
		}
	}
}
