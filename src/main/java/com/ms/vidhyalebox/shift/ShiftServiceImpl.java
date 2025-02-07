package com.ms.vidhyalebox.shift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ShiftServiceImpl extends GenericService<GenericEntity, Long> implements ShiftService{

    private final ShiftMapperNormal shiftMapperNormal;

    public ShiftServiceImpl(ShiftMapperNormal shiftMapperNormal, ShiftRepo shiftRepo) {
        this.shiftMapperNormal = shiftMapperNormal;
        this.shiftRepo = shiftRepo;
    }

    private final ShiftRepo shiftRepo;

    @Override
    public JpaRepository getRepo() {
        return shiftRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return shiftMapperNormal;
    }
}
