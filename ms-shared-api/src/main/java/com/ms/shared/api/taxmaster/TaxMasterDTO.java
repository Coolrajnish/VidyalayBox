package com.ms.shared.api.taxmaster;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.shared.api.generic.GenericDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Accessors(prefix = "_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaxMasterDTO extends GenericDTO {

    private BigDecimal _percentage;

    private Date _startDate;

    private Date _endDate;

    private String _taxDescription;

    private Date _createdAt;

    private Date _updatedAt;

    private Long _createdBy;

    private Long _updatedBy;
}
