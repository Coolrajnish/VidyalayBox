package com.ms.vidhyalebox.Class;

import com.ms.shared.api.auth.ClassDTO.ClassDTO;
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
