package com.ms.vidhyalebox.staff;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@Validated
@RequestMapping("/staff")
public class StaffController extends GenericController<StaffDTO, Long> {

	private final StaffServiceImpl _iStaffService;

	public StaffController(final StaffServiceImpl staffService) {
		_iStaffService = staffService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		_iStaffService.setAuthToken(getAuthToken());
		return _iStaffService;
	}

	@PostMapping(path = "/addstaff", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<APiResponse<Object>> addStaff(@RequestPart("staffDTO") StaffDTO staffDto,
			@RequestParam("image") MultipartFile image) {

		try {
			_iStaffService.addStaff(staffDto, image);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APiResponse<>("error", "Staff regestration failed - " + e.getLocalizedMessage(), Map
							.of("staff FirstName", staffDto.getFirstName(), "staff LastName", staffDto.getLastName()),
							null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Staff registered successfully",
				Map.of("staff FirstName", staffDto.getFirstName(), "staff LastName", staffDto.getLastName()), null));
	}

	@PatchMapping(path = "/modify/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<APiResponse<Object>> modifyStaff(@PathVariable Long id,
			@RequestPart("staffDTO") StaffDTO staffDTO, @RequestParam("image") MultipartFile image) {

		try {
			staffDTO.setId(id);
			_iStaffService.modifyStaff(staffDTO, image);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APiResponse<>("error", "Staff regestration failed - " + e.getLocalizedMessage(), Map
							.of("staff FirstName", staffDTO.getFirstName(), "staff LastName", staffDTO.getLastName()),
							null));
		}

		return ResponseEntity.ok(new APiResponse<>("success", "Staff registered successfully", null, null));
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<StaffEntity>>> filterStaff(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {

		Page<StaffEntity> val = _iStaffService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				_iStaffService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));
	}

}
