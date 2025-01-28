package com.ms.shared.api.user.password;

import com.ms.shared.api.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ChangePasswordResponseDTO  extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2505031855910908739L;
	
	private boolean _isPasswordUpdated;
	
}
