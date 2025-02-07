package com.ms.vidhyalebox.addadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SchAdminServiceImpl extends GenericService<GenericEntity, Long> implements SchAdminService{

    private final SchAdminRepo schAdminRepo;

    private final SchAdminMapper schAdminMapper;

    public SchAdminServiceImpl(SchAdminRepo schAdminRepo, SchAdminMapper schAdminMapper) {
        this.schAdminRepo = schAdminRepo;
        this.schAdminMapper = schAdminMapper;
    }

    @Override
    public JpaRepository getRepo() {
        return schAdminRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return schAdminMapper;
    }
}
