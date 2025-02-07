package com.ms.vidhyalebox.classsubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.sharedapi.classsubjectDTO.ClassSubjectDTO;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.sharedapi.generic.Notification;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.subject.SubjectRepo;
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

    public ClassSubjectServiceImpl(ClassSubjectRepo subjectRepo, ClassSubjectMapperNormal subjectMapperNormal, SubjectRepo subjectRepo1, IOrgClientRepo schAdminRepo) {
        this.subjectRepo = subjectRepo;
        this.subjectMapperNormal = subjectMapperNormal;
        this.sbjectRepo = subjectRepo1;
        this.schAdminRepo = schAdminRepo;
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
        List<Notification> notifications = new ArrayList<>();
        try {
            classSubjectDTO.getCoreSubject().forEach(coreId -> {
                var subjectEntity = sbjectRepo.findById(coreId)
                                              .orElseThrow(() -> new ItemNotFoundException("Subject not found for id: " + coreId));
                
                var school = schAdminRepo.findByOrgUniqId(classSubjectDTO.getOrgUniqId())
                                         .orElseThrow(() -> new ItemNotFoundException("School not found for OrgUniqId: " + classSubjectDTO.getOrgUniqId()));

                var entity = new ClassSubjectEntity();
                entity.setSubjectEntity(subjectEntity);
                entity.setSchool(school);
                entity.setGroupName(0);
                entity.setSubjectType("CORE");
                entity.setClassname(classSubjectDTO.getClassName());
                
                subjectRepo.save(entity);
            });
            var totalElectCount = classSubjectDTO.getElectSubject().getTotalElectCount();
            AtomicInteger atomicCounter = new AtomicInteger(0); // Atomic integer to keep track of the group number

            classSubjectDTO.getElectSubject().getElectSubject().forEach(electId -> {
                var subjectEntity = sbjectRepo.findById(electId)
                                              .orElseThrow(() -> new EntityNotFoundException("Subject not found for id: " + electId));

                var entity = new ClassSubjectEntity();
                entity.setSubjectEntity(subjectEntity);
                entity.setGroupName(atomicCounter.getAndIncrement()); // Increment and get the value
                entity.setSubjectType("ELECT");
                entity.setClassname(classSubjectDTO.getClassName());
                entity.setTotalElectCount(totalElectCount);

                subjectRepo.save(entity);
            });
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
}
