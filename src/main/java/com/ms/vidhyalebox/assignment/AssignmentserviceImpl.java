package com.ms.vidhyalebox.assignment;

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
public class AssignmentserviceImpl extends GenericService<GenericEntity, Long> implements AssignmentService {

	private final AssignmentRepo assignmentCRepo;
	private final AssignmentMapperNormal assignmentCMapperNormal;

	public AssignmentserviceImpl(AssignmentRepo assignmentCRepo, AssignmentMapperNormal assignmentCMapperNormal) {
		this.assignmentCRepo = assignmentCRepo;
		this.assignmentCMapperNormal = assignmentCMapperNormal;
	}

	@Override
	public JpaRepository getRepo() {
		return assignmentCRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return assignmentCMapperNormal;
	}

	@Transactional
	@Override
	public Page<AssignmentEntity> search(String orgId, String searchText, int page, int size, String sortBy,
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
			return assignmentCRepo.search(orgId, searchText, pageable);
		} else {
			return assignmentCRepo.findAll(pageable);
		}
	}

	@Transactional
	@Override
	public AssignmentEntity save(AssignmentDTO dto) {

		AssignmentEntity entity = (AssignmentEntity) assignmentCMapperNormal.dtoToEntity(dto);
		entity = assignmentCRepo.save(entity);

		return entity;

	}

	@Transactional
	@Override
	public AssignmentEntity modify(AssignmentDTO assignDTO) {
		AssignmentEntity entity = null;
		try {
			entity = assignmentCRepo.findById((Long) assignDTO.getId()).get();
			entity = (AssignmentEntity) assignmentCMapperNormal.dtoToEntity(assignDTO, entity);
			entity = assignmentCRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
