package com.ms.vidhyalebox.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.ms.vidhyalebox.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private Boolean isAccountNonLocked;
	private Boolean isAccountNonExpired;
	private Boolean isCredentialsNonExpired;
	private Boolean isActive;
	private String identity;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(final Long id, final String username, final String password, final Collection<? extends GrantedAuthority> authorities,
						 final Boolean isActive, final Boolean isAccountNonLocked,
						 final Boolean isAccountNonExpired, final Boolean isCredentialsNonExpired, final String identity) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.identity = identity;
	}

	// Build method for UserEntity
	public static UserPrincipal build(final UserEntity userEntity) {
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(userEntity.getRole()));

		return new UserPrincipal(
				userEntity.getId(),
				userEntity.getMobileNumber(),
				userEntity.getPassword(),
				authorities,
				userEntity.isActive(),
				userEntity.isAccountNonLocked(),
				userEntity.isAccountNonExpired(),
				userEntity.isCredentialsNonExpired(),
				userEntity.getSchool().getOrgUniqId()
		);
	}

	// Overloaded build method for OrgClientEntity
//	public static UserPrincipal build(final OrgClientEntity orgClientEntity) {
//		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(orgClientEntity.getRoleEntity().getName()));
//
//		return new UserPrincipal(
//				orgClientEntity.getId(), // Ensure OrgClientEntity has an ID
//				orgClientEntity.getEmailAddress(), // Use email as username
//				orgClientEntity.getPassword(),
//				authorities,
//				orgClientEntity.isActive(), // Assuming OrgClientEntity has similar fields
//				orgClientEntity.isAccountNonLocked(), // Assuming OrgClientEntity has similar fields
//				orgClientEntity.isAccountNonExpired(), // Assuming OrgClientEntity has similar fields
//				orgClientEntity.isCredentialsNonExpired() // Assuming OrgClientEntity has similar fields
//		);
//	}
//
//
//	// Overloaded build method for TeacherEntity
//	public static UserPrincipal build(final TeacherEntity teacherEntity) {
//		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(teacherEntity.getRoleEntity().getName()));
//
//		return new UserPrincipal(
//				teacherEntity.getId(), // Ensure OrgClientEntity has an ID
//				teacherEntity.getMobileNumber(), // Use Phone as username
//				teacherEntity.getPassword(),
//				authorities,
//				teacherEntity.isActive(), // Assuming OrgClientEntity has similar fields
//				teacherEntity.isAccountNonLocked(), // Assuming OrgClientEntity has similar fields
//				teacherEntity.isAccountNonExpired(), // Assuming OrgClientEntity has similar fields
//				teacherEntity.isCredentialsNonExpired() // Assuming OrgClientEntity has similar fields
//		);
//	}
//
//	// Overloaded build method for StaffEntity
//	public static UserPrincipal build(final StaffEntity staffEntity) {
//		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(staffEntity.getRoleEntity().getName()));
//
//		return new UserPrincipal(
//				staffEntity.getId(), // Ensure OrgClientEntity has an ID
//				staffEntity.getMobileNumber(), // Use Phone as username
//				staffEntity.getPassword(),
//				authorities,
//				staffEntity.isActive(), // Assuming OrgClientEntity has similar fields
//				staffEntity.isAccountNonLocked(), // Assuming OrgClientEntity has similar fields
//				staffEntity.isAccountNonExpired(), // Assuming OrgClientEntity has similar fields
//				staffEntity.isCredentialsNonExpired() // Assuming OrgClientEntity has similar fields
//		);
//	}
//
//		public static UserPrincipal build(final ParentEntity parentEntity) {
//			List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(parentEntity.getRoleEntity().getName()));
//
//			return new UserPrincipal(
//					parentEntity.getId(), // Ensure OrgClientEntity has an ID
//					parentEntity.getMobileNumber(), // Use Phone as username
//					parentEntity.getPassword(),
//					authorities,
//					parentEntity.isActive(), // Assuming OrgClientEntity has similar fields
//					parentEntity.isAccountNonLocked(), // Assuming OrgClientEntity has similar fields
//					parentEntity.isAccountNonExpired(), // Assuming OrgClientEntity has similar fields
//					parentEntity.isCredentialsNonExpired() // Assuming OrgClientEntity has similar fields
//			);
//		}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public Long getId() {
		return this.id;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isActive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal user = (UserPrincipal) o;
		return Objects.equals(id, user.id);
	}
}
