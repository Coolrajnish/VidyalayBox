package com.ms.vidhyalebox.student;

import com.ms.shared.api.auth.stream.StreamDTO;
import com.ms.shared.api.auth.studentDTO.StudentDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import com.ms.vidhyalebox.stream.StreamserviceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/student")
public class StudentController extends GenericController<StudentDTO, Long> {

    private final StudentServiceImpl _studentService;

    public StudentController(StudentServiceImpl studentService) {
        _studentService = studentService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _studentService;
    }
}
