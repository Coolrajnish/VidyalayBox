package com.ms.vidhyalebox.classsubject;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.subject.SubjectRepo;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

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
    public ResponseEntity<APiResponse<Object>> registerOrg(@Valid @RequestBody ClassSubjectDTO classSubjectDTO) {

        return _classSubjectService.MapClassSubject(classSubjectDTO);
    }
}
