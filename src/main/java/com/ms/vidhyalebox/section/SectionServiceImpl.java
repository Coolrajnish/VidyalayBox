package com.ms.vidhyalebox.section;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends GenericService<GenericEntity, Long> implements SectionService{

    private  final SectionRepo sectionRepo;
    private final SectionMapperNormal sectionMapperNormal;

    public SectionServiceImpl(SectionRepo sectionRepo, SectionMapperNormal sectionMapperNormal) {
        this.sectionRepo = sectionRepo;
        this.sectionMapperNormal = sectionMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return sectionRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return sectionMapperNormal;
    }
}
