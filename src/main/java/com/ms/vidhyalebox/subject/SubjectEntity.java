package com.ms.vidhyalebox.subject;

import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "subject")
public class SubjectEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "medium")
    private String medium;

    @Column(name = "subject_type")
    private String subjectType;

}
