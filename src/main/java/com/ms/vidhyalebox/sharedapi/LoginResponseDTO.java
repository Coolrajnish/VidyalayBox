package com.ms.vidhyalebox.sharedapi;

import java.util.List;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class LoginResponseDTO extends GenericDTO {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String _token;
//	private final String _refreshToken;
	private final Long _userId;
	private final Long _orgId;
	private final String _orgUniqId;
	private final List<String> _roles;
	private final boolean _active;
	private final boolean _accountNonLocked;
	private final boolean _accountNonExpired;
	private final boolean _credentialNonExpired;
	private final Long _expirationMS;
	private final String _message;

}
