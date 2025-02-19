package com.ms.vidhyalebox.section;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SectionMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Autowired
	public SectionMapperNormal(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		SectionEntity entity = genericEntity == null ? new SectionEntity() : (SectionEntity) genericEntity;

		SectionDTO sectionDTO = (SectionDTO) genericDto;
		// Check if orgUniqId is provided and not null before attempting to update
		if (sectionDTO.getOrgUniqId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(sectionDTO.getOrgUniqId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			}
		}

		// Check if sectionName is not null before updating
		if (sectionDTO.getSectionName() != null) {
			entity.setSectionName(sectionDTO.getSectionName());
		}

		// Set the active status; since it's a primitive boolean, it is always set
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
