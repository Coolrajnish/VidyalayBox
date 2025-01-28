package com.ms.shared.api.referral;

import com.ms.shared.api.generic.GenericDTO;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReferralResponseDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4875900863109650619L;
	
	private String _referralType; //Email, SMS, WhatsApp
	
	private String _referralTarget; //Email-Id, PhoneNumber
	
}
