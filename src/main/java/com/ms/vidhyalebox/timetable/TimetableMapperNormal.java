package com.ms.vidhyalebox.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.student.StudentRepo;
import com.ms.vidhyalebox.subject.SubjectRepo;
import com.ms.vidhyalebox.teacher.ITeacherRepo;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class TimetableMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private ITeacherRepo trepo;
	private ClassRepo classRepo;
	private SubjectRepo subrepo;

	@Autowired
	public  TimetableMapperNormal(ITeacherRepo trepo,SubjectRepo subrepo,ClassRepo classRepo,IOrgClientRepo orgRepo) {
		this.orgRepo = orgRepo;
		this.classRepo = classRepo;
		this.subrepo = subrepo;
		this.trepo = trepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		TimetableEntity entity = genericEntity == null ? new TimetableEntity() : (TimetableEntity) genericEntity;

		TimetableDTO timetableCDTO = (TimetableDTO) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(timetableCDTO.getSchool()).get());
		entity.setClassEntity(classRepo.findById(Long.valueOf(timetableCDTO.getClassEntity())).get());
		entity.setSubject(subrepo.findById(Long.valueOf( timetableCDTO.getSubject())).get());
		entity.setDate(timetableCDTO.getDate());
		entity.setDay(timetableCDTO.getDay());
		entity.setStartTime(timetableCDTO.getStartTime());
		entity.setEndTime(timetableCDTO.getEndTime());
		entity.setTeacher(trepo.findById( timetableCDTO.getTeacher()).get());
		
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
