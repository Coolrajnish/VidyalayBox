package com.ms.shared.api.referral;

import com.ms.shared.api.generic.GenericDTO;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class ReferralRequestDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1913799949264573284L;
	
	@NotEmpty
	private String _referralType; //Email, SMS, WhatsApp
	
	@NotEmpty
	private String _referralTarget; //Email-Id, PhoneNumber
}
