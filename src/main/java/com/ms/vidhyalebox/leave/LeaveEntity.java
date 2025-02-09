package com.ms.vidhyalebox.leave;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.user.UserEntity;
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
@Table(name = "user_leave")
public class LeaveEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;
    
    @ManyToOne
	@JoinColumn(name = "userId", nullable = false, unique = true)
	private final UserEntity user;

    @Column(name = "leaveStrtDate")
    private String leaveStrtDate;
    
    @Column(name = "leaveStrtEnd")
    private String leaveStrtEnd;
    
    @Column(name = "leaveReason")
    private String leaveReason;
    
    @Column(name = "leaveStatus")
    private String leaveStatus = "PENDING"; // Enum: PENDING, APPROVED, REJECTED

    @Column(name = "leaveFilePath")
    private String leaveFilePath;
    
}