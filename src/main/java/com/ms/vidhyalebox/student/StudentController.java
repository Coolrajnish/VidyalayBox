package com.ms.vidhyalebox.student;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/student")
public class StudentController extends GenericController<StudentDTO, Long> {

	@Value("${image.storage.path}")
	private String storagePath; 
	
    private final StudentServiceImpl _studentService;
    private final IUserRepo userRepo;

    public StudentController(StudentServiceImpl studentService, IUserRepo userRepo) {
        _studentService = studentService;
		this.userRepo = userRepo;
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

    @GetMapping("/getimage")
    public ResponseEntity<Resource> getImage(@RequestParam String userId) {
        try {
        	Optional<UserEntity> imageEntityOptional = userRepo.findById(Long.valueOf( userId));
        	Resource resource = null;
        	Path imagePath = null;
    		if (imageEntityOptional.isPresent()) {
    			 imagePath = Paths.get(imageEntityOptional.get().getImage());
    			resource = new FileSystemResource(imagePath);
    		} else {
    			throw new FileNotFoundException("Image not found");
    		}
            if (!resource.exists()) {
                throw new FileNotFoundException("Image not found");
            }

            String contentType = Files.probeContentType(imagePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _studentService;
    }
    @GetMapping("/pagination")
    public ResponseEntity<APiResponse<List<StudentEntity>>> filterStudent(
            @RequestParam String orgId,
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "student_name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){

        Page<StudentEntity> val  =  _studentService.search(orgId, searchText, page, size, sortBy, sortOrder);
        return ResponseEntity.ok(
                new APiResponse<>(
                        "success" ,
                        "Data fetched successfully" ,
                        _studentService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
                        Map.of(
                                "currentPage", val.getNumber(),
                                "totalPages", val.getTotalPages(),
                                "totalItems", val.getTotalElements()
                        )));
    }
}
