package com.ms.shared.api.subscription.plan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionPlanDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3877643924490532925L;

	@NotEmpty(message = "Enter plan name")
	private String planName;
    @NotNull(message = "Enter planPrice")
	private BigDecimal planPricing;
	private Integer noOfPrivateStrategy;
	private Integer noOfPublicStrategy;
	private Integer continousCheckingFrequency;
	private Integer noOfStrategyDeploy;
	private String exchangeDataFeed;
	private Integer complimentaryTestAllowedPerMonth;
	private boolean subscriberAllowed;
	private String currency;
}
