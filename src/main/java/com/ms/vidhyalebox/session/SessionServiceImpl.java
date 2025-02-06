package com.ms.vidhyalebox.session;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends GenericService<GenericEntity, Long> implements SessionService {

    private final SessionMapper sessionMapper;

    private final SessionRepo sessionRepo;

    public SessionServiceImpl(SessionMapper sessionMapper, SessionRepo sessionRepo) {
        this.sessionMapper = sessionMapper;
        this.sessionRepo = sessionRepo;
    }


    @Override
    public JpaRepository getRepo() {
        return sessionRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return sessionMapper;
    }
}
