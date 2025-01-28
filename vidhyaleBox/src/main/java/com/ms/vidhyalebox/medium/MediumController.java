package com.ms.vidhyalebox.medium;

import com.ms.shared.api.auth.mediumDTO.MediumDTO;
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
@RequestMapping("/medium")
public class MediumController extends GenericController<MediumDTO, Long> {

    private final MediumServiceImpl _mediumService;


    public MediumController(MediumServiceImpl mediumService) {
        _mediumService = mediumService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _mediumService;
    }
}
