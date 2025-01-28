package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Accessors(prefix = "_")
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTO extends GenericDTO {

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have minimum 8 character")
    private String _newpassword;

    @NotEmpty(message = "Password is mandatory")
    private String _confirmPassword;

    @NotEmpty(message = "PhoneNumber is mandatory")
    private String _phoneNumber;

    @NotEmpty(message = "EmailAddress is mandatory")
    @Email
    private String _emailAddress;

    private Long _verificationRequestId;
}
