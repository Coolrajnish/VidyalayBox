package com.ms.vidhyalebox.timetable;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.Class.ClassDTO;
import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.section.SectionDTO;
import com.ms.vidhyalebox.section.SectionEntity;
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
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<APiResponse<Object>> delete(@PathVariable Long id) {
		  try {
			timetableService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APiResponse<>("error",
					"Data deletion failed - " + e.getLocalizedMessage(), null, null));
		}
		
		return ResponseEntity.ok(new APiResponse<>("success", "Data deleted successfully", null, null));
	}
	
	@PostMapping("/save")
	public ResponseEntity<APiResponse<TimetableEntity>> save(@RequestBody TimetableDTO dto) {

		TimetableEntity entity = timetableService.save(dto);

		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully", entity, null));
	}
	
	
	@PatchMapping(path = "/modify/{id}")
	public ResponseEntity<APiResponse<Object>> modify(@PathVariable Long id, @RequestBody TimetableDTO cDTO) {
		TimetableEntity entity = null;
		try {
			cDTO.setId(id);
			entity = timetableService.modify(cDTO);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APiResponse<>("error",
					"Data modification failed - " + e.getLocalizedMessage(), entity, null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Data modified successfully", entity, null));
	}
}
