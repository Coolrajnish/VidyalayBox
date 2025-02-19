package com.ms.vidhyalebox.feesamount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.feestype.FeesTypeRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesAmountMapperNormal implements IMapperNormal {
	@Autowired
	IOrgClientRepo orgRepo;
	@Autowired
	FeesTypeRepo feeRepo;
	
	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		FeesAmountEntity entity = genericEntity == null ? new FeesAmountEntity() : (FeesAmountEntity) genericEntity;

		FeesAmountDTO feesDTO = (FeesAmountDTO) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(feesDTO.getOrgUniqId()).get());
		entity.setFeeType(feeRepo.findById(feesDTO.getFeeTypeId()).get());
		entity.setFeeTypes(feesDTO.getFeeTypes());
	//	entity.set
		System.out.println("Entity class --->>>" + entity);
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {

		return null;
	}
}
