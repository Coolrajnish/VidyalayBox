package com.ms.vidhyalebox.stream;

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
@RequestMapping("/stream")
public class StreamController extends GenericController<StreamDTO, Long> {

    private final  StreamserviceImpl _streamService;

    public StreamController(StreamserviceImpl streamService) {
        _streamService = streamService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _streamService;
    }
    @GetMapping("/pagination")
    public ResponseEntity<APiResponse<List<StreamEntity>>> filterStream(
    	       @RequestParam String orgId,
               @RequestParam(defaultValue = "") String searchText,
               @RequestParam(defaultValue = "0") int page,
               @RequestParam(defaultValue = "10") int size,
               @RequestParam(defaultValue = "id") String sortBy,
               @RequestParam(defaultValue = "asc") String sortOrder
       ){
    	Page<StreamEntity> val = _streamService.search(orgId, searchText, page, size, sortBy, sortOrder);
   	 return ResponseEntity.ok(
   			 new APiResponse<>(
                        "success" ,
                        "Data fetched successfully" ,
                        _streamService.search(orgId, searchText, page, size, sortBy, sortOrder).getContent(),
                        Map.of( "currentPage", val.getNumber(),
                                "totalPages", val.getTotalPages(),
                                "totalItems", val.getTotalElements()
                                )));
    }
}
