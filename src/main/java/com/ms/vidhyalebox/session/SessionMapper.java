package com.ms.vidhyalebox.session;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SessionMapper implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Autowired
	public SessionMapper(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		SessionEntity entity = genericEntity == null ? new SessionEntity() : (SessionEntity) genericEntity;

		SessionDTO sessionDTO = (SessionDTO) genericDto;

		// Check if orgUniqueId is provided and not null before attempting to update
		if (sessionDTO.getOrgUniqueId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(sessionDTO.getOrgUniqueId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			}
		}

		// Check if sessionName is not null before updating
		if (sessionDTO.getSessionName() != null) {
			entity.setSessionName(sessionDTO.getSessionName());
		}

		// Set the active status; since it's a primitive boolean, it is always set
		entity.setActive(sessionDTO.isActive());

		// Check if startDate is not null before updating
		if (sessionDTO.getStartDate() != null) {
			entity.setStartDate(sessionDTO.getStartDate());
		}

		// Check if endDate is not null before updating
		if (sessionDTO.getEndDate() != null) {
			entity.setEndDate(sessionDTO.getEndDate());
		}

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
