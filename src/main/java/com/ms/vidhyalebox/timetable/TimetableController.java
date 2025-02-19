package com.ms.vidhyalebox.timetable;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;


@RestController
@Validated
@RequestMapping("/timetable")
public class TimetableController extends GenericController<TimetableDTO, Long> {

	private final TimetableserviceImpl timetableService;

	public TimetableController(TimetableserviceImpl timetableService) {
		this.timetableService = timetableService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return timetableService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<TimetableEntity>>> filterStream(@RequestParam String orgId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder) {
		Page<TimetableEntity> val = timetableService.search(orgId, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				timetableService.search(orgId, page, size, sortBy, sortOrder).getContent(), Map.of("currentPage",
						val.getNumber(), "totalPages", val.getTotalPages(), "totalItems", val.getTotalElements())));
	}
}
