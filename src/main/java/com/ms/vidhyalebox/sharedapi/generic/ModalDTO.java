package com.ms.vidhyalebox.sharedapi.generic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

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
