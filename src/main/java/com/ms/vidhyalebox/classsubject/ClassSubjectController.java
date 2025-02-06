package com.ms.vidhyalebox.classsubject;

import com.ms.shared.api.auth.classsubjectDTO.ClassSubjectDTO;
import com.ms.shared.api.auth.classsubjectDTO.ElectSubDTO;
import com.ms.shared.api.auth.studentDTO.StudentTransferDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.subject.SubjectRepo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/classsubject")
public class ClassSubjectController extends GenericController<ClassSubjectDTO, Long> {

    private final ClassSubjectServiceImpl _classSubjectService;
    private final ClassSubjectRepo _classClassSubjectRepo;
    private final SubjectRepo _SubjectRepo;

    public ClassSubjectController(ClassSubjectServiceImpl classSubjectService, ClassSubjectRepo classClassSubjectRepo, SubjectRepo subjectRepo) {
        _classSubjectService = classSubjectService;
        _classClassSubjectRepo = classClassSubjectRepo;
        _SubjectRepo = subjectRepo;
    }


    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _classSubjectService;
    }


    @PostMapping("/mapclasssub")
    public GenericResponse registerOrg(@Valid @RequestBody ClassSubjectDTO classSubjectDTO) {

        GenericResponse genericResponse =   _classSubjectService.MapClassSubject(classSubjectDTO);
        return genericResponse;
    }
}
