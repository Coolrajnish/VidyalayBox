package com.ms.shared.api.user.verify.otp;

import com.ms.shared.api.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class VerifyOTPResponseDTO extends GenericDTO {/**
	 * 
	 */
	private static final long serialVersionUID = -8119261457019396139L;
	
	private String _target;
	private boolean _isVerified;
	private boolean _isRequestProcessed;
}
