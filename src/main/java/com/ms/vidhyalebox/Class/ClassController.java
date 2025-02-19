package com.ms.vidhyalebox.Class;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@Validated
@RequestMapping("/class")
public class ClassController extends GenericController<ClassDTO, Long> {

	private final ClassServiceImpl _classService;

	public ClassController(final ClassServiceImpl classService) {
		_classService = classService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _classService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<ClassEntity>>> filterClass(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {

		Page<ClassEntity> val = _classService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				_classService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));

	}

	@PostMapping("/save")
	public ResponseEntity<APiResponse<ClassEntity>> filterStreamval(@RequestBody ClassDTO dto) {

		ClassEntity entity = _classService.save(dto);

		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully", entity, null));
	}

	@PatchMapping(path = "/modify/{id}")
	public ResponseEntity<APiResponse<Object>> modify(@PathVariable Long id, @RequestBody ClassDTO cDTO) {
		ClassEntity entity = null;
		try {
			cDTO.setId(id);
			entity = _classService.modify(cDTO);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APiResponse<>("error",
					"Data modification failed - " + e.getLocalizedMessage(), entity, null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Data modified successfully", entity, null));
	}
}
