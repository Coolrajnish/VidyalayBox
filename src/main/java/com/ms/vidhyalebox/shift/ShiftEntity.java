package com.ms.vidhyalebox.shift;

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
@Table(name = "shift")
public class ShiftEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "shift_name")
    private String shiftName;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

}
