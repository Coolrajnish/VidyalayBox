package com.ms.vidhyalebox.feesdiscount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesDiscountServiceImpl extends GenericService<GenericEntity, Long> implements FeesDiscountService {

	@Autowired
	FeesDiscountMapperNormal feesDiscountMapperNormal;

	@Autowired
	FeesDiscountRepo feesDiscountRepo;

	@Override
	public JpaRepository getRepo() {
		return feesDiscountRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return feesDiscountMapperNormal;
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
	public FeesDiscountEntity save(FeesDiscountDTO dto) {

		FeesDiscountEntity entity = (FeesDiscountEntity) feesDiscountMapperNormal.dtoToEntity(dto);
		entity = feesDiscountRepo.save(entity);

		return entity;

	}

}
