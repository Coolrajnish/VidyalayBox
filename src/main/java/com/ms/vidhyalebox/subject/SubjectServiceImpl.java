package com.ms.vidhyalebox.subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SubjectServiceImpl extends GenericService<GenericEntity, Long> implements SubjectService{

    private final SubjectRepo subjectRepo;
    private final SubjectMapperNormal subjectMapperNormal;

    public SubjectServiceImpl(SubjectRepo subjectRepo, SubjectMapperNormal subjectMapperNormal) {
        this.subjectRepo = subjectRepo;
        this.subjectMapperNormal = subjectMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return subjectRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return subjectMapperNormal;
    }
    @Transactional
	@Override
	public Page<SubjectEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		// TODO Auto-generated method stub
		Pageable pageable = null;
		if(sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		}else {
			pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc")?
					Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());	
		}
		if(!orgId.isEmpty()) {
			return subjectRepo.search(orgId, searchText, pageable);
		}else {
			return subjectRepo.findAll(pageable);
		}
	}
}
