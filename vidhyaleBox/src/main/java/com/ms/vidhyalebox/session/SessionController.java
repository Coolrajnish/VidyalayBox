package com.ms.vidhyalebox.session;

import com.ms.shared.api.auth.sessionDTO.SessionDTO;
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
