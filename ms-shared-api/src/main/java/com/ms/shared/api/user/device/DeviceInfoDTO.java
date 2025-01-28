package com.ms.shared.api.user.device;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeviceInfoDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5983435916063448374L;
	
	@NotEmpty(message = "DeviceId can not be empty or null")
	private String _deviceId;
	
	@NotEmpty(message = "DeviceType can not be empty or null")
    private String _deviceType;
}
