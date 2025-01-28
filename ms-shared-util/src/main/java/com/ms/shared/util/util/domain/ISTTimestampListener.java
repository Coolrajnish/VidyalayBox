package com.ms.shared.util.util.domain;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ISTTimestampListener {

	@PrePersist
	public void prePersist(GenericEntity entity) {
		ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
		ZoneOffset istOffset = OffsetDateTime.now(istZoneId).getOffset();

		entity.setCreatedAt(OffsetDateTime.now(istOffset));
		entity.setUpdatedAt(OffsetDateTime.now(istOffset));
	}

	@PreUpdate
	public void preUpdate(GenericEntity entity) {
		ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
		ZoneOffset istOffset = OffsetDateTime.now(istZoneId).getOffset();

		entity.setUpdatedAt(OffsetDateTime.now(istOffset));
	}
}
