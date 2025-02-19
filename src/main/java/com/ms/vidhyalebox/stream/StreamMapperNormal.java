package com.ms.vidhyalebox.stream;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class StreamMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Autowired
	public StreamMapperNormal(IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		StreamEntity entity = genericEntity == null ? new StreamEntity() : (StreamEntity) genericEntity;

		StreamDTO streamDTO = (StreamDTO) genericDto;
		// Check if orgUniqId is provided and not null before attempting to update
		if (streamDTO.getOrgUniqId() != null) {
			Optional<OrgClientEntity> orgOpt = orgRepo.findByOrgUniqId(streamDTO.getOrgUniqId());
			if (orgOpt.isPresent()) {
				entity.setSchool(orgOpt.get());
			}
		}

		// Check if streamName is not null before updating
		if (streamDTO.getStreamName() != null) {
			entity.setStreamName(streamDTO.getStreamName());
		}

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		StreamEntity entity = (StreamEntity) genericEntity;

		StreamDTO streamDTO = new StreamDTO();
		streamDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		streamDTO.setStreamName(entity.getStreamName());

		return streamDTO;
	}

}
