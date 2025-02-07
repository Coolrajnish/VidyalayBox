package com.ms.vidhyalebox.student;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.sharedapi.studentDTO.StudentDTO;
import com.ms.vidhyalebox.sharedapi.studentDTO.StudentTransferDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/student")
public class StudentController extends GenericController<StudentDTO, Long> {

    private final StudentServiceImpl _studentService;

    public StudentController(StudentServiceImpl studentService) {
        _studentService = studentService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<APiResponse<Object>> registerOrg(@Valid @RequestBody List<StudentTransferDTO> studentTransferDTO) {
        try {
            boolean update = _studentService.transferStudent(studentTransferDTO);

            if (!update) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(new APiResponse<>("error", "Failed to transfer/promote student", null, null));
            }

            return ResponseEntity.ok(new APiResponse<>("success", "Student transferred/promoted successfully", null, null));
        } catch (Exception e) {
            // logger.error("Error occurred while transferring students", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new APiResponse<>("error", "Internal server error: " + e.getMessage(), null, null));
        }
    }

    @PostMapping(path = "/addstudent", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<APiResponse<Object>> addStudent(@RequestPart("studentDTO") StudentDTO studentDTO, @RequestParam("image") MultipartFile image) {

        try {
			_studentService.addStudent(studentDTO, image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APiResponse<>(
	                "error",
	                "Student registered failed - "+e.getLocalizedMessage(),
	                Map.of("stufName" , studentDTO.getFirstName(),
	                        "stulName" , studentDTO.getLastName()),
	                null
	        ));
		}

        return ResponseEntity.ok(new APiResponse<>(
                "success",
                "Student registered successfully",
                Map.of("stufName" , studentDTO.getFirstName(),
                        "stulName" , studentDTO.getLastName()),
                null
        ));
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _studentService;
    }
}
