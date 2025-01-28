package com.ms.shared.api.google;

import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.user.device.DeviceInfoDTO;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleSignInRequest extends GenericDTO {

    @NotEmpty
    private String _code;

    @Valid
    @NotNull
    private DeviceInfoDTO _deviceInfo;
    private String _referralCode;
}
