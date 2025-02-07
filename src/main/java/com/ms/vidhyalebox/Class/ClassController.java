package com.ms.vidhyalebox.Class;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.ClassDTO.ClassDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/class")
public class ClassController extends GenericController<ClassDTO, Long> {

    private final ClassServiceImpl _classService;

    public ClassController(final ClassServiceImpl classService) {
        _classService = classService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _classService;
    }
}
