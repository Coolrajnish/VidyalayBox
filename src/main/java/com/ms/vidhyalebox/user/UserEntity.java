package com.ms.vidhyalebox.user;

import java.time.LocalDateTime;
import java.util.Date;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class UserEntity extends GenericEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	//removing email as unique for now due to student
	@Column(name = "email")
	private String email;

//	@Column(name = "status")
//	private String status;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "gender")
	private String gender;

	@EqualsAndHashCode.Include
	@Column(name = "mobileNumber")
	private String mobileNumber;

	@Column(name = "pwd")
	private String password;

	@Column(name = "address")
	private String address;

	@Column(name = "image")
	private String image;
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "role_id", referencedColumnName = "id", unique = false)
//	private RoleEntity roleEntity;

	@Column(nullable = false)
	private String role;

	@Column(name = "is_active", nullable = false)
	private boolean isActive = true; // Default to active
	
	@Column(name = "is_account_non_locked")
	private boolean isAccountNonLocked = true;
	
	@Column(name = "is_account_non_expired")
	private boolean isAccountNonExpired = true;
	
	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired = true;

	@Column(name= "federated_user_id")
	private String federatedUserId;

	@Column(name = "identity_provider")
	private String identityProvider;

	@Column(name = "verificationToken")
	private String verificationToken;

	@Column(name = "expiryDate")
	private LocalDateTime expiryDate;

	@Column(name = "isVerified")
	private boolean isVerified;

}
