package com.ms.vidhyalebox.studentattendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.student.StudentRepo;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class StudentAttendanceMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private StudentAttendanceRepo repo;
	private SessionRepo session;
	private ClassRepo classRepo;
	private StudentRepo sturepo;

	@Autowired
	public StudentAttendanceMapperNormal(StudentRepo sturepo,ClassRepo classRepo,IOrgClientRepo orgRepo, StudentAttendanceRepo repo, SessionRepo session) {
		this.orgRepo = orgRepo;
		this.repo = repo;
		this.session = session;
		this.classRepo = classRepo;
		this.sturepo = sturepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		StudentAttendanceEntity entity = genericEntity == null ? new StudentAttendanceEntity() : (StudentAttendanceEntity) genericEntity;

		StudentAttendanceDTO studentCDTO = (StudentAttendanceDTO) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(studentCDTO.getOrgUniqId()).get());
		entity.setClassEntity(classRepo.findById(Long.valueOf(studentCDTO.getClassId())).get());
		entity.setStudent(sturepo.findById(Long.valueOf( studentCDTO.getStudentId())).get());
		entity.setHoilday(studentCDTO.isHoilday());
		entity.setSendNotificationIfAbsent(studentCDTO.isSendNotificationIfAbsent());
		entity.setDate(studentCDTO.getDate());

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		StudentAttendanceEntity entity = (StudentAttendanceEntity) genericEntity;

		StudentAttendanceDTO expenseCDTO = new StudentAttendanceDTO();
		expenseCDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		expenseCDTO.setId(entity.getId());
		
		return expenseCDTO;
	}

}
