package com.ms.shared.util.util.bl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.domain.NativeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*
 *Company:mithlaSoftech Creation Date:2024
 *@author sumit kumar
 *@version 1.0
 */
@Slf4j
@Service
public abstract class AbstractNativeMapper implements IMapperNormal {

	public abstract GenericDTO objectToDto(final Object[] objArr);

	@Override
	public GenericEntity dtoToEntity(final GenericDTO genericDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericDTO entityToDto(final GenericEntity genericEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenericDTO> entityToDto(final List<GenericEntity> genericEntities) {
		List<GenericDTO> genericDTOs = new ArrayList<>();
		for (Object[] objArr : (List<Object[]>) ((NativeEntity) genericEntities.get(0)).getObject()) {
			genericDTOs.add(objectToDto(objArr));
		}
		return genericDTOs;
	}

	public static <T extends GenericDTO> List<T> mapGenericRestResponseTOConcereteDTOList(T t, String genericResponseStr)
			throws JsonProcessingException {
		List<T> concereteDTOs = new ArrayList<>();

		final ObjectNode mainNode = new ObjectMapper().readValue(genericResponseStr, ObjectNode.class);
		if (mainNode.has("code") && mainNode.get("code").toString().trim().equalsIgnoreCase("\"200\"")) {
			if (mainNode.has("modal")) {
				final ObjectNode modalNode = new ObjectMapper().readValue(mainNode.get("modal").toString(),
						ObjectNode.class);
				if (modalNode.has("data")) {
					// create ObjectMapper instance
					ObjectMapper objectMapper = new ObjectMapper();
					concereteDTOs = objectMapper.readValue(modalNode.get("data").toString(),
							objectMapper.getTypeFactory().constructCollectionType(List.class, t.getClass()));
				}
			}
		} else {
			log.error("til service failure with response : {}", genericResponseStr);
			throw new IllegalStateException("til service failure with response :" + genericResponseStr);
		}
		return concereteDTOs;
	}
}
