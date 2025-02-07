package com.ms.vidhyalebox.sharedapi;

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
