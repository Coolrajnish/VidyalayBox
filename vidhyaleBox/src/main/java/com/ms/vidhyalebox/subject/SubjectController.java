package com.ms.vidhyalebox.subject;

import com.ms.shared.api.auth.subject.SubjectDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
