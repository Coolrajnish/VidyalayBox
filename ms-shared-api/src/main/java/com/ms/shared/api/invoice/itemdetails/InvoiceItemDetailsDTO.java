package com.ms.shared.api.invoice.itemdetails;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InvoiceItemDetailsDTO extends GenericDTO {



    private String _subscriptionPlanMasterId;

    private String _planeName;

    private String _planDescription;

    private String _planQuantity;

    private String _planPrice;

    private String _planTotal;

    private String _planType;

    private String _createdAt;

    private String _updateAt;

    private String _createdBy;

    private String _updatedBy;

}
