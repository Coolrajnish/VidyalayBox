package com.ms.vidhyalebox.Class;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.medium.MediumDTO;
import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ClassServiceImpl extends GenericService<GenericEntity, Long> implements ClassService {

	private final ClassMapperNormal classMapperNormal;

	private final ClassRepo classRepo;

	public ClassServiceImpl(ClassMapperNormal classMapperNormal, ClassRepo classRepo) {
		this.classMapperNormal = classMapperNormal;
		this.classRepo = classRepo;
	}

	@Override
	public JpaRepository getRepo() {
		return classRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return classMapperNormal;
	}

	@Transactional
	@Override
	public Page<ClassEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return classRepo.search(orgId, searchText, pageable);
		} else {
			return classRepo.findAll(pageable);
		}
	}

	@Transactional
	@Override
	public ClassEntity save(ClassDTO dto) {

		ClassEntity entity = (ClassEntity) classMapperNormal.dtoToEntity(dto);
		entity = classRepo.save(entity);

		return entity;

	}
	
	@Transactional
	@Override
	public ClassEntity modify(ClassDTO classDTO) {
		ClassEntity entity = null;
		try {
			entity = classRepo.findById((Long) classDTO.getId()).get();
			entity = (ClassEntity) classMapperNormal.dtoToEntity(classDTO, entity);
			entity =  classRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
	
}
