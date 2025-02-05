package com.ms.vidhyalebox.medium;

import com.ms.shared.api.auth.mediumDTO.MediumDTO;
import com.ms.shared.api.generic.APiResponse;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
