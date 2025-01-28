package com.ms.shared.api.auth;


import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.user.device.DeviceInfoDTO;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
