package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StaffSignupRequestDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqueId;

    @NotEmpty(message = "Firstname Name is mandatory")
    private String firstname; // Updated to align with the `name` field in `OrgClientEntity`

    @NotEmpty(message = "Lastname Name is mandatory")
    private String lastname; // Updated to align with the `name` field in `OrgClientEntity`

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have a minimum of 8 characters")
    private String password;

//    @NotEmpty(message = "Email address is mandatory")
    @Email
    private String emailAddress; // Updated to match `emailAddress` in `OrgClientEntity`

    @NotEmpty(message = "Mobile Number is mandatory")
    private String mobileNumber; // Updated to match `mobileNumber` in `OrgClientEntity`

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Role is mandatory")
    private String role;

    @NotEmpty(message = "Gender is mandatory")
    private String gender;

    @NotNull(message = "ISD Code is mandatory")
    private String isdCode;


}
