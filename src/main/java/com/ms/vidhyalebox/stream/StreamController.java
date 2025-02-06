package com.ms.vidhyalebox.stream;

import com.ms.shared.api.auth.stream.StreamDTO;
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
}
