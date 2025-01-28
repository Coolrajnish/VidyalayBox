package com.ms.shared.api.user.activity;

import com.ms.shared.api.generic.GenericDTO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class UserActivityDTO extends GenericDTO  {
	/**
	 * 
	 */
	private String _dateTime;
	private String _activityModule;
	private String _messages;
	private String _userId;
	

}
