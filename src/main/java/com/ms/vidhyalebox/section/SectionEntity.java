package com.ms.vidhyalebox.section;

import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "section")
public class SectionEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "section_name")
    private String sectionName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
