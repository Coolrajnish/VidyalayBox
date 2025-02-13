package com.ms.vidhyalebox.holiday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.EntityNotFoundException;

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

		HolidayDTO holidayDTO = (HolidayDTO) genericDto;
		if (holidayDTO.getSchool() != null) {
			entity.setSchool(orgRepo.findByOrgUniqId(holidayDTO.getSchool())
					.orElseThrow(() -> new EntityNotFoundException("School not found")));
		}
		if (holidayDTO.getDescr() != null) {
			entity.setDescr(holidayDTO.getDescr());
		}
		if (holidayDTO.getTitle() != null) {
			entity.setTitle(holidayDTO.getTitle());
		}
		if (holidayDTO.getSessionId() != null) {
			entity.setSessionId(session.findById(Long.valueOf(holidayDTO.getSessionId()))
					.orElseThrow(() -> new EntityNotFoundException("Session not found")));
		}
		if (holidayDTO.getDate() != null) {
			entity.setDate(holidayDTO.getDate());
		}
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		HolidayEntity entity = (HolidayEntity) genericEntity;

		HolidayDTO expenseCDTO = new HolidayDTO();
		expenseCDTO.setSchool(entity.getSchool().getOrgUniqId());
		expenseCDTO.setDescr(entity.getDescr());
		expenseCDTO.setTitle(entity.getTitle());
		expenseCDTO.setId(entity.getId());

		return expenseCDTO;
	}

}
