package com.ms.vidhyalebox.fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.transaction.Transactional;

@Service
public class FeesServiceImpl extends GenericService<GenericEntity, Long> implements FeesService {

	@Autowired
	FeesMapperNormal feesMapperNormal;

	@Autowired
	FeesRepo feesRepo;

	@Override
	public JpaRepository getRepo() {
		return feesRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return feesMapperNormal;
	}

	@Transactional
	@Override
	public Page<FeesEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return feesRepo.search(orgId, searchText, pageable);
		} else {
			return feesRepo.findAll(pageable);
		}
	}

	@Transactional
	@Override
	public FeesEntity save(FeesDTO dto) {

		FeesEntity entity = (FeesEntity) feesMapperNormal.dtoToEntity(dto);
		entity = feesRepo.save(entity);

		return entity;

	}

	@Transactional
	@Override
	public FeesEntity modify(FeesDTO dto) {

		FeesEntity entity = feesRepo.findById((Long) dto.getId()).get();
		entity = (FeesEntity) feesMapperNormal.dtoToEntity(dto);
		entity = feesRepo.save(entity);

		return entity;

	}

}
