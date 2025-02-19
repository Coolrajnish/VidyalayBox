package com.ms.vidhyalebox.fees;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/fees")
public class FeesController extends GenericController<FeesDTO, Long> {

	@Autowired
	FeesService _feesService;

	@Autowired
	FeesMapperNormal mapper;

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _feesService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<FeesEntity>>> filterClass(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {

		Page<FeesEntity> val = _feesService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				_feesService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));

	}

	@PostMapping("/save")
	public ResponseEntity<APiResponse<FeesDTO>> filterStreamval(@RequestBody FeesDTO dto) {

		FeesEntity entity = _feesService.save(dto);
		FeesDTO fdto = (FeesDTO) mapper.entityToDto(entity);

		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully", fdto, null));
	}

	@PatchMapping(path = "/modify/{id}")
	public ResponseEntity<APiResponse<Object>> modifyTeacher(@PathVariable Long id,
			@RequestPart("feesDTO") FeesDTO feesDTO) {

		try {
			feesDTO.setId(id);
			FeesEntity entity = _feesService.modify(feesDTO);
			feesDTO = (FeesDTO) mapper.entityToDto(entity);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new APiResponse<>("error", "Fees modification failed - " + e.getLocalizedMessage(), feesDTO, null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Fees modified successfully", feesDTO, null));
	}
}
