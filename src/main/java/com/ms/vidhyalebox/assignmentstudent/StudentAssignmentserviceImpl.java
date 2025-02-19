package com.ms.vidhyalebox.assignmentstudent;

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
public class StudentAssignmentserviceImpl extends GenericService<GenericEntity, Long>
		implements StudentAssignmentService {

	private final StudentAssignmentRepo studentAssignmentCRepo;
	private final StudentAssignmentMapperNormal studentAssignmentCMapperNormal;

	public StudentAssignmentserviceImpl(StudentAssignmentRepo studentAssignmentCRepo,
			StudentAssignmentMapperNormal studentAssignmentCMapperNormal) {
		this.studentAssignmentCRepo = studentAssignmentCRepo;
		this.studentAssignmentCMapperNormal = studentAssignmentCMapperNormal;
	}

	@Override
	public JpaRepository getRepo() {
		return studentAssignmentCRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return studentAssignmentCMapperNormal;
	}

	@Transactional
	@Override
	public Page<StudentAssignmentEntity> search(String orgId, String searchText, int page, int size, String sortBy,
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
			return studentAssignmentCRepo.search(orgId, searchText, pageable);
		} else {
			return studentAssignmentCRepo.findAll(pageable);
		}
	}

	@Transactional
	@Override
	public StudentAssignmentEntity save(StudentAssignmentDTO dto) {

		StudentAssignmentEntity entity = (StudentAssignmentEntity) studentAssignmentCMapperNormal.dtoToEntity(dto);
		entity = studentAssignmentCRepo.save(entity);

		return entity;
	}

	@Transactional
	@Override
	public StudentAssignmentEntity modify(StudentAssignmentDTO saDTO) {
		StudentAssignmentEntity entity = null;
		try {
			entity = studentAssignmentCRepo.findById((Long) saDTO.getId()).get();
			entity = (StudentAssignmentEntity) studentAssignmentCMapperNormal.dtoToEntity(saDTO, entity);
			entity = studentAssignmentCRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
