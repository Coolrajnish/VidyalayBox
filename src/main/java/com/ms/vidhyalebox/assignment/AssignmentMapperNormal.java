package com.ms.vidhyalebox.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.subject.SubjectRepo;
import com.ms.vidhyalebox.teacher.ITeacherRepo;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.utility.VidhyaleBoxUtil;

@Service
public class AssignmentMapperNormal implements IMapperNormal {
	private IOrgClientRepo orgRepo;
	private AssignmentRepo repo;
	private SessionRepo session;
	private ITeacherRepo trepo;
	private SubjectRepo subRepo;
	private ClassRepo crepo;

	@Autowired
	public AssignmentMapperNormal(ITeacherRepo trepo, SubjectRepo subRepo, ClassRepo crepo, IOrgClientRepo orgRepo,
			AssignmentRepo repo, SessionRepo session) {
		this.orgRepo = orgRepo;
		this.repo = repo;
		this.session = session;
		this.crepo = crepo;
		this.subRepo = subRepo;
		this.trepo = trepo;
	}

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		AssignmentEntity entity = genericEntity == null ? new AssignmentEntity() : (AssignmentEntity) genericEntity;

		AssignmentDTO assignmentDTO = (AssignmentDTO) genericDto;
		entity.setSchool(orgRepo.findByOrgUniqId(assignmentDTO.getOrgUniqId()).get());
		if (assignmentDTO.getClassId() != null) {
			entity.setClassName(crepo.findById(assignmentDTO.getClassId()).get());
		}
		if (assignmentDTO.getSession() != null) {
			entity.setSession(session.findById(assignmentDTO.getSession()).orElse(null)); // orElse handles null cases
		}
		if (assignmentDTO.getSubjectId() != null) {
			entity.setSubject(subRepo.findById(Long.valueOf(assignmentDTO.getSubjectId())).orElse(null)); // orElse
		}
		if (assignmentDTO.getCreatedById() != null) {
			entity.setTeacher(trepo.findById(Long.valueOf(assignmentDTO.getCreatedById())).orElse(null)); // orElse
		}
		//if (assignmentDTO.getEditTeacherId() != null) {
	//		entity.setEditTeacher(trepo.findById(Long.valueOf(assignmentDTO.getEditTeacherId())).orElse(null)); // orElse
	//	}
		if (VidhyaleBoxUtil.isNullOrBlank( assignmentDTO.getAssignmentName())) {
			entity.setAssignmentName(assignmentDTO.getAssignmentName());
		}
		if (VidhyaleBoxUtil.isNullOrBlank( assignmentDTO.getDescription())) {
			entity.setDescription(assignmentDTO.getDescription());
		}
		if (VidhyaleBoxUtil.isNullOrBlank( assignmentDTO.getDueDate())) {
			entity.setDueDate(assignmentDTO.getDueDate());
		}
		if (VidhyaleBoxUtil.isNullOrBlank( assignmentDTO.getPoints())) {
			entity.setPoints(assignmentDTO.getPoints());
		}
		if (VidhyaleBoxUtil.isNullOrBlank( assignmentDTO.getResubmissionDays()) ) {
			entity.setResubmissionDays(assignmentDTO.getResubmissionDays());
		}

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		AssignmentEntity entity = (AssignmentEntity) genericEntity;

		AssignmentDTO assignmentCDTO = new AssignmentDTO();
		assignmentCDTO.setOrgUniqId(entity.getSchool().getOrgUniqId());
		assignmentCDTO.setAssignmentName(entity.getAssignmentName());
		assignmentCDTO.setDescription(entity.getDescription());
		assignmentCDTO.setClassId(entity.getClassName().getId());
		//assignmentCDTO.setId(entity.getId());

		return assignmentCDTO;
	}

}
