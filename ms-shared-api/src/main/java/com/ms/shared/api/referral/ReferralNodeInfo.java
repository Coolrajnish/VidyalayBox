package com.ms.shared.api.referral;

import com.ms.shared.api.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(prefix = "_")
@ToString(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ReferralNodeInfo extends GenericDTO {

    private String _referrerName;
    private Long _rewardPoint;
    private List<ReferralNodeInfo> _children = new ArrayList<>();
}
