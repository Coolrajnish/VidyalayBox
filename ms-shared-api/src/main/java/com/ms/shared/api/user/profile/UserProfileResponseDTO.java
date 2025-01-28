package com.ms.shared.api.user.profile;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.subscription.plan.UserSubscriptionPlanInfo;
import com.ms.shared.api.taxmaster.TaxMasterDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserProfileResponseDTO extends GenericDTO {

	/**
	 *
	 */
	private static final long serialVersionUID = -9002721633650048812L;

	private String _firstName;
	private String _lastName;
	private String _gender;
	private String _phoneNumber;
	private String _email;
	private String _language;
	private String _city;
	private String _dateOfBirth;
	private String _status;
	private String _nationality;
	private boolean _active;
	private boolean _isPhoneNumberVerified;
	private boolean _isEmailAddressVerified;
	private String _currency;
	private String _imageLink;
	private String _occupation;
	private String _socialMediaLink;
	private String _country;
	private String _countryCode;
	private String _isdCode;
	private String _incomeSource;
	private String _identityProvider;
	private String _federatedUserId;

//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	private List<DiscountCouponUsageDetailsDTO> _availableDiscountCoupons;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<TaxMasterDTO> _taxMasterDTOList;

//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	private RewardPointTransactionDTO _rewardPointInfo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserSubscriptionPlanInfo _userSubscriptionPlanInfo;
}
