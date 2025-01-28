package com.ms.shared.api.auth;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserPasswordResetDTO {

    private String oldPassword;
    private String newPassword;
}