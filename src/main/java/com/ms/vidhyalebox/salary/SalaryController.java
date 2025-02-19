package com.ms.vidhyalebox.salary;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;


@RestController
@Validated
@RequestMapping("/salary")
public class SalaryController extends GenericController<SalaryDTOs, Long> {

	private final SalaryserviceImpl _salaryService;

	public SalaryController(SalaryserviceImpl salaryService) {
		_salaryService = salaryService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _salaryService;
	}
}
