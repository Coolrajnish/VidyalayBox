package com.ms.shared.api.payment.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Accessors(prefix = "_")
@Data
@RequiredArgsConstructor
@ToString
public class PaymentOrderRequestDTO extends GenericDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 313986057284719777L;
	
	@JsonProperty(value = "planSubscriptionId")
	private Long _planSubscriptionId;
	
	@JsonProperty(value = "receiptId")
	private String _receiptId;
	
	@JsonProperty(value = "amount")
    private BigDecimal _amount;
	
	@JsonProperty(value = "currency")
	private String _currency;
	
	@JsonProperty(value = "notes", required = false)
	private String _notes;

	@JsonProperty(value = "minimumApplicableDiscountAmount")
	private BigDecimal _minimumApplicableDiscountAmount;

	@JsonProperty(value = "taxOnPricingPlan")
	private BigDecimal _taxOnPricingPlan;

	@JsonProperty(value = "isFreePlan", required = false)
	private boolean _isFreePlan;
	
}
