//package com.ms.vidhyalebox.staff;
//
//import com.ms.shared.api.auth.StaffSignupRequestDTO;
//import com.ms.shared.api.auth.TeacherSignupRequestDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.shared.util.util.bl.GenericService;
//import com.ms.shared.util.util.bl.IMapperNormal;
//import com.ms.shared.util.util.domain.GenericEntity;
//import com.ms.vidhyalebox.auth.JwtTokenProvider;
//import com.ms.vidhyalebox.role.RoleEntity;
//import com.ms.vidhyalebox.role.RoleRepo;
//import com.ms.vidhyalebox.teacher.TeacherEntity;
//import com.ms.vidhyalebox.user.IUserRepo;
//import com.ms.vidhyalebox.user.IUserService;
//import com.ms.vidhyalebox.user.UserEntity;
//import com.ms.vidhyalebox.user.UserMapperNormal;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StaffServiceImpl extends GenericService<GenericEntity, Long> implements IStaffService {
//
//	private final IStaffRepo _iStaffRepo;
//	private final AuthenticationManager authenticationManager;
//	private final JwtTokenProvider jwtTokenProvider;
//	private final PasswordEncoder passwordEncoder;
//
//	private final StaffMapperNormal _staffMapperNormal;
//
//	private final RoleRepo roleRepo;
//
//    public StaffServiceImpl(IStaffRepo iStaffRepo, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserMapperNormal userMapperNormal, StaffMapperNormal staffMapperNormal, RoleRepo roleRepo) {
//        _iStaffRepo = iStaffRepo;
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.passwordEncoder = passwordEncoder;
//        _staffMapperNormal = staffMapperNormal;
//		this.roleRepo = roleRepo;
//    }
//	public GenericDTO signup(StaffSignupRequestDTO staffSignupRequestDTO) {
//		// Encode the password
//		staffSignupRequestDTO.setPassword(passwordEncoder.encode(staffSignupRequestDTO.getPassword()));
//		// Fetch the RoleEntity by role name (e.g., "ROLE_SCHOOL_ADMIN")
//		RoleEntity role = roleRepo.findByName(staffSignupRequestDTO.getRole())
//				.orElseThrow(() -> new IllegalArgumentException("Invalid role specified"));
//
//		StaffEntity staffEntity = (StaffEntity) _staffMapperNormal.dtoToEntity(staffSignupRequestDTO);
//
//		// Set the role in the OrgClientEntity
//		staffEntity.setRoleEntity(role);
//
//		// Save the OrgClientEntity with the assigned role
//		StaffEntity saveEntity = _iStaffRepo.save(staffEntity);
//		GenericDTO genericDTO = _staffMapperNormal.entityToDto(saveEntity);
//		return genericDTO;
//	}
////    public UserEntity signup(UserEntity userEntity) {
////		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
////		return _iStaffRepo.save(userEntity);
////	}
//
//	public String login(String phoneNumber, String password) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(phoneNumber, password)
//		);
//		return jwtTokenProvider.generateToken(phoneNumber);
//	}
//
//
//	public boolean isEmailAlreadyExist(final String email) {
//		return _iStaffRepo.existsByEmail(email);
//	}
//
//	public boolean isMobileNumberExist(final String MobileNumber) {
//		return _iStaffRepo.existsByMobileNumber(MobileNumber);
//	}
//	public void logout() {
//		// Handle logout if needed (e.g., invalidate tokens on client-side).
//	}
//
//	public UserDetails loadUserByUsername(String username) {
//
//        return null;
//    }
//
//	@Override
//	public JpaRepository getRepo() {
//		return _iStaffRepo;
//	}
//
//	@Override
//	public IMapperNormal getMapper() {
//		return _staffMapperNormal;
//	}
//}
