package com.ms.vidhyalebox.sharedapi;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

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
