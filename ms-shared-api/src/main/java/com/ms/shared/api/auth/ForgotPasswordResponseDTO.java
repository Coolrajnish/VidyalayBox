package com.ms.shared.api.auth;

import com.ms.shared.api.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
public class ForgotPasswordResponseDTO extends GenericDTO {

    private Long _verificationRequestId;
    private boolean _requestProcessed;
}
