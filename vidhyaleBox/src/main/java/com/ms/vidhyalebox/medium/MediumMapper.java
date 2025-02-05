package com.ms.vidhyalebox.medium;

import com.ms.shared.api.auth.mediumDTO.MediumDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumMapper implements IMapperNormal {

    private IOrgClientRepo orgRepo;

    @Autowired
    private MediumMapper (IOrgClientRepo orgRepo){
        this.orgRepo = orgRepo;
    }


    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        MediumEntity entity = genericEntity == null ? new MediumEntity() : (MediumEntity) genericEntity;
        System.out.println("Executed....medium mapper");
        MediumDTO mediumDTO = (MediumDTO) genericDto;
        entity.setSchool(orgRepo.findByOrgUniqId( mediumDTO.getOrgUniqueId()).get());
        entity.setMediumName(mediumDTO.getMediumName());
        entity.setActive(mediumDTO.isActive());

        return entity;
    }
    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        MediumEntity entity = (MediumEntity) genericEntity;
        System.out.println("Executed....medium mapper resp " );
        MediumDTO mediumDTO = new MediumDTO();
        mediumDTO.setId(entity.getId());
        mediumDTO.setOrgUniqueId(entity.getSchool().getOrgUniqId());
        mediumDTO.setMediumName(entity.getMediumName());
        mediumDTO.setActive(entity.isActive());

        return mediumDTO;
    }

}
