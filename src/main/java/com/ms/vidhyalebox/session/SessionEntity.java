package com.ms.vidhyalebox.session;

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
