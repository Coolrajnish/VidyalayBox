package com.ms.shared.api.auth.stream;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StreamDTO extends GenericDTO {

    @NotEmpty(message = "Org unique ID is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Stream name is mandatory")
    private String streamName;

}
