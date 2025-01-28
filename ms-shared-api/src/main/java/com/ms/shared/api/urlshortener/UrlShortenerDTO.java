package com.ms.shared.api.urlshortener;

import com.ms.shared.api.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UrlShortenerDTO extends GenericDTO{
	

	private String _url;
	
	private String _shortUrl;
		
	private boolean _isLinkExpired;

}
