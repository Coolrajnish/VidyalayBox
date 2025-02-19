package com.ms.vidhyalebox.timetable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.subject.SubjectRepo;
import com.ms.vidhyalebox.teacher.ITeacherRepo;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class TimetableMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private ITeacherRepo trepo;
	private ClassRepo classRepo;
	private SubjectRepo subrepo;

	@Autowired
	public TimetableMapperNormal(ITeacherRepo trepo, SubjectRepo subrepo, ClassRepo classRepo, IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
		this.classRepo = classRepo;
		this.subrepo = subrepo;
		this.trepo = trepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		TimetableEntity entity = genericEntity == null ? new TimetableEntity() : (TimetableEntity) genericEntity;

		TimetableDTO timetableCDTO = (TimetableDTO) genericDto;
		if (timetableCDTO.getSchool() != null) {
			Optional<OrgClientEntity> orgClient = orgRepo.findByOrgUniqId(timetableCDTO.getSchool());
			orgClient.ifPresent(entity::setSchool); // Only set if found
		}

		if (timetableCDTO.getClassEntity() != null) {
			Optional<ClassEntity> classEntity = classRepo.findById(Long.valueOf(timetableCDTO.getClassEntity()));
			classEntity.ifPresent(entity::setClassEntity); // Only set if found
		}

		if (timetableCDTO.getSubject() != null) {
			Optional<SubjectEntity> subject = subrepo.findById(Long.valueOf(timetableCDTO.getSubject()));
			subject.ifPresent(entity::setSubject); // Only set if found
		}

		if (timetableCDTO.getDate() != null) {
			entity.setDate(timetableCDTO.getDate());
		}

		if (timetableCDTO.getDay() != null) {
			entity.setDay(timetableCDTO.getDay());
		}

		if (timetableCDTO.getStartTime() != null) {
			entity.setStartTime(timetableCDTO.getStartTime());
		}

		if (timetableCDTO.getEndTime() != null) {
			entity.setEndTime(timetableCDTO.getEndTime());
		}

		if (timetableCDTO.getTeacher() != null) {
			Optional<TeacherEntity> teacher = trepo.findById(timetableCDTO.getTeacher());
			teacher.ifPresent(entity::setTeacher); // Only set if found
		}

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		TimetableEntity entity = (TimetableEntity) genericEntity;

		TimetableDTO expenseCDTO = new TimetableDTO();
		expenseCDTO.setSchool(entity.getSchool().getOrgUniqId());
		expenseCDTO.setId(entity.getId());
		expenseCDTO.setClassEntity(entity.getClassEntity().getId());

		return expenseCDTO;
	}

}
