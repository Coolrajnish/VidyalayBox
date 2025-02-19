package com.ms.vidhyalebox.feesinstalment;

import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesInstallmentMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		FeesInstallmentEntity entity = genericEntity == null ? new FeesInstallmentEntity()
				: (FeesInstallmentEntity) genericEntity;

		FeesInstallmentDTO classDTO = (FeesInstallmentDTO) genericDto;
		
		System.out.println("Entity class --->>>" + entity);
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {

		return null;
	}
}
