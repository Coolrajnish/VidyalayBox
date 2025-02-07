package com.ms.vidhyalebox.payrollSettings;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/payroll")
public class PayrollController extends GenericController<PayrollDTOs, Long> {

	private final PayrollserviceImpl _payrollService;

	public PayrollController(PayrollserviceImpl payrollService) {
		_payrollService = payrollService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _payrollService;
	}
}
