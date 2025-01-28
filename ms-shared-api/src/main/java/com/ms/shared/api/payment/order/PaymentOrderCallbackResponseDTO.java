package com.ms.shared.api.payment.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(prefix = "_")
@Getter
@Setter
@Builder
@ToString
public class PaymentOrderCallbackResponseDTO extends GenericDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595877135430957840L;
	
	@JsonProperty(value = "id")
	private Long _id;

}
