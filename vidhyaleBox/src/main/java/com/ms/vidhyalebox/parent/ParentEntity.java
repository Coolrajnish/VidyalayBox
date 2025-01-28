package com.ms.vidhyalebox.parent;

import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "parent")
public class ParentEntity extends GenericEntity {

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "userId", nullable = false)
        private UserEntity user;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "orgUniqId", nullable = false)
        private OrgClientEntity school;

        @Column(name = "customfield1")
        private String customfield1;

        @Column(name = "customfield2")
        private String customfield2;

}
