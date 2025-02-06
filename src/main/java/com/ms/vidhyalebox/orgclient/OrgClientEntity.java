package com.ms.vidhyalebox.orgclient;

import com.ms.shared.util.util.domain.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ms_org_client")
public class OrgClientEntity extends GenericEntity {

    @Column(name = "orgUniqId")
    private String orgUniqId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @EqualsAndHashCode.Include
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "address")
    private String address;

//    @Column(name = "country")
//    private String country;
//
//    @Column(name = "state")
//    private String state;
//
//    @Column(name = "district")
//    private String district;
//
//    @Column(name = "area_pin")
//    private Integer areaPin;
//
//    @Column(name = "web_site_url")
//    private String webSiteUrl;
//
//
//    @Column(name = "pwd")
//    private String password;

    // Additional fields for account status, if necessary
    @Column(name = "is_active")
    private boolean isActive = true; // Default to active

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true; // Default to non-locked

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true; // Default to non-expired

//    @Column(name = "is_credentials_non_expired")
//    private boolean isCredentialsNonExpired = true; // Default to non-expired

}
