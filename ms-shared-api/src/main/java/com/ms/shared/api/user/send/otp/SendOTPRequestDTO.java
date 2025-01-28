package com.ms.shared.api.user.send.otp;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Accessors(prefix = "_")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class SendOTPRequestDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7146563550544641375L;
	
	@NotEmpty(message = "target is mandatory")
	private String _target;

	@NotEmpty (message = "type is mandatory")
	private String _type;

	@NotEmpty(message = " purpose is mandatory")
	private String _purpose;
}
