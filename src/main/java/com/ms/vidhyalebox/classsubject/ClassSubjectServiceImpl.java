package com.ms.vidhyalebox.classsubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.subject.SubjectRepo;
import com.ms.vidhyalebox.teacher.ITeacherRepo;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.ItemNotFoundException;
import com.ms.vidhyalebox.util.rest.UnknownErrorException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassSubjectServiceImpl extends GenericService<GenericEntity, Long> implements ClassSubjectService {

    private final ClassSubjectRepo subjectRepo;
    private final ClassSubjectMapperNormal subjectMapperNormal;
    private final SubjectRepo sbjectRepo;
    private final IOrgClientRepo schAdminRepo;
    private final ClassRepo classRepo;
    private final ITeacherRepo teacherRepo;

    public ClassSubjectServiceImpl(ITeacherRepo teacherRepo,ClassRepo classRepo,ClassSubjectRepo subjectRepo, ClassSubjectMapperNormal subjectMapperNormal, SubjectRepo subjectRepo1, IOrgClientRepo schAdminRepo) {
        this.subjectRepo = subjectRepo;
        this.subjectMapperNormal = subjectMapperNormal;
        this.sbjectRepo = subjectRepo1;
        this.schAdminRepo = schAdminRepo;
		this.classRepo = classRepo;
		this.teacherRepo = teacherRepo;
    }

    @Override
    public JpaRepository getRepo() {
        return subjectRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return subjectMapperNormal;
    }

    @Transactional
    @Override
    public ResponseEntity<APiResponse<Object>> MapClassSubject(ClassSubjectDTO classSubjectDTO) {
        try {
        	var school = schAdminRepo.findByOrgUniqId(classSubjectDTO.getOrgUniqId())
        	    .orElseThrow(() -> new ItemNotFoundException("School not found for OrgUniqId: " + classSubjectDTO.getOrgUniqId()));

        	var classEntity = classRepo.findById(Long.valueOf(classSubjectDTO.getClassId()))
        	    .orElseThrow(() -> new EntityNotFoundException("Class not found for id: " + classSubjectDTO.getClassId()));

        	List<ClassSubjectEntity> coreEntities = new ArrayList<>();
        	classSubjectDTO.getCoreSubject().forEach(coreId -> {
        	    var subjectEntity = sbjectRepo.findById(coreId)
        	        .orElseThrow(() -> new ItemNotFoundException("Subject not found for id: " + coreId));

        	    var entity = new ClassSubjectEntity();
        	    entity.setSubjectEntity(subjectEntity);
        	    entity.setSchool(school);
        	    entity.setGroupName(0); // Fixed value for CORE
        	    entity.setSubjectType("CORE");
        	    entity.setClassname(classEntity);

        	    coreEntities.add(entity);  // Add to list for batch save
        	});

        	List<ClassSubjectEntity> electEntities = new ArrayList<>();
        	AtomicInteger atomicCounter = new AtomicInteger(0);
        	String totalElectCount = classSubjectDTO.getElectSubject().getTotalElectCount();

        	classSubjectDTO.getElectSubject().getElectSubject().forEach(electId -> {
        	    var subjectEntity = sbjectRepo.findById(electId)
        	        .orElseThrow(() -> new EntityNotFoundException("Subject not found for id: " + electId));

        	    var entity = new ClassSubjectEntity();
        	    entity.setSubjectEntity(subjectEntity);
        	    entity.setGroupName(atomicCounter.getAndIncrement());
        	    entity.setSubjectType("ELECT");
        	    entity.setClassname(classEntity);
        	    entity.setTotalElectCount(totalElectCount);

        	    electEntities.add(entity); 
        	});

        	subjectRepo.saveAll(coreEntities);
        	subjectRepo.saveAll(electEntities);

        } catch (Exception e) {

            throw new UnknownErrorException("Failed to map class with subject - "+e.getMessage());
        }

        return ResponseEntity.ok(new APiResponse<>(
                "success",
                "Class and subject mapped successfully",
                null,
                null
        ));
    }

	@Override
	public ResponseEntity<APiResponse<Object>> MapSubjectTeacher(SubjectTeacherDTO subjectDTO) {

		// Fetch constant entities before looping to avoid redundant database calls
		OrgClientEntity school = schAdminRepo.findById(subjectDTO.getOrgUniqId()).orElseThrow(
		        () -> new ItemNotFoundException("School not found for OrgUniqId: " + subjectDTO.getOrgUniqId()));

		var subjectEntity = sbjectRepo.findById(subjectDTO.getSubject()).orElseThrow(
		        () -> new EntityNotFoundException("Subject not found for id: " + subjectDTO.getSubject()));

		var classval = classRepo.findById(Long.valueOf(subjectDTO.getClassId())).orElseThrow(
		        () -> new EntityNotFoundException("Class not found for id: " + subjectDTO.getClassId()));

		var teacher = teacherRepo.findById(subjectDTO.getTeacherId()).orElseThrow(
		        () -> new EntityNotFoundException("Class teacher not found for id: " + subjectDTO.getTeacherId()));

		// Collect all the subject teacher ids and perform a single query to fetch all teachers
		List<Long> teacherIds = subjectDTO.getTeacher(); 
		List<TeacherEntity> subjectTeachers = teacherRepo.findAllById(teacherIds);
		if (subjectTeachers.size() != teacherIds.size()) {
		    throw new EntityNotFoundException("One or more subject teachers not found.");
		}

		// Prepare entities for bulk saving
		List<ClassSubjectEntity> entitiesToSave = new ArrayList<>();

		// Loop through teacherIds and create ClassSubjectEntity
		for (TeacherEntity subteacher : subjectTeachers) {
		    ClassSubjectEntity entity = new ClassSubjectEntity();

		    classval.setClassTeacher(teacher);
		    entity.setSubjectEntity(subjectEntity);
		    entity.setSchool(school);
		    entity.setClassname(classval);
		    entity.setSubjectTeacher(subteacher);

		    entitiesToSave.add(entity);
		}

		// Bulk save entities
		subjectRepo.saveAll(entitiesToSave);

		
		return null;
	}
}
