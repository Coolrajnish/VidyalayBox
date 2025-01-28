package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

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
	private final List<String> _roles;
	private final boolean _active;
	private final boolean _accountNonLocked;
	private final boolean _accountNonExpired;
	private final boolean _credentialNonExpired;
	private final Long _expirationMS;
	private final String _message;
	

}
