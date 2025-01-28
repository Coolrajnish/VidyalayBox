package com.ms.shared.api.invoice.taxdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InvoiceTaxDetailsDTO extends GenericDTO {


    private String _taxAmount;
    private String _taxName;
}
