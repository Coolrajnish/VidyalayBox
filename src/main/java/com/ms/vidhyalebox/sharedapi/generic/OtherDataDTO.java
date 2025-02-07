package com.ms.vidhyalebox.sharedapi.generic;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName(value = "OthersData")
public class OtherDataDTO {
	@JsonProperty(value = "data")
	List<GenericDTO> genericDTO = new ArrayList<>();
}
