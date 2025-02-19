package com.ms.vidhyalebox.feesinstalment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesInstallmentServiceImpl extends GenericService<GenericEntity, Long> implements FeesInstallmentService {

	@Autowired
	FeesInstallmentMapperNormal feesInstallmentMapperNormal;

	@Autowired
	FeesInstallmentRepo feesInstallmentRepo;

	@Override
	public JpaRepository getRepo() {
		return feesInstallmentRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return feesInstallmentMapperNormal;
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
	public FeesInstallmentEntity save(FeesInstallmentDTO dto) {

		FeesInstallmentEntity entity = (FeesInstallmentEntity) feesInstallmentMapperNormal.dtoToEntity(dto);
		entity = feesInstallmentRepo.save(entity);

		return entity;

	}

}
