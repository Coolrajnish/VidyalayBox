package com.ms.vidhyalebox.medium;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class MediumServiceImpl extends GenericService<GenericEntity, Long> implements MediumService {

	private final MediumMapper mediumMapper;

	private final MediumRepo mediumRepo;

	public MediumServiceImpl(MediumMapper mediumMapper, MediumRepo mediumRepo) {
		this.mediumMapper = mediumMapper;
		this.mediumRepo = mediumRepo;
	}

	@Override
	public JpaRepository getRepo() {
		return mediumRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return mediumMapper;
	}

	@Override
	public Page<MediumEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return mediumRepo.search(orgId, searchText, pageable);
		} else {
			return mediumRepo.findAll(pageable);
		}
	}

	@Override
	public MediumEntity save(MediumDTO dto) {

		MediumEntity entity = (MediumEntity) mediumMapper.dtoToEntity(dto);
		entity = mediumRepo.save(entity);

		return entity;
	}

	@Transactional
	@Override
	public MediumEntity modify(MediumDTO mediumDTO) {
		MediumEntity entity = null;
		try {
			entity = mediumRepo.findById((Long) mediumDTO.getId()).get();
			entity = (MediumEntity) mediumMapper.dtoToEntity(mediumDTO, entity);
			entity =  mediumRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
