package com.ms.shared.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@AllArgsConstructor 
public class LogoutResponseDTO {
	
	private boolean _isLogout;
	private String _message;
}
