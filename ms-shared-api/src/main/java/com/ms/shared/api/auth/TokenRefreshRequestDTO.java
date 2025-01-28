package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class TokenRefreshRequestDTO extends GenericDTO {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -764319496091704915L;
	
	private String _refreshToken;
}
