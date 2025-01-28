//package com.ms.vidhyalebox.addadmin;
//
//import com.ms.shared.util.util.domain.GenericEntity;
//import com.ms.vidhyalebox.role.RoleEntity;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//
//@Data
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
//@Entity
//@Table(name = "org_admin")
//public class OrgAdminEntity extends GenericEntity {
//
//    @Column(name = "org_uniq_id")
//    private String orgUniqId;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @EqualsAndHashCode.Include
//    @Column(name = "mobile_number")
//    private String mobileNumber;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "pwd")
//    private String password;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    private RoleEntity roleEntity;
//
//}
