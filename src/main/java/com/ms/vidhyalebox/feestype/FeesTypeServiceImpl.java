package com.ms.vidhyalebox.feestype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesTypeServiceImpl extends GenericService<GenericEntity, Long> implements FeesTypeService {

	@Autowired
	FeesTypeMapperNormal feesTypeMapperNormal;

	@Autowired
	FeesTypeRepo feesTypeRepo;

	@Override
	public JpaRepository getRepo() {
		return feesTypeRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return feesTypeMapperNormal;
	}

//	@Transactional
//	@Override
//	public Page<ClassEntity> search(String orgId, String searchText, int page, int size, String sortBy,
//			String sortOrder) {
//		Pageable pageable = null;
//		if (sortBy.isEmpty()) {
//			pageable = PageRequest.of(page, size);
//		} else {
//			pageable = PageRequest.of(page, size,
//					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
//		}
//		if (!orgId.isEmpty()) {
//			return classRepo.search(orgId, searchText, pageable);
//		} else {
//			return classRepo.findAll(pageable);
//		}
//	}

	@Override
	public FeesTypeEntity save(FeesTypeDTO dto) {

		FeesTypeEntity entity = (FeesTypeEntity) feesTypeMapperNormal.dtoToEntity(dto);
		entity = feesTypeRepo.save(entity);

		return entity;

	}

}
