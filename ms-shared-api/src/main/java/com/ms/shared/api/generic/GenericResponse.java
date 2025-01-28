package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GenericResponse {

	String code;

	@JsonProperty(value = "paginationInfo")
	PaginationInfoDTO paginationInfo;
	
	@JsonProperty(value = "modal")
	ModalDTO modalDTO;

	@JsonProperty(value = "notifications")
	List<Notification> notifications;
}
