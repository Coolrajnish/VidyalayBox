package com.ms.vidhyalebox.timetable;

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
public class TimetableserviceImpl extends GenericService<GenericEntity, Long> implements TimetableService {

    private final TimetableRepo timetableRepo;
    private final TimetableMapperNormal  timetableMapperNormal;

    public TimetableserviceImpl(TimetableRepo timetableRepo,TimetableMapperNormal timetableMapperNormal) {
        this.timetableRepo = timetableRepo;
        this.timetableMapperNormal = timetableMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return timetableRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return timetableMapperNormal;
    }

    @Transactional
	@Override
	public Page<TimetableEntity> search(String orgId, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc")?
				Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		
			return timetableRepo.findAll(pageable);
	}
}
