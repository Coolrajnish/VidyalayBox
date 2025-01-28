package com.ms.shared.api.invoice.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.invoice.itemdetails.InvoiceItemDetailsDTO;
import com.ms.shared.api.invoice.taxdetails.InvoiceTaxDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InvoiceMasterDTO extends GenericDTO {

    private UUID _invoiceId;

    private String _invoiceDate;

    private String _invoiceAmount;

    private String _paymentStatus;

    private String _userId;

    private String _createdAt;

    private String _updatedAt;

    private String _createdBy;

    private String _updatedBy;

    private List<InvoiceItemDetailsDTO> _invoiceItemDetails;

    private List<InvoiceTaxDetailsDTO> _invoiceTaxDetails;

}
