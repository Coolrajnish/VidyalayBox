package com.ms.shared.api.user.subscription;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserPlanSubscriptionResponseDTO extends GenericDTO {

    private Long _subscriptionPlanId;

    private Long _paymentOrderId;

    private String _paymentGatewayOrderId;

    private String _status;
}
