package com.ms.shared.api.generic;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AppHealthDTO extends GenericDTO{
	
	private static final long serialVersionUID = 1L;

	private String appGroup;
	private String appName;
	private String appVersion;
	private String time;

}
