package com.ms.vidhyalebox.studentattendance;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.expensecategory.ExpenseCategoryEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import com.ms.vidhyalebox.student.StudentEntity;
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
@Table(name = "student_attendance")
public class StudentAttendanceEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "classId", nullable = false)
    private ClassEntity classEntity;
   
    @Column(name = "date")
    private String date;

    @Column(name = "isHoliday")
    private boolean isHoilday = false;
    
    @Column(name = "status")
    private String status;  // PRESENT/ABSENT/LATE 
    
    @Column(name = "sendNotificationIfAbsent")
    private boolean sendNotificationIfAbsent = false;
    
}
