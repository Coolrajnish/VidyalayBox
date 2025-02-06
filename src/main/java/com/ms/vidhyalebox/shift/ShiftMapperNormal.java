package com.ms.vidhyalebox.shift;

import com.ms.shared.api.auth.shiftDTO.ShiftDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        entity.setSchool(orgclientrepo.findByOrgUniqId( shiftDTO.getOrgUniqId()).get());
        entity.setShiftName(shiftDTO.getShiftName());
        entity.setStartTime(shiftDTO.getStartTime());
        entity.setEndTime(shiftDTO.getEndTime());
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
