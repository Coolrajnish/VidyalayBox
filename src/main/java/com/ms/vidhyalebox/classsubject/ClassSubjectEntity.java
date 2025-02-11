package com.ms.vidhyalebox.classsubject;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classname;

    @ManyToOne
    @JoinColumn(name = "subject_teacher_id")
    private TeacherEntity subjectTeacher;
    
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
