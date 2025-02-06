package com.ms.vidhyalebox.classsubject;

import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.subject.SubjectEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "class_subject_table")
public class ClassSubjectEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "class_name")
    private String classname;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;

    @Column(name = "subject_type")
    private String subjectType;  // Enum to represent SELECTIVE/MANDATORY better

    @Column(name = "total_elect_count")
    private String totalElectCount;

    @Column(name = "group_name")
    private int groupName;
}
