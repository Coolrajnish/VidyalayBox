package com.ms.shared.api.user.verify.otp;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Accessors(prefix = "_")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class VerifyOTPRequestDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7146563550544641375L;
	
	@NotEmpty
	private String _target;

	@NotEmpty
	private String _type;

	@NotEmpty
	private String _purpose;

	@NotEmpty
	@Size(min = 6, max = 6)
	private String _verificationCode;
}
