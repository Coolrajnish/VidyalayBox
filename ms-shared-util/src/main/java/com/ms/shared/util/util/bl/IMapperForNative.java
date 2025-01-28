package com.ms.shared.util.util.bl;

import com.ms.shared.api.generic.GenericDTO;

import java.util.ArrayList;
import java.util.List;

public interface IMapperForNative {

	public GenericDTO objectToDto(Object[] objArr);

	default List<GenericDTO> entityToDto(final List<Object[]> genericObjectArrays) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (Object[] objArr : genericObjectArrays) {
			genericDTOs.add(objectToDto(objArr));
		}
		return genericDTOs;
	}

}
