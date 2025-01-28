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
public class PaymentOrderCallbackRequestDTO extends GenericDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595877135430957840L;

	@JsonProperty(value = "razorpay_payment_id")
	private String _razorpayPaymentId;

	@JsonProperty(value = "razorpay_order_id")
	private String _razorpayOrderId;

	@JsonProperty(value = "razorpay_signature")
	private String _razorpaySignature;

	@JsonProperty(value = "payment_order_id")
	private String _paymentOrderId;

}
