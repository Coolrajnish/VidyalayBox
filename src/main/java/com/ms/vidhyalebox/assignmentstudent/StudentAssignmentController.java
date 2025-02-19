package com.ms.vidhyalebox.assignmentstudent;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.medium.MediumDTO;
import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.teacher.TeacherDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;


@RestController
@Validated
@RequestMapping("/studentassignment")
public class StudentAssignmentController extends GenericController<StudentAssignmentDTO, Long> {

	private final StudentAssignmentserviceImpl _assignmentService;

	public StudentAssignmentController(StudentAssignmentserviceImpl assignmentService) {
		_assignmentService = assignmentService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _assignmentService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<StudentAssignmentEntity>>> filterStream(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		Page<StudentAssignmentEntity> val = _assignmentService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				_assignmentService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));
	}
//	
//	@PostMapping(path = "/save", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<APiResponse<Object>> save(@RequestPart("sassignmentDTO") StudentAssignmentDTO sassignmentDTO,
//			                       @RequestParam("image") List<MultipartFile> image){
//		try {
//			sassignmentDTO.setFiles(image);
//			_assignmentService.save(sassignmentDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO Auto-generated catch block
//			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(new APiResponse<>("error", "Data save failed - " + e.getLocalizedMessage(), null,
//							null));
//		}
//		
//		return ResponseEntity.ok(new APiResponse<>("success", "Data saved successfully",
//				null, null));
//	}
//	
//	@PatchMapping(path = "/modify/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<APiResponse<Object>> modify(@PathVariable Long id, @RequestPart("sassignmentDTO") StudentAssignmentDTO sassignmentDTO,
//			                       @RequestParam("image") MultipartFile image){
//
//		try {
//			sassignmentDTO.setId(id);
//			_assignmentService.modify(sassignmentDTO, image);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO Auto-generated catch block
//			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(new APiResponse<>("error", "Data modification failed - " + e.getLocalizedMessage(), null,
//							null));
//		}
//		
//		return ResponseEntity.ok(new APiResponse<>("success", "Data modified successfully",
//				null, null));
//	}
}
