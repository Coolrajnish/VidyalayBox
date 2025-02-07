package com.ms.vidhyalebox.util.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NativeEntity extends GenericEntity {
	private Long key;
	private Object object;
}