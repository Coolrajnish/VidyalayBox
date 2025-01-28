package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "filterData")
public class FilterDataDTOWithParent extends FilterDataDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object parentLinkObject;

	public FilterDataDTOWithParent(Object key, Object shortName, Object longName, Object parentLinkDesc) {
		super(key, shortName, longName);
		this.parentLinkObject = parentLinkDesc;
	}

}
