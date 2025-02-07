package com.ms.vidhyalebox.session;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
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
@Table(name = "session")
public class SessionEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "sessionName")
    private String sessionName;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

}
