package com.ms.vidhyalebox.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SalaryserviceImpl extends GenericService<GenericEntity, Long> implements SalaryService {

	private final SalaryRepo salaryRepo;
	private final SalaryMapperNormal salaryMapperNormal;

	public SalaryserviceImpl(SalaryRepo salaryRepo, SalaryMapperNormal salaryMapperNormal) {
		this.salaryRepo = salaryRepo;
		this.salaryMapperNormal = salaryMapperNormal;
	}

	@Override
	public JpaRepository getRepo() {
		return salaryRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return salaryMapperNormal;
	}
}
