package com.ms.shared.api;

import lombok.Builder;
import lombok.Data;

@Data
public class GenericPatchRequest {

	private String op;
	private String path;
	private Object value;
}
