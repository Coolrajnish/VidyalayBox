package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.validator.DateOfBirth;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TeacherSignupRequestDTO extends GenericDTO {


    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqueId;

    @NotEmpty(message = "Firstname Name is mandatory")
    private String firstname; // Updated to align with the `name` field in `OrgClientEntity`

    @NotEmpty(message = "Lastname Name is mandatory")
    private String lastname; // Updated to align with the `name` field in `OrgClientEntity`

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have a minimum of 8 characters")
    private String password;

    @NotEmpty(message = "Email address is mandatory")
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

    @NotNull(message = "BirthDate is mandatory")
    @Past(message = "The date of birth must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateOfBirth
    private Date dateOfBirth;

    private String schoolName;
}
