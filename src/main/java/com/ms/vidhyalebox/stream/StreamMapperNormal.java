package com.ms.vidhyalebox.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class StreamMapperNormal implements IMapperNormal {
    private IOrgClientRepo orgRepo;

    @Autowired
    public StreamMapperNormal (IOrgClientRepo orgRepo){
        this.orgRepo = orgRepo;
    }
    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        StreamEntity entity = genericEntity == null ? new StreamEntity() : (StreamEntity) genericEntity;

        StreamDTO streamDTO = (StreamDTO) genericDto;
        entity.setSchool(orgRepo.findByOrgUniqId(streamDTO.getOrgUniqId()).get());
        entity.setStreamName(streamDTO.getStreamName());

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
