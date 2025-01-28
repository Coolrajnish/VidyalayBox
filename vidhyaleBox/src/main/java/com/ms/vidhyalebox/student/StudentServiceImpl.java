package com.ms.vidhyalebox.student;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends GenericService<GenericEntity, Long> implements StudentService {

    private final StudentMapperNormal studentMapperNormal;

    public StudentServiceImpl(StudentMapperNormal studentMapperNormal, StudentRepo studentRepo) {
        this.studentMapperNormal = studentMapperNormal;
        this.studentRepo = studentRepo;
    }

    private final StudentRepo studentRepo;

    @Override
    public JpaRepository getRepo() {
        return studentRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return studentMapperNormal;
    }
}
