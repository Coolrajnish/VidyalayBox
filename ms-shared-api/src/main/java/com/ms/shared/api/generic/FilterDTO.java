package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "filters")
public class FilterDTO extends GenericDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filterKey;
	private String filterName;
	private Integer sortingOrder;
	private String applyToColumn;
	@JsonProperty(value = "filterData")
	List<Object> genericDTO = new ArrayList<>();
	
	public FilterDTO() {
	}

	public FilterDTO(FilterDTO filterDTO) {
		this.filterKey = filterDTO.getFilterKey();
		this.filterName = filterDTO.getFilterName();
		this.sortingOrder = filterDTO.getSortingOrder();
		this.applyToColumn = filterDTO.getApplyToColumn();
		this.genericDTO = filterDTO.getGenericDTO();
	}


}
