package com.ms.vidhyalebox.subject;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SubjectMapperNormal implements IMapperNormal {

	private IOrgClientRepo orgRepo;

	@Autowired
	private SubjectMapperNormal(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		SubjectEntity entity = genericEntity == null ? new SubjectEntity() : (SubjectEntity) genericEntity;

		SubjectDTO subjectDTO = (SubjectDTO) genericDto;
		// Check if orgUniqId is provided and not null before attempting to update
		if (subjectDTO.getOrgUniqId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(subjectDTO.getOrgUniqId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			}
		}

		// Check if subjectName is not null before updating
		if (subjectDTO.getSubjectName() != null) {
			entity.setSubjectName(subjectDTO.getSubjectName());
		}

		// Check if subjectCode is not null before updating
		if (subjectDTO.getSubjectCode() != null) {
			entity.setSubjectCode(subjectDTO.getSubjectCode());
		}

		// Check if medium is not null before updating
		if (subjectDTO.getMedium() != null) {
			entity.setMedium(subjectDTO.getMedium());
		}

		// Check if subjectType is not null before updating
		if (subjectDTO.getSubjectType() != null) {
			entity.setSubjectType(subjectDTO.getSubjectType());
		}

		// entity.setActive(subjectDTO.isActive());

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		SubjectEntity entity = (SubjectEntity) genericEntity;

		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		subjectDTO.setSubjectName(entity.getSubjectName());
		subjectDTO.setSubjectCode(entity.getSubjectCode());
		subjectDTO.setMedium(entity.getMedium());
		subjectDTO.setSubjectType(entity.getSubjectType());
		// subjectDTO.setActive(entity.getSchool().isActive());

		return subjectDTO;
	}
}
