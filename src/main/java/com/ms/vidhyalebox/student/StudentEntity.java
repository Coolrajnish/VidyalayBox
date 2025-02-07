package com.ms.vidhyalebox.student;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.parent.ParentEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import com.ms.vidhyalebox.user.UserEntity;
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
@Table(name = "student")
public class StudentEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "sessionId", nullable = false)
    private SessionEntity sessionEntity;

    @ManyToOne
    @JoinColumn(name = "classId",  nullable = false)
    private ClassEntity classEntity;

    @ManyToOne
    @JoinColumn(name = "parentId", nullable = false)
    private ParentEntity parentEntity;

    
    //redundant data should be removed
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "admissionDate")
    private String admissionDate;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "emergencyContact")
    private String emergencyContact;

    @Column(name = "permanentAddress")
    private String permanentAddress;

}
