package com.ms.vidhyalebox.Class;

import com.ms.shared.api.auth.ClassDTO.ClassDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.medium.MediumRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.section.SectionRepo;
import com.ms.vidhyalebox.shift.ShiftRepo;
import com.ms.vidhyalebox.stream.StreamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassMapperNormal implements IMapperNormal {
    private IOrgClientRepo orgRepo;
    private ShiftRepo shiftRepo;
    private SectionRepo sectionRepo;
    private StreamRepo streamRepo;
    private MediumRepo mediumRepo;

    @Autowired
    private ClassMapperNormal (IOrgClientRepo orgRepo, ShiftRepo shiftRepo, SectionRepo sectionRepo, StreamRepo streamRepo,MediumRepo mediumRepo ){
        this.orgRepo = orgRepo;
        this.shiftRepo = shiftRepo;
        this.sectionRepo = sectionRepo;
        this.streamRepo = streamRepo;
        this.mediumRepo = mediumRepo;
    }

    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        ClassEntity entity = genericEntity == null ? new ClassEntity() : (ClassEntity) genericEntity;

        ClassDTO classDTO = (ClassDTO) genericDto;

        entity.setSchool(orgRepo.findByOrgUniqId( classDTO.getOrgUniqueId()).get());
        entity.setClassName(classDTO.getClassName());
        entity.setShiftEntity(shiftRepo.findById(classDTO.getShiftId()).get());
        entity.setSectionEntity(sectionRepo.findById(classDTO.getSectionId()).get());
        entity.setStreamEntity(streamRepo.findById(classDTO.getStreamId()).get());
        entity.setMedium(mediumRepo.findById(classDTO.getMediumId()).get());
        entity.setActive(classDTO.isActive());
        System.out.println("Entity class --->>>"+entity);
        return entity;
    }
    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        ClassEntity entity = (ClassEntity) genericEntity;

        ClassDTO classDTO = new ClassDTO();
        classDTO.setOrgUniqueId(entity.getSchool().getOrgUniqId());
        classDTO.setClassName(entity.getClassName());
        classDTO.setActive(entity.isActive());

        return classDTO;
    }
    }
