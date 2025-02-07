package com.ms.vidhyalebox.sharedapi.generic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

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