package com.ms.shared.api.payment.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Accessors(prefix = "_")
@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class PaymentOrderResponseDTO extends GenericDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3053734772252623340L;
	
	@JsonProperty(value = "key")
	private String _key;
	
	@JsonProperty(value = "amount")
	private BigDecimal _amount;
	
	@JsonProperty(value = "currency")
	private String _currency;
	
	@JsonProperty(value = "businessName")
	private String _businessName;

	@JsonProperty(value = "description")
	private String _description;
	
	@JsonProperty(value = "image")
	private String _image;
	
	@JsonProperty(value = "orderId")
	private String _orderId;

	@JsonProperty(value = "callbackUrl")
	private String _callbackUrl;
	
	@JsonProperty(value = "customerName")
	private String _customerName;
	
	@JsonProperty(value = "email")
	private String _email;
	
	@JsonProperty(value = "contact")
	private String _contact;
	
	@JsonProperty(value = "address")
	private String _address;
	
}
