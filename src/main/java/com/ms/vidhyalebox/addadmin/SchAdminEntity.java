package com.ms.vidhyalebox.addadmin;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sch_admin")
public class SchAdminEntity extends GenericEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long adminId;


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
