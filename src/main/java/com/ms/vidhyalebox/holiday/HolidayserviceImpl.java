package com.ms.vidhyalebox.holiday;

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
public class HolidayserviceImpl extends GenericService<GenericEntity, Long> implements HolidayService {

    private final HolidayRepo expenseCRepo;
    private final HolidayMapperNormal  expenseCMapperNormal;

    public HolidayserviceImpl(HolidayRepo expenseCRepo, HolidayMapperNormal expenseCMapperNormal) {
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
	public Page<HolidayEntity> search(String orgId, String searchText, int page, int size, String sortBy,
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
			return expenseCRepo.search(orgId, searchText, pageable);
		}else {
			return expenseCRepo.findAll(pageable);
		}
	}
}
