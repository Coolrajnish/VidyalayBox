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
public class ShiftServiceImpl extends GenericService<GenericEntity, Long> implements ShiftService {

	private final ShiftMapperNormal shiftMapperNormal;
	private final ShiftRepo shiftRepo;

	public ShiftServiceImpl(ShiftMapperNormal shiftMapperNormal, ShiftRepo shiftRepo) {
		this.shiftMapperNormal = shiftMapperNormal;
		this.shiftRepo = shiftRepo;
	}

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
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return shiftRepo.search(orgId, searchText, pageable);
		} else {
			return shiftRepo.findAll(pageable);
		}

	}

	public ShiftEntity save(ShiftDTO dto) {

		ShiftEntity entity = (ShiftEntity) shiftMapperNormal.dtoToEntity(dto);
		entity = shiftRepo.save(entity);

		return entity;

	}
	
	@Transactional
	@Override
	public ShiftEntity modify(ShiftDTO shiftDTO) {
		ShiftEntity entity = null;
		try {
			entity = shiftRepo.findById((Long) shiftDTO.getId()).get();
			entity = (ShiftEntity) shiftMapperNormal.dtoToEntity(shiftDTO, entity);
			entity =  shiftRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
