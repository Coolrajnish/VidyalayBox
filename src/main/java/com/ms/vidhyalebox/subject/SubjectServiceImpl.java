package com.ms.vidhyalebox.subject;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends GenericService<GenericEntity, Long> implements SubjectService{

    private final SubjectRepo subjectRepo;
    private final SubjectMapperNormal subjectMapperNormal;

    public SubjectServiceImpl(SubjectRepo subjectRepo, SubjectMapperNormal subjectMapperNormal) {
        this.subjectRepo = subjectRepo;
        this.subjectMapperNormal = subjectMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return subjectRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return subjectMapperNormal;
    }
}
