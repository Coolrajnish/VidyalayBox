package com.ms.vidhyalebox.sharedapi;

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
public class TokenRefreshRequestDTO extends GenericDTO {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -764319496091704915L;
	
	private String _refreshToken;
}
