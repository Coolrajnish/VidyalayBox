package com.ms.vidhyalebox.leavesettings;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
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
@Table(name = "leavesettings")
public class LeaveSettingsEntity extends GenericEntity {
	
	public LeaveSettingsEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "totalLeavePerMnth")
    private String totalLeavePerMnth;
    
    //sun-mon
    @Column(name = "holiday")
    private String holiday;
    
    @OneToOne
	@JoinColumn(name = "sessionId", nullable = false, unique = true)
	private SessionEntity session;

}
