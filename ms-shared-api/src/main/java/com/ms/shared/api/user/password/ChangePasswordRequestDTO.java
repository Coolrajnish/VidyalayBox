package com.ms.shared.api.user.password;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChangePasswordRequestDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8634265547157524828L;
	
	@NotEmpty
	@Size(min = 8, message = "Current Password should have minimum 8 character")
	private String _currentPassword;
	
	@NotEmpty
	@Size(min = 8, message = "New Password should have minimum 8 character")
	private String _newPassword;
	
	@NotEmpty (message = "confirmPassword is mandatory")
	private String _confirmPassword;
	
	@NotEmpty(message = "verificationRequestId is mandatory")
	private Long _verificationRequestId;
}
