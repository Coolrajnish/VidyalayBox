package com.ms.vidhyalebox.sharedapi;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrgSignupRequestDTO extends GenericDTO {

    @NotEmpty(message = "Organization Name is mandatory")
    private String name; // Updated to align with the `name` field in `OrgClientEntity`

//    @NotEmpty(message = "Password is mandatory")
//    @Size(min = 8, message = "Password should have a minimum of 8 characters")
//    private String password;

    @NotEmpty(message = "Email address is mandatory")
    @Email
    private String emailAddress; // Updated to match `emailAddress` in `OrgClientEntity`

    @NotEmpty(message = "Mobile Number is mandatory")
    private String mobileNumber; // Updated to match `mobileNumber` in `OrgClientEntity`

//    @NotNull(message = "ISD Code is mandatory")
//    private String isdCode;

   // private String phoneNumber; // Keeping this optional for now
//
//    @NotEmpty(message = "Country is mandatory")
//    private String country;
//
//    @NotEmpty(message = "State is mandatory")
//    private String state;
//
//    @NotEmpty(message = "District is mandatory")
//    private String district;
//
//    @NotNull(message = "Area Pin is mandatory")
//    private String areaPin;
//
//    @NotEmpty(message = "Website URL is mandatory")
//    private String webSiteUrl;
//
    @NotEmpty(message = "Address is mandatory")
    private String address;

//    @NotEmpty(message = "Role is mandatory")
//    private String role;
    // Optional fields like referral code, ISD code, and country code can remain
//    private String referralCode;
//
//    private String countryCode;
//
    private OrgAdminSignupRequestDTO orgAdminSignupRequestDTO;
}
