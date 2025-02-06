package com.ms.vidhyalebox.section;

import com.ms.shared.api.auth.sectionDTO.SectionDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionMapperNormal implements IMapperNormal {
    private IOrgClientRepo orgRepo;

    @Autowired
    public SectionMapperNormal (IOrgClientRepo orgRepo){
        this.orgRepo = orgRepo;
    }


    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        SectionEntity entity = genericEntity == null ? new SectionEntity() : (SectionEntity) genericEntity;

        SectionDTO sectionDTO = (SectionDTO) genericDto;
        entity.setSchool(orgRepo.findByOrgUniqId(  sectionDTO.getOrgUniqId()).get());
        entity.setSectionName(sectionDTO.getSectionName());
        entity.setActive(sectionDTO.isActive());

        return entity;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        SectionEntity entity = (SectionEntity) genericEntity;

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
        sectionDTO.setSectionName(entity.getSectionName());
        sectionDTO.setActive(entity.isActive());
        return sectionDTO;
    }

    }
