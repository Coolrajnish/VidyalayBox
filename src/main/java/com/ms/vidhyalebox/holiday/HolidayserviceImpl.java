package com.ms.vidhyalebox.holiday;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	HolidayMapperNormal holidayMapperNormal;

	// @Autowired
	// HolidayMapper holidayMapper;

//    public HolidayserviceImpl(HolidayRepo holidayRepo, HolidayMapperNormal holidayMapperNormal) {
//        this.holidayRepo = holidayRepo;
//        this.holidayMapperNormal = holidayMapperNormal;
//    }

	@Override
	public JpaRepository getRepo() {
		return holidayRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return holidayMapperNormal;
	}

	@Transactional
	@Override
	public Page<HolidayEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		// TODO Auto-generated method stub
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return holidayRepo.search(orgId, searchText, pageable);
		} else {
			return holidayRepo.findAll(pageable);
		}
	}

	@Transactional
	@Override
	public HolidayEntity updateHolidatFromDTO(HolidayDTO holidayDTO) {
		HolidayEntity holiday = holidayRepo.findById((Long) holidayDTO.getId()).get();
		holiday = (HolidayEntity) holidayMapperNormal.dtoToEntity(holidayDTO, holiday);
		holidayRepo.save(holiday);
		return holiday;
	}

	@Override
	public HolidayEntity save(HolidayDTO dto) {
		HolidayEntity entity = (HolidayEntity) holidayMapperNormal.dtoToEntity(dto);
		entity = holidayRepo.save(entity);

		return entity;
	}

}
