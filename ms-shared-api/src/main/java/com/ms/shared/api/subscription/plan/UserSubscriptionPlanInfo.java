package com.ms.shared.api.subscription.plan;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(prefix = "_")
@Builder
@Getter
public class UserSubscriptionPlanInfo extends GenericDTO {

    private Long _subscriptionPlanId;
    private Integer _noOfPrivateStrategy;
    private Integer _noOfPublicStrategy;
    private String _subscriptionPlanName;
//    private List<UserStrategySubscriptionInfo> _userStrategySubscriptionInfos;
}
