package com.ms.vidhyalebox.sharedapi.stream;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StreamDTO extends GenericDTO {

    @NotEmpty(message = "Org unique ID is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Stream name is mandatory")
    private String streamName;

}
