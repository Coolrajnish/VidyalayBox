package com.ms.vidhyalebox.classsubject;

import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.stereotype.Service;

@Service
public class ClassSubjectMapperNormal implements IMapperNormal {

    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
//        SubjectEntity entity = genericEntity == null ? new SubjectEntity() : (SubjectEntity) genericEntity;
//
//        SubjectDTO subjectDTO = (SubjectDTO) genericDto;
//        entity.setOrgUniqId(subjectDTO.getOrgUniqId());
//        entity.setSubjectName(subjectDTO.getSubjectName());
//        entity.setSubjectCode(subjectDTO.getSubjectCode());
//        entity.setMedium(subjectDTO.getMedium());
//        entity.setSubjectType(subjectDTO.getSubjectType());
//        entity.setActive(subjectDTO.isActive());

        return null;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
//        SubjectEntity entity = (SubjectEntity) genericEntity;
//
//        SubjectDTO subjectDTO = new SubjectDTO();
//        subjectDTO.setOrgUniqId(entity.getOrgUniqId());
//        subjectDTO.setSubjectName(entity.getSubjectName());
//        subjectDTO.setSubjectCode(entity.getSubjectCode());
//        subjectDTO.setMedium(entity.getMedium());
//        subjectDTO.setSubjectType(entity.getSubjectType());
//        subjectDTO.setActive(entity.isActive());

        return null;
    }
}
