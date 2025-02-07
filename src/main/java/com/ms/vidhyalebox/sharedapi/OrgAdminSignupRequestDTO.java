package com.ms.vidhyalebox.sharedapi;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OrgAdminSignupRequestDTO  extends GenericDTO{

//    @NotEmpty(message = "Org unique Id is mandatory")
//    private String orgUniqueId;

    @NotEmpty(message = "Firstname is mandatory")
    private String firstname; // Updated to align with the `name` field in `OrgClientEntity`

    @NotEmpty(message = "Lastname is mandatory")
    private String lastname; // Updated to align with the `name` field in `OrgClientEntity`

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have a minimum of 8 characters")
    private String password;

    //    @NotEmpty(message = "Email address is mandatory")
    @Email
    private String emailAddress; // Updated to match `emailAddress` in `OrgClientEntity`

    @NotEmpty(message = "Mobile Number is mandatory")
    private String mobileNumber; // Updated to match `mobileNumber` in `OrgClientEntity`

    @NotEmpty(message = "Role is mandatory")
    private String role;
}
