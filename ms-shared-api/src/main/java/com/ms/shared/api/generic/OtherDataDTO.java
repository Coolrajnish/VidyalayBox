package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonRootName(value = "OthersData")
public class OtherDataDTO {
	@JsonProperty(value = "data")
	List<GenericDTO> genericDTO = new ArrayList<>();
}
