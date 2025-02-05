package com.ms.vidhyalebox.student;

import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.auth.stream.StreamDTO;
import com.ms.shared.api.auth.studentDTO.StudentDTO;
import com.ms.shared.api.auth.studentDTO.StudentTransferDTO;
import com.ms.shared.api.generic.APiResponse;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.generic.ModalDTO;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import com.ms.vidhyalebox.emailsender.EmailDetails;
import com.ms.vidhyalebox.parent.ParentEntity;
import com.ms.vidhyalebox.stream.StreamserviceImpl;
import com.ms.vidhyalebox.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

        boolean update =  _studentService.transferStudent(studentTransferDTO);

        if(!update){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new APiResponse<>( "error",
                    "Failed to transfer/promote student" ,
                    null,
                    null));
        }

        return ResponseEntity.ok(new APiResponse<>( "success",
                "Student transfered/promoted successfully",
                null,
                null
        ));
    }

    @PostMapping(path = "/addstudent", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<APiResponse<Object>> addStudent(@RequestPart("studentDTO") StudentDTO studentDTO, @RequestParam("image") MultipartFile image) {

        _studentService.addStudent(studentDTO, image);

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
