package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName(value = "notification")
public class Notification {
	String noificationCode;
	String notificationDescription;
	Object metadata;
}
