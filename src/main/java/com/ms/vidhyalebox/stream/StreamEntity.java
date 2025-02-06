package com.ms.vidhyalebox.stream;

import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "stream")
public class StreamEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "stream_name")
    private String streamName;

}
