package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "filterData")
@AllArgsConstructor
public class FilterDataDTO extends GenericDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object key;
	private Object shortValue;
	private Object longValue;

}
