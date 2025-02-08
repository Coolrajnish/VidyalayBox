package com.ms.vidhyalebox.shift;

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
public class ShiftServiceImpl extends GenericService<GenericEntity, Long> implements ShiftService{

    private final ShiftMapperNormal shiftMapperNormal;

    public ShiftServiceImpl(ShiftMapperNormal shiftMapperNormal, ShiftRepo shiftRepo) {
        this.shiftMapperNormal = shiftMapperNormal;
        this.shiftRepo = shiftRepo;
    }

    private final ShiftRepo shiftRepo;

    @Override
    public JpaRepository getRepo() {
        return shiftRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return shiftMapperNormal;
    }

    @Transactional
	@Override
	public Page<ShiftEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if(sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		}else {
			pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc")?
					Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());				
		}
		if(!orgId.isEmpty()) {
			return shiftRepo.search(orgId, searchText, pageable);
		}else {
			return shiftRepo.findAll(pageable);
		}
		
	
	}
}
