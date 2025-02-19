package com.ms.vidhyalebox.subject;

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
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@Validated
@RequestMapping("/subject")
public class SubjectController extends GenericController<SubjectDTO, Long> {

	private final SubjectService _subjectService;

	public SubjectController(SubjectService subjectService) {
		_subjectService = subjectService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _subjectService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<SubjectEntity>>> filterSubject(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		Page<SubjectEntity> val = _subjectService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				_subjectService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));
	}

	@PostMapping("/save")
	public ResponseEntity<APiResponse<SubjectEntity>> filterStreamval(@RequestBody SubjectDTO dto) {

		SubjectEntity entity = _subjectService.save(dto);

		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully", entity, null));
	}

	@PatchMapping(path = "/modify/{id}")
	public ResponseEntity<APiResponse<Object>> modify(@PathVariable Long id, @RequestBody SubjectDTO saDTO) {
		SubjectEntity entity = null;
		try {
			saDTO.setId(id);
			entity = _subjectService.modify(saDTO);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APiResponse<>("error",
					"Data modification failed - " + e.getLocalizedMessage(), entity, null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Data modified successfully", entity, null));
	}
}
