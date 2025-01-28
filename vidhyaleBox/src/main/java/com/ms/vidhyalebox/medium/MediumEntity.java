package com.ms.vidhyalebox.medium;

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
@Table(name = "medium")
public class MediumEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "medium_name")
    private String mediumName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

}
