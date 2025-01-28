package com.ms.shared.api.user.subscription;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserPlanSubscriptionRequestDTO extends GenericDTO {

    @NotNull
    private Long _subscriptionPlanId;
    @NotNull
    private BigDecimal _actualSubscriptionPlanPrice;
    @NotNull
    private BigDecimal _finalSubscriptionPlanPrice;
    private boolean _isDiscountCouponApplied;
    private String _referralDiscountCoupon;
    private Long _discountCouponId;
    private String _discountCoupon;
    private boolean _isRewardPointsApplied;
    private Integer _redeemedRewardPoints;
    private Long _rewardPointTransactionId;
    private BigDecimal _discountAmount;
    private BigDecimal _discountedSubscriptionPlanPrice;
    private Map<String, BigDecimal> _taxAmounts;
}
