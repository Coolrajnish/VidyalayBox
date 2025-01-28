package com.ms.shared.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Accessors(prefix = "_")
@Data
@AllArgsConstructor
public class ForgetPasswordRequestDTO {

   @NotEmpty(message = "PhoneNumber is mandatory")
   private String _phoneNumber;

   @NotEmpty(message = "EmailAddress is mandatory")
   @Email
   private String _emailAddress;
}
