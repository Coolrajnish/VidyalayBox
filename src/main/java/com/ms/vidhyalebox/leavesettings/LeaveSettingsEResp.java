package com.ms.vidhyalebox.leavesettings;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class LeaveSettingsEResp {

	private Long id;

	//private String school;

	private String totalLeavePerMnth;

	private String holiday;

	private Long session_id;
	
	
	public LeaveSettingsEResp(Long id,  String totalLeavePerMnth, String holiday, Long session_id) {
        this.id = id;
     //   this.school = school;
        this.totalLeavePerMnth = totalLeavePerMnth;
        this.holiday = holiday;
        this.session_id = session_id;
    }
}