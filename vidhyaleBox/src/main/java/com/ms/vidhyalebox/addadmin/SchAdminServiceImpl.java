package com.ms.vidhyalebox.addadmin;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.repo.GenericRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
