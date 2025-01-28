package com.ms.vidhyalebox.medium;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MediumServiceImpl extends GenericService<GenericEntity, Long> implements  MediumService{

    private final MediumMapper mediumMapper;

    private final MediumRepo mediumRepo;

    public MediumServiceImpl(MediumMapper mediumMapper, MediumRepo mediumRepo) {
        this.mediumMapper = mediumMapper;
        this.mediumRepo = mediumRepo;
    }


    @Override
    public JpaRepository getRepo() {
        return mediumRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return mediumMapper;
    }
}
