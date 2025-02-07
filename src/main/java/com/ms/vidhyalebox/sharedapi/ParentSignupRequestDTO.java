package com.ms.vidhyalebox.sharedapi;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ParentSignupRequestDTO extends GenericDTO {

  //  @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqueId;

  //  @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have a minimum of 8 characters")
    private String password;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Role is mandatory")
    private String parentRole;

    @NotEmpty(message = "Parent email is mandatory")
    private String parentEmail;

    @NotEmpty(message = "Parent first name is mandatory")
    private String parentFirstName;

    @NotEmpty(message = "Parent last name is mandatory")
    private String parentLastName;

    @NotEmpty(message = "Parent mobile is mandatory")
    private String parentMobile;

    @NotEmpty(message = "Parent gender is mandatory")
    private String parentGender;

    private String parentImage;

}


