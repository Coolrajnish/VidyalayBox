package com.ms.vidhyalebox.session;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.sessionDTO.SessionDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/session")
public class SessionController extends GenericController<SessionDTO, Long> {

    private final SessionServiceImpl _sessionService;


    public SessionController(SessionServiceImpl sessionService) {
        _sessionService = sessionService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _sessionService;
    }
}
