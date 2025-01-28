package com.ms.vidhyalebox.session;

import com.ms.shared.api.auth.mediumDTO.MediumDTO;
import com.ms.shared.api.auth.sessionDTO.SessionDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionMapper implements IMapperNormal {
    private IOrgClientRepo orgRepo;

    @Autowired
    public SessionMapper (IOrgClientRepo orgRepo){
        this.orgRepo = orgRepo;
    }


    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        SessionEntity entity = genericEntity == null ? new SessionEntity() : (SessionEntity) genericEntity;

        SessionDTO sessionDTO = (SessionDTO) genericDto;

        entity.setSchool(orgRepo.findByOrgUniqId( sessionDTO.getOrgUniqueId()).get());
        entity.setSessionName(sessionDTO.getSessionName());
        entity.setActive(sessionDTO.isActive() );
        entity.setStartDate(sessionDTO.getStartDate());
        entity.setEndDate(sessionDTO.getEndDate());
        System.out.println("Entity ..........>>>"+entity);
        return entity;
    }
    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        SessionEntity entity = (SessionEntity) genericEntity;

        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setOrgUniqueId(entity.getSchool().getOrgUniqId());
        sessionDTO.setSessionName(entity.getSessionName());
        sessionDTO.setStartDate(entity.getStartDate());
        sessionDTO.setEndDate(entity.getEndDate());
        sessionDTO.setActive(entity.isActive());

        return sessionDTO;
    }

}
