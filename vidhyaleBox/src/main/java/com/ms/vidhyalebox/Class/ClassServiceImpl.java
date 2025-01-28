package com.ms.vidhyalebox.Class;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends GenericService<GenericEntity, Long> implements ClassService{

    private final ClassMapperNormal classMapperNormal;

    private  final  ClassRepo classRepo;

    public ClassServiceImpl(ClassMapperNormal classMapperNormal, ClassRepo classRepo) {
        this.classMapperNormal = classMapperNormal;
        this.classRepo = classRepo;
    }

    @Override
    public JpaRepository getRepo() {
        return classRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return classMapperNormal;
    }
}
