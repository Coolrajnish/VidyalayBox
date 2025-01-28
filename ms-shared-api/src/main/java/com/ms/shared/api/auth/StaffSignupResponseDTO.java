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
public class StaffSignupResponseDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;

	private String _orgUniqId;
	private String _name;
	private String _mobileNumber;
	private String _emailAddress;
	private String _address;

	private Boolean _isActive;
	private boolean _isAccountNonLocked;
	private boolean _isAccountNonExpired;
	private boolean _isCredentialsNonExpired;

	// If you still need these fields, keep them, otherwise you can remove them:
//	private String _orgName;    // could overlap with _name
//	private String _lastName;
//	private String _dateOfBirth;
//	private String _gender;
//	private String _email;      // could overlap with _emailAddress
//	private String _phoneNumber; // could overlap with _mobileNumber
//	private boolean _isEmailVerified;
//	private boolean _isPhoneVerified;
//	private String _identityProvider;
}
