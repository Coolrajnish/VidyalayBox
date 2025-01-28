package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SignupResponseDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _firstName;
	private String _lastName;
	private String _dateOfBirth;
	private String _gender;
	//private String _email;
	//private String _mobileNumber;
	private boolean _isActive;
    private boolean _isEmailVerified;
    private boolean _isPhoneVerified;
	private String _admissionId;
}
