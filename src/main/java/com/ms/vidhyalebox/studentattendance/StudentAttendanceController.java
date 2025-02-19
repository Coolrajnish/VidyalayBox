package com.ms.vidhyalebox.studentattendance;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/studentattendance")
public class StudentAttendanceController extends GenericController<StudentAttendanceDTO, Long> {

	private final StudentAttendanceserviceImpl _attendanceService;

	public StudentAttendanceController(StudentAttendanceserviceImpl attendanceService) {
		_attendanceService = attendanceService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return _attendanceService;
	}

	@GetMapping("/pagination")
	public ResponseEntity<APiResponse<List<StudentAttendanceEntity>>> filterStream(@RequestParam String orgId,
			@RequestParam(defaultValue = "") String searchText, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		Page<StudentAttendanceEntity> val = _attendanceService.search(orgId, searchText, page, size, sortBy, sortOrder);
		return ResponseEntity.ok(new APiResponse<>("success", "Data fetched successfully",
				_attendanceService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
				Map.of("currentPage", val.getNumber(), "totalPages", val.getTotalPages(), "totalItems",
						val.getTotalElements())));
	}
	
	@PostMapping("/save")
    public ResponseEntity<APiResponse<StudentAttendanceEntity>> filterStreamval(@RequestBody StudentAttendanceDTO dto){

    	 StudentAttendanceEntity entity = _attendanceService.save(dto);
    	 
   	 return ResponseEntity.ok(
   			 new APiResponse<>(
                        "success" ,
                        "Data saved successfully" ,
                        entity,
                        null));
    }
}
