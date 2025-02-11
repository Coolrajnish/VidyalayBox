package com.ms.vidhyalebox.leavesettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class LeaveSettingsMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private SessionRepo sesRepo;

	@Autowired
	public LeaveSettingsMapperNormal(IOrgClientRepo orgRepo, SessionRepo sesRepo) {
		this.orgRepo = orgRepo;
		this.sesRepo = sesRepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		LeaveSettingsDTO leaveSettingsDTO = (LeaveSettingsDTO) genericDto;
		LeaveSettingsEntity entity = genericEntity == null
				? new LeaveSettingsEntity()
				: (LeaveSettingsEntity) genericEntity;

		entity.setSchool(orgRepo.findByOrgUniqId(leaveSettingsDTO.getOrgUniqueId()).get());
		entity.setHoliday(leaveSettingsDTO.getHoliday());
		entity.setTotalLeavePerMnth(leaveSettingsDTO.getTotalLeavePerMnth());
		entity.setSession(sesRepo.findById(Long.valueOf( leaveSettingsDTO.getSessionId())).get());
		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		LeaveSettingsEntity entity = (LeaveSettingsEntity) genericEntity;

		return new LeaveSettingsDTO(entity.getSchool().getOrgUniqId(), entity.getTotalLeavePerMnth(),
				entity.getHoliday(), String.valueOf(entity.getSession().getId()));

	}

}
