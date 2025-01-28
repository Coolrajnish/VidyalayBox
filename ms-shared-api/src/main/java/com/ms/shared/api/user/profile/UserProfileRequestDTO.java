package com.ms.shared.api.user.profile;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserProfileRequestDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -234041844077063397L;
	
	private String _currency;
	private String _imageLink;
	private String _incomeSource;
	private String _language;
	private String _nationality;
	private String _city;
	private String _occupation;
	private String _socialMediaLink;
	private String _country;
	private String _countryCode;
	private String _isdCode;
	private String _federatedUserId;
	private String _phoneNumber;
	private Date _dateOfBirth;
	private String _gender;
}
