package com.ms.vidhyalebox.classsubject;

import com.ms.shared.api.auth.classsubjectDTO.ClassSubjectDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.addadmin.SchAdminEntity;
import com.ms.vidhyalebox.addadmin.SchAdminRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.subject.SubjectRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public GenericResponse MapClassSubject(ClassSubjectDTO classSubjectDTO) {
        List<Notification> notifications = new ArrayList<>();
        try {
            for (Long coreId : classSubjectDTO.getCoreSubject()) {
                ClassSubjectEntity entity = new ClassSubjectEntity();
                SubjectEntity subjectEntity = sbjectRepo.findById(coreId).get();

                entity.setSubjectEntity(subjectEntity);
                entity.setSchool(schAdminRepo.findByOrgUniqId(classSubjectDTO.getOrgUniqId()).get());
                entity.setGroupName(0);
                entity.setSubjectType("CORE");
                entity.setClassname(classSubjectDTO.getClassName());
                subjectRepo.save(entity);
            }
            int i = 1;
            for (Long electId : classSubjectDTO.getElectSubject().getElectSubject()) {
                ClassSubjectEntity entity = new ClassSubjectEntity();
                SubjectEntity subjectEntity = sbjectRepo.findById(electId).get();
                entity.setSubjectEntity(subjectEntity);
                entity.setGroupName(i++);
                entity.setSubjectType("ELECT");
                entity.setClassname(classSubjectDTO.getClassName());
                entity.setTotalElectCount(classSubjectDTO.getElectSubject().getTotalElectCount());
                subjectRepo.save(entity);
            }
        } catch (Exception e) {
            Notification notification = new Notification();
            notification.setNoificationCode("401");
            notification.setNotificationDescription("Failed to transfer/promote student");
            notifications.add(notification);

         //   throw new RuntimeException(e);
        }

        if (!notifications.isEmpty()) {
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            genericResponse.setNotifications(notifications);

            return genericResponse;
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
        Notification notification = new Notification();
        notification.setNoificationCode("200");
        notification.setNotificationDescription("Class and subject mapped successfully");
        notifications.add(notification);

        return genericResponse;
    }
}
