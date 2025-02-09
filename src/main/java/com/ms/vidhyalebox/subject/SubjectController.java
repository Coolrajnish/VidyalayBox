package com.ms.vidhyalebox.subject;

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
    @GetMapping("/pagination")
    public ResponseEntity<APiResponse<List<SubjectEntity>>> filterSubject(
    	       @RequestParam String orgId,
               @RequestParam(defaultValue = "") String searchText,
               @RequestParam(defaultValue = "0") int page,
               @RequestParam(defaultValue = "10") int size,
               @RequestParam(defaultValue = "subject_name") String sortBy,
               @RequestParam(defaultValue = "asc") String sortOrder
       ){
    	Page<SubjectEntity> val =  _subjectService.search(orgId, searchText, page, size, sortBy, sortOrder);
   	 return ResponseEntity.ok(
   			 new APiResponse<>(
                        "success" ,
                        "Data fetched successfully" ,
                        _subjectService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
                        Map.of( "currentPage", val.getNumber(),
                                "totalPages", val.getTotalPages(),
                                "totalItems", val.getTotalElements()
                                )));
    }

}
