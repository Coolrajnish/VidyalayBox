package com.ms.vidhyalebox.section;

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
public class SectionServiceImpl extends GenericService<GenericEntity, Long> implements SectionService {

	private final SectionRepo sectionRepo;
	private final SectionMapperNormal sectionMapperNormal;

	public SectionServiceImpl(SectionRepo sectionRepo, SectionMapperNormal sectionMapperNormal) {
		this.sectionRepo = sectionRepo;
		this.sectionMapperNormal = sectionMapperNormal;
	}

	@Override
	public JpaRepository getRepo() {
		return sectionRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return sectionMapperNormal;
	}

	@Transactional
	@Override
	public Page<SectionEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return sectionRepo.search(orgId, searchText, pageable);
		} else {
			return sectionRepo.findAll(pageable);
		}
	}

	@Transactional
	@Override
	public SectionEntity save(SectionDTO dto) {

		SectionEntity entity = (SectionEntity) sectionMapperNormal.dtoToEntity(dto);
		entity = sectionRepo.save(entity);

		return entity;
	}
	
	@Transactional
	@Override
	public SectionEntity modify(SectionDTO sDTO) {
		SectionEntity entity = null;
		try {
			entity = sectionRepo.findById((Long) sDTO.getId()).get();
			entity = (SectionEntity) sectionMapperNormal.dtoToEntity(sDTO, entity);
			entity =  sectionRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
