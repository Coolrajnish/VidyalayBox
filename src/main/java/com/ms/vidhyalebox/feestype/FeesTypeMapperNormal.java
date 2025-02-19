package com.ms.vidhyalebox.feestype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassDTO;
import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesTypeMapperNormal implements IMapperNormal {
	@Autowired
    IOrgClientRepo orgRepo;

    

    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        FeesTypeEntity entity = genericEntity == null ? new FeesTypeEntity() : (FeesTypeEntity) genericEntity;

        FeesTypeDTO feeDTO = (FeesTypeDTO) genericDto;
        entity.setFeeDescr(feeDTO.getFeesDescr());
        entity.setFeeName(feeDTO.getFeesName());
        entity.setSchool(orgRepo.findByOrgUniqId(feeDTO.getOrgUniqId()).get());
        
        return entity;
    }
    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        

        return null;
    }
    }
