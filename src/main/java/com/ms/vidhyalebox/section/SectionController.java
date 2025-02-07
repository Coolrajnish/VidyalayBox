package com.ms.vidhyalebox.section;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.sectionDTO.SectionDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

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
