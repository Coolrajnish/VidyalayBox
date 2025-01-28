package com.ms.shared.util.util.bl;

import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.domain.GenericEntity;

import java.util.ArrayList;
import java.util.List;

public interface IMapperNormal {
	
	public List<GenericDTO> filters = new ArrayList<>();

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

	default List<GenericDTO> entityToDtoForAdmin(final List<GenericEntity> genericEntities) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (GenericEntity genericEntity : genericEntities) {
			genericDTOs.add(entityToDtoForAdmin(genericEntity));
		}
		return genericDTOs;
	}

	default List<GenericDTO> entityToDtoLazy(final List<GenericEntity> genericEntities) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (GenericEntity genericEntity : genericEntities) {
			genericDTOs.add(entityToDtoLazy(genericEntity));
		}
		return genericDTOs;
	}


	default List<GenericDTO> entityToDto(final List<GenericEntity> genericEntities,boolean flag) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (GenericEntity genericEntity : genericEntities) {
			genericDTOs.add(entityToDto(genericEntity));
		}
		return genericDTOs;
	}

	default List<GenericDTO> getFilters() {
		return filters;
	}



	default List<GenericDTO> dtoToDto(final List<GenericDTO> genericDtos) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (GenericDTO genericEntity : genericDtos) {
			genericDTOs.add(dtoToDto(genericEntity));
		}
		return genericDTOs;
	}
	public default GenericDTO dtoToDto(final GenericDTO genericDTO) {
		
		//will be implemented in child class, by default returning same object
		return genericDTO;
	}

	public default GenericEntity dtoToEntity(final GenericDTO genericDTO, GenericEntity genericEntity)
	{
		
		//will be implemented in child class, by default returning same object
		return new GenericEntity();
	}
	
	public default GenericEntity dtoToEntity(final GenericDTO genericDTO)
	{
		GenericEntity genericEntity = dtoToEntity(genericDTO, null);
		
		return genericEntity;
	}
	
	public default GenericDTO entityToDto(final GenericEntity genericEntity)
	{
		//will be implemented in child class, by default returning same object
		return new GenericDTO();
	}
	public default GenericDTO entityToDtoForAdmin(final GenericEntity genericEntity)
	{
		//will be implemented in child class, by default returning same object
		return new GenericDTO();
	}
	
	public default GenericDTO entityToDtoLazy(final GenericEntity genericEntity)
	{
		//will be implemented in child class, by default returning same object
		return new GenericDTO();
	}
}
