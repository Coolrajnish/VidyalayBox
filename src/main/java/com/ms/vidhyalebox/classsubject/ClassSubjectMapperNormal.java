package com.ms.vidhyalebox.classsubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.subject.SubjectRepo;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.ItemNotFoundException;
import com.ms.vidhyalebox.util.rest.UnknownErrorException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassSubjectMapperNormal implements IMapperNormal {

	@Autowired
	IOrgClientRepo schAdminRepo;
	@Autowired
	ClassRepo classRepo;
	@Autowired
	SubjectRepo sbjectRepo;
	@Autowired
	ClassSubjectRepo subjectRepo;
	
	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		
		ClassSubjectEntity entity = genericEntity == null ? new ClassSubjectEntity() : (ClassSubjectEntity) genericEntity;

		ClassSubjectDTO classSubjectDTO = (ClassSubjectDTO) genericDto;

		try {
        	var school = schAdminRepo.findByOrgUniqId(classSubjectDTO.getOrgUniqId())
        	    .orElseThrow(() -> new ItemNotFoundException("School not found for OrgUniqId: " + classSubjectDTO.getOrgUniqId()));

        	var classEntity = classRepo.findById(Long.valueOf(classSubjectDTO.getClassId()))
        	    .orElseThrow(() -> new EntityNotFoundException("Class not found for id: " + classSubjectDTO.getClassId()));

        	List<ClassSubjectEntity> coreEntities = new ArrayList<>();
        	classSubjectDTO.getCoreSubject().forEach(coreId -> {
        	    var subjectEntity = sbjectRepo.findById(coreId)
        	        .orElseThrow(() -> new ItemNotFoundException("Subject not found for id: " + coreId));
        	    var enty = new ClassSubjectEntity();
        	    enty.setSubjectEntity(subjectEntity);
        	    enty.setSchool(school);
        	    enty.setGroupName(0);
        	    enty.setSubjectType("CORE");
        	    enty.setClassname(classEntity);

        	    coreEntities.add(enty);  
        	});

        	List<ClassSubjectEntity> electEntities = new ArrayList<>();
        	AtomicInteger atomicCounter = new AtomicInteger(0);
        	String totalElectCount = classSubjectDTO.getElectSubject().getTotalElectCount();

        	classSubjectDTO.getElectSubject().getElectSubject().forEach(electId -> {
        	    var subjectEntity = sbjectRepo.findById(electId)
        	        .orElseThrow(() -> new EntityNotFoundException("Subject not found for id: " + electId));

        	    var entty = new ClassSubjectEntity();
        	    entty.setSubjectEntity(subjectEntity);
        	    entty.setGroupName(atomicCounter.getAndIncrement());
        	    entty.setSubjectType("ELECT");
        	    entty.setClassname(classEntity);
        	    entty.setTotalElectCount(totalElectCount);

        	    electEntities.add(entty); 
        	});

        	subjectRepo.saveAll(coreEntities);
        	subjectRepo.saveAll(electEntities);

        } catch (Exception e) {

            throw new UnknownErrorException("Failed to map class with subject - "+e.getMessage());
        }
		return null;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
//        SubjectEntity entity = (SubjectEntity) genericEntity;
//
//        SubjectDTO subjectDTO = new SubjectDTO();
//        subjectDTO.setOrgUniqId(entity.getOrgUniqId());
//        subjectDTO.setSubjectName(entity.getSubjectName());
//        subjectDTO.setSubjectCode(entity.getSubjectCode());
//        subjectDTO.setMedium(entity.getMedium());
//        subjectDTO.setSubjectType(entity.getSubjectType());
//        subjectDTO.setActive(entity.isActive());

		return null;
	}
}
