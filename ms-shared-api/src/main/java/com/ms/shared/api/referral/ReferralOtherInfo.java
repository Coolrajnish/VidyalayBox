package com.ms.shared.api.referral;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
public class ReferralOtherInfo {

    private String _keyA;
    private String _keyB;
}
