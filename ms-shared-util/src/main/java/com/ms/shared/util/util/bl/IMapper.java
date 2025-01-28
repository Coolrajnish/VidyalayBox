package com.ms.shared.util.util.bl;

import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.domain.GenericEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapper {

	IMapper INSTANCE = Mappers.getMapper(IMapper.class);
	
	public GenericEntity dtoToEntity(final GenericDTO genericDTO);

	public GenericDTO entityToDto(final GenericEntity genericEntity);

	default List<GenericEntity> dtoToEntity(final List<GenericDTO> genericDTOs) {
		List<GenericEntity> genericEntities = new ArrayList<>();
		for (GenericDTO genericDTO : genericDTOs) {
			genericEntities.add(dtoToEntity(genericDTO));
		}
		return genericEntities;
	}

	default List<GenericDTO> entityToDto(final List<GenericEntity> genericEntities) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (GenericEntity genericEntity : genericEntities) {
			genericDTOs.add(entityToDto(genericEntity));
		}
		return genericDTOs;
	}
	default List<GenericDTO> getFilters(){
		return new ArrayList<>();
	}

}
