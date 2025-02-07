package com.ms.vidhyalebox.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

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
