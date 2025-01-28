package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonRootName(value = "modal")
public class ModalDTO {
	@JsonProperty(value = "filters")
	private Set<GenericDTO> filters = new HashSet<>();
	
	@JsonProperty(value = "data")
	private List<GenericDTO> data = new ArrayList<>();
	
	@JsonProperty(value = "othersData")
	private OtherDataDTO otherDTO;
}
