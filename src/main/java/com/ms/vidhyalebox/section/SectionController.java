package com.ms.vidhyalebox.section;

import com.ms.shared.api.auth.sectionDTO.SectionDTO;
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
@RequestMapping("/section")
public class SectionController extends GenericController<SectionDTO, Long> {

    private final SectionServiceImpl _sectionService;

    public SectionController(final SectionServiceImpl shiftService) {
        _sectionService = shiftService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _sectionService;
    }
}
