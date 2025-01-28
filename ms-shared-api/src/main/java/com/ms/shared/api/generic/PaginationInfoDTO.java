package com.ms.shared.api.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PaginationInfoDTO {
	@JsonProperty(value = "totalElements")
	Integer count;
	@JsonProperty(value = "pageNo")
	Integer pageNo;
	@JsonProperty(value = "pageSize")
	Integer pageSize;
	@JsonProperty(value = "totalPages")
	Integer totalPages;

	public PaginationInfoDTO() {
		this.pageNo = 1;
		this.pageSize = -1;
		this.totalPages = -1;
	}
}
