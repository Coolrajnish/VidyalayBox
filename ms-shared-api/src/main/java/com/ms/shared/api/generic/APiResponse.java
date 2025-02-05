package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class APiResponse<T> {

	@JsonProperty(value = "status")
	private String status;
	@JsonProperty(value = "message")
	private String message;
	@JsonProperty(value = "data")
	private T data;
	@JsonProperty(value = "metadata")
	private Object metadata;

	public APiResponse(String status, String message, T data, Object metadata) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.metadata = metadata;
	}

}
