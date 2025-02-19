package com.ms.vidhyalebox.holiday;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.stream.StreamEntity;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@Validated
@RequestMapping("/holiday")
public class HolidayController extends GenericController<HolidayDTO, Long> {

	private final HolidayserviceImpl holidayService;

	public HolidayController(HolidayserviceImpl expenseService) {
		holidayService = expenseService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return holidayService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<HolidayEntity>>> filterStream(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		Page<HolidayEntity> val = holidayService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				holidayService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));
	}

	@PatchMapping("/modify/{id}")
	public ResponseEntity<HolidayEntity> updateUser(@PathVariable Long id, @RequestBody HolidayDTO holidayDTO) {
		holidayDTO.setId(id);
		HolidayEntity updatedUser = holidayService.updateHolidatFromDTO(holidayDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@PostMapping("/save")
	public ResponseEntity<APiResponse<HolidayEntity>> filterStreamval(@RequestBody HolidayDTO dto) {

		HolidayEntity entity = holidayService.save(dto);

		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully", entity, null));
	}

}
