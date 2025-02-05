package com.ms.vidhyalebox.section;

import com.ms.shared.api.auth.sectionDTO.SectionDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pagination")
    public Page<SectionEntity> filterSection(
            @RequestParam String orgId,
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "medium_name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {

        return _sectionService.search(orgId, searchText, page, size, sortBy, sortOrder);
    }
}
