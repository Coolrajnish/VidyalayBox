package com.ms.vidhyalebox.payrollSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

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
}
