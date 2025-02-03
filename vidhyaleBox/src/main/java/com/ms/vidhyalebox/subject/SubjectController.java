package com.ms.vidhyalebox.subject;

import com.ms.shared.api.auth.studentDTO.StudentTransferDTO;
import com.ms.shared.api.auth.subject.SubjectDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/subject")
public class SubjectController extends GenericController<SubjectDTO, Long> {

    private final SubjectServiceImpl _subjectService;

    public SubjectController(SubjectServiceImpl subjectService) {
        _subjectService = subjectService;
    }


    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _subjectService;
    }

}
