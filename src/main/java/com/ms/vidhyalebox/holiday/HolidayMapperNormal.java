package com.ms.vidhyalebox.holiday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class HolidayMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private HolidayRepo repo;
	private SessionRepo session;

	@Autowired
	public HolidayMapperNormal(IOrgClientRepo orgRepo, HolidayRepo repo, SessionRepo session) {
		this.orgRepo = orgRepo;
		this.repo = repo;
		this.session = session;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		HolidayEntity entity = genericEntity == null ? new HolidayEntity() : (HolidayEntity) genericEntity;

		HolidayDTO esxpenseCDTO = (HolidayDTO) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(esxpenseCDTO.getOrgUniqId()).get());
		entity.setDescr(esxpenseCDTO.getDescr());
		entity.setTitle(esxpenseCDTO.getTitle());
		entity.setSession(session.findById(Long.valueOf(esxpenseCDTO.getSessionId())).get());
		entity.setDate(esxpenseCDTO.getDate());
		
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		HolidayEntity entity = (HolidayEntity) genericEntity;

		HolidayDTO expenseCDTO = new HolidayDTO();
		expenseCDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		expenseCDTO.setDescr(entity.getDescr());
		expenseCDTO.setTitle(entity.getTitle());
		expenseCDTO.setId(entity.getId());
		
		return expenseCDTO;
	}

}
