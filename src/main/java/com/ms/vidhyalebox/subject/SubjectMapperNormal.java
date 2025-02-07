package com.ms.vidhyalebox.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.sharedapi.subject.SubjectDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SubjectMapperNormal implements IMapperNormal {

    private IOrgClientRepo orgRepo;

    @Autowired
    private SubjectMapperNormal (IOrgClientRepo orgRepo){
        this.orgRepo = orgRepo;
    }

    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        SubjectEntity entity = genericEntity == null ? new SubjectEntity() : (SubjectEntity) genericEntity;

        SubjectDTO subjectDTO = (SubjectDTO) genericDto;
        entity.setSchool( orgRepo.findByOrgUniqId( subjectDTO.getOrgUniqId()).get());
        entity.setSubjectName(subjectDTO.getSubjectName());
        entity.setSubjectCode(subjectDTO.getSubjectCode());
        entity.setMedium(subjectDTO.getMedium());
        entity.setSubjectType(subjectDTO.getSubjectType());
       // entity.setActive(subjectDTO.isActive());

        return entity;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        SubjectEntity entity = (SubjectEntity) genericEntity;

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setOrgUniqId( entity.getSchool().getOrgUniqId());
        subjectDTO.setSubjectName(entity.getSubjectName());
        subjectDTO.setSubjectCode(entity.getSubjectCode());
        subjectDTO.setMedium(entity.getMedium());
        subjectDTO.setSubjectType(entity.getSubjectType());
      //  subjectDTO.setActive(entity.getSchool().isActive());

        return subjectDTO;
    }
}
