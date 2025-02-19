package com.ms.vidhyalebox.medium;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class MediumMapper implements IMapperNormal {

	private IOrgClientRepo orgRepo;

	@Autowired
	private MediumMapper(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		MediumEntity entity = genericEntity == null ? new MediumEntity() : (MediumEntity) genericEntity;
		MediumDTO mediumDTO = (MediumDTO) genericDto;
		// Check if the result of orgRepo.findByOrgUniqId is null or empty, and handle
		// it accordingly
		if (mediumDTO.getOrgUniqueId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(mediumDTO.getOrgUniqueId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			} 
		}

		if (mediumDTO.getMediumName() != null) {
			entity.setMediumName(mediumDTO.getMediumName());
		}

		entity.setActive(mediumDTO.isActive());

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		MediumEntity entity = (MediumEntity) genericEntity;
		System.out.println("Executed....medium mapper resp ");
		MediumDTO mediumDTO = new MediumDTO();
		mediumDTO.setId(entity.getId());
		mediumDTO.setOrgUniqueId(entity.getSchool().getOrgUniqId());
		mediumDTO.setMediumName(entity.getMediumName());
		mediumDTO.setActive(entity.isActive());

		return mediumDTO;
	}

}
