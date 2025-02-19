package com.ms.vidhyalebox.shift;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ShiftMapperNormal implements IMapperNormal {

    private IOrgClientRepo orgclientrepo;

    @Autowired
    public ShiftMapperNormal(IOrgClientRepo orgclientrepo){
        this.orgclientrepo = orgclientrepo;
    }

    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        ShiftEntity entity = genericEntity == null ? new ShiftEntity() : (ShiftEntity) genericEntity;

        ShiftDTO shiftDTO = (ShiftDTO) genericDto;
     // Check if orgUniqueId is provided and not null before attempting to update
        if (shiftDTO.getOrgUniqId() != null) {
            Optional<OrgClientEntity> orgOpt = orgclientrepo.findByOrgUniqId(shiftDTO.getOrgUniqId());
            if (orgOpt.isPresent()) {
                entity.setSchool(orgOpt.get());
            } 
        }

        // Check if shiftName is not null before updating
        if (shiftDTO.getShiftName() != null) {
            entity.setShiftName(shiftDTO.getShiftName());
        }

        // Check if startTime is not null before updating
        if (shiftDTO.getStartTime() != null) {
            entity.setStartTime(shiftDTO.getStartTime());
        }

        // Check if endTime is not null before updating
        if (shiftDTO.getEndTime() != null) {
            entity.setEndTime(shiftDTO.getEndTime());
        }

        // Set the active status; since it's a primitive boolean, it is always set
        entity.setActive(shiftDTO.isActive());


        return entity;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        ShiftEntity entity = (ShiftEntity) genericEntity;

        ShiftDTO shiftDTO = new ShiftDTO();
        shiftDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
        shiftDTO.setShiftName(entity.getShiftName());
        shiftDTO.setStartTime(entity.getStartTime());
        shiftDTO.setEndTime(entity.getEndTime());
        shiftDTO.setActive(entity.isActive());

        return shiftDTO;
    }
}
