package com.ms.vidhyalebox.util.bl;

import java.util.ArrayList;
import java.util.List;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

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
