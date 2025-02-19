package com.ms.vidhyalebox.feespayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@Validated
@RequestMapping("/feespayment")
public class FeesPaymentController extends GenericController<FeesPaymentDTO, Long> {

	@Autowired
	FeesPaymentService _feesService;

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _feesService;
	}

//	@GetMapping("/pagination")
//	public ResponseEntity<APiResponse<List<ClassEntity>>> filterClass(@RequestParam String orgId,
//			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
//			@RequestParam(defaultValue = "asc") String sortOrder) {
//
//		Page<ClassEntity> val = _classService.search(orgId, searchText, page, size, sortBy, sortOrder);
//		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
//				_classService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
//				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
//						val.getTotalElements())));
//
//	}

	@PostMapping("/save")
	public ResponseEntity<APiResponse<FeesPaymentEntity>> filterStreamval(@RequestBody FeesPaymentDTO dto) {

		FeesPaymentEntity entity = _feesService.save(dto);

		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully", entity, null));
	}
}
