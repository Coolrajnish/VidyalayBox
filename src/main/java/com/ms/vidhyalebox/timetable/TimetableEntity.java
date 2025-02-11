package com.ms.vidhyalebox.timetable;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.expensecategory.ExpenseCategoryEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import com.ms.vidhyalebox.student.StudentEntity;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "timetable")
public class TimetableEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @ManyToOne
    @JoinColumn(name = "teacherId", nullable = false)
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "classId", nullable = false)
    private ClassEntity classEntity;
   
    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private SubjectEntity subject;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "day")
    private String day; // e.g., Monday, Tuesday
    
    @Column(name = "startTimeate")
    private String startTime;
    
    @Column(name = "endTimeate")
    private String endTime;
    
}
