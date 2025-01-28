package com.ms.shared.api.auth;

import com.ms.shared.api.user.device.DeviceInfoDTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
public class LogoutRequestDTO {
	
	private DeviceInfoDTO _deviceInfoDTO;
	
}
