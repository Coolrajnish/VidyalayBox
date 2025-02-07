package com.ms.vidhyalebox.sharedapi;

import lombok.Data;

@Data
public class GenericPatchRequest {

	private String op;
	private String path;
	private Object value;
}
