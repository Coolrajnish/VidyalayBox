package com.ms.vidhyalebox.payrollSettings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.section.SectionDTO;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.transaction.Transactional;

@Service
public class PayrollserviceImpl extends GenericService<GenericEntity, Long> implements PayrollService {

	private final PayrollRepo payrollRepo;
	private final PayrollMapperNormal payrollMapperNormal;

	public PayrollserviceImpl(PayrollRepo payrollRepo, PayrollMapperNormal payrollMapperNormal) {
		this.payrollRepo = payrollRepo;
		this.payrollMapperNormal = payrollMapperNormal;
	}

	@Override
	public JpaRepository getRepo() {
		return payrollRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return payrollMapperNormal;
	}

	@Override
	public Page<PayrollEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return payrollRepo.search(orgId, searchText, pageable);
		} else {
			return payrollRepo.findAll(pageable);
		}
	}

	@Override
	public PayrollEntity save(SectionDTO dto) {
		PayrollEntity entity = (PayrollEntity) payrollMapperNormal.dtoToEntity(dto);
		entity = payrollRepo.save(entity);

		return entity;
	}
	
	@Transactional
	@Override
	public PayrollEntity modify(PayrollDTOs pDTO) {
		PayrollEntity entity = null;
		try {
			entity = payrollRepo.findById((Long) pDTO.getId()).get();
			entity = (PayrollEntity) payrollMapperNormal.dtoToEntity(pDTO, entity);
			entity =  payrollRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}
