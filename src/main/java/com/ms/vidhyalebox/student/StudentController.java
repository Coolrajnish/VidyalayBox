package com.ms.vidhyalebox.student;

import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.auth.stream.StreamDTO;
import com.ms.shared.api.auth.studentDTO.StudentDTO;
import com.ms.shared.api.auth.studentDTO.StudentTransferDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.generic.GenericResponse;
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
    public GenericResponse registerOrg(@Valid @RequestBody List<StudentTransferDTO> studentTransferDTO) {

        List<Notification> notifications = new ArrayList<>();
        boolean update =  _studentService.transferStudent(studentTransferDTO);

        if(!update){
            Notification notification = new Notification();
            notification.setNoificationCode("401");
            notification.setNotificationDescription("Failed to transfer/promote student");
            notifications.add(notification);
        }

        if (!notifications.isEmpty()) {
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            genericResponse.setNotifications(notifications);

            return genericResponse;
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
        Notification notification = new Notification();
        notification.setNoificationCode("200");
        notification.setNotificationDescription("Student transfered/promoted successfully");
        notifications.add(notification);

        return genericResponse;
    }

    @PostMapping(path = "/addstudent", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String addStudent(@RequestPart("studentDTO") StudentDTO studentDTO, @RequestParam("image") MultipartFile image) {

        String bool =  _studentService.addStudent(studentDTO, image);

        return bool;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _studentService;
    }
}
