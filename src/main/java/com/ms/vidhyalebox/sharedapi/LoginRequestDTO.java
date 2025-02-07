package com.ms.vidhyalebox.sharedapi;


import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class LoginRequestDTO extends GenericDTO {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "UserName is mandatory")
	private final String _username;
	
	@NotEmpty (message = "Password is mandatory")
	private final String _password;
	
//	@Valid
//	@NotNull (message = "DeviceInfo is mandatory")
//	private final DeviceInfoDTO _deviceInfo;
//
//	public DeviceInfoDTO get_deviceInfo() {
//		return _deviceInfo;
//	}
	
}
