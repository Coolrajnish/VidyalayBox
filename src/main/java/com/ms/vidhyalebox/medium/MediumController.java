package com.ms.vidhyalebox.medium;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

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

    @GetMapping("/pagination")
    public ResponseEntity<APiResponse<List<MediumEntity>>> filterMedium(
            @RequestParam String orgId,
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "medium_name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {

        Page<MediumEntity> val  =  _mediumService.search(orgId, searchText, page, size, sortBy, sortOrder);
        return ResponseEntity.ok(
                new APiResponse<>(
                        "success" ,
                        "Data fetched successfully" ,
                        _mediumService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
                        Map.of(
                                "currentPage", val.getNumber(),
                                "totalPages", val.getTotalPages(),
                                "totalItems", val.getTotalElements()
                        )));
    }
}
