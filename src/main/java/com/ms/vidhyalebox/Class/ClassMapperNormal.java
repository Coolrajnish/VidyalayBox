package com.ms.vidhyalebox.Class;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.medium.MediumRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.section.SectionEntity;
import com.ms.vidhyalebox.section.SectionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.shift.ShiftEntity;
import com.ms.vidhyalebox.shift.ShiftRepo;
import com.ms.vidhyalebox.stream.StreamEntity;
import com.ms.vidhyalebox.stream.StreamRepo;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ClassMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private ShiftRepo shiftRepo;
	private SectionRepo sectionRepo;
	private StreamRepo streamRepo;
	private MediumRepo mediumRepo;

	@Autowired
	private ClassMapperNormal(IOrgClientRepo orgRepo, ShiftRepo shiftRepo, SectionRepo sectionRepo,
			StreamRepo streamRepo, MediumRepo mediumRepo) {
		this.orgRepo = orgRepo;
		this.shiftRepo = shiftRepo;
		this.sectionRepo = sectionRepo;
		this.streamRepo = streamRepo;
		this.mediumRepo = mediumRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		ClassEntity entity = genericEntity == null ? new ClassEntity() : (ClassEntity) genericEntity;

		ClassDTO classDTO = (ClassDTO) genericDto;

		// Check if orgUniqueId is provided and not null before attempting to update
		if (classDTO.getOrgUniqueId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(classDTO.getOrgUniqueId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			} 
		}

		// Check if className is not null before updating
		if (classDTO.getClassName() != null) {
			entity.setClassName(classDTO.getClassName());
		}

		// Check if shiftId is provided and not null before attempting to update
		if (classDTO.getShiftId() != null) {
			Optional<ShiftEntity> shiftOpt = shiftRepo.findById(classDTO.getShiftId());
			if (shiftOpt.isPresent()) {
				entity.setShiftEntity(shiftOpt.get());
			}
		}

		// Check if sectionId is provided and not null before attempting to update
		if (classDTO.getSectionId() != null) {
			Optional<SectionEntity> sectionOpt = sectionRepo.findById(classDTO.getSectionId());
			if (sectionOpt.isPresent()) {
				entity.setSectionEntity(sectionOpt.get());
			} 
		}

		// Check if streamId is provided and not null before attempting to update
		if (classDTO.getStreamId() != null) {
			Optional<StreamEntity> streamOpt = streamRepo.findById(classDTO.getStreamId());
			if (streamOpt.isPresent()) {
				entity.setStreamEntity(streamOpt.get());
			}
		}

		// Check if mediumId is provided and not null before attempting to update
		if (classDTO.getMediumId() != null) {
			Optional<MediumEntity> mediumOpt = mediumRepo.findById(classDTO.getMediumId());
			if (mediumOpt.isPresent()) {
				entity.setMedium(mediumOpt.get());
			}
		}

		// Check if isActive is provided before setting it
		entity.setActive(classDTO.isActive());

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		ClassEntity entity = (ClassEntity) genericEntity;

		ClassDTO classDTO = new ClassDTO();
		classDTO.setOrgUniqueId(entity.getSchool().getOrgUniqId());
		classDTO.setClassName(entity.getClassName());
		classDTO.setActive(entity.isActive());

		return classDTO;
	}
}
