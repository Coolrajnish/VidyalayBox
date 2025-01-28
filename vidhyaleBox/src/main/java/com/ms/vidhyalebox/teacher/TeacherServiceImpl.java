//package com.ms.vidhyalebox.teacher;
//
//import com.ms.shared.api.auth.SignupRequestDTO;
//import com.ms.shared.api.auth.TeacherSignupRequestDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.shared.util.util.bl.GenericService;
//import com.ms.shared.util.util.bl.IMapperNormal;
//import com.ms.shared.util.util.domain.GenericEntity;
//import com.ms.vidhyalebox.auth.JwtTokenProvider;
//import com.ms.vidhyalebox.role.RoleEntity;
//import com.ms.vidhyalebox.role.RoleRepo;
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
//public class TeacherServiceImpl extends GenericService<GenericEntity, Long> implements ITeacherService {
//
//	private final ITeacherRepo teacherRepo;
//	private final AuthenticationManager authenticationManager;
//	private final JwtTokenProvider jwtTokenProvider;
//	private final PasswordEncoder passwordEncoder;
//
//	private final TeacherMapperNormal teacherMapperNormal;
//	private final RoleRepo roleRepo;
//
//
//	public TeacherServiceImpl(ITeacherRepo teacherRepo, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, TeacherMapperNormal teacherMapperNormal, RoleRepo roleRepo) {
//        this.teacherRepo = teacherRepo;
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.passwordEncoder = passwordEncoder;
//        this.teacherMapperNormal = teacherMapperNormal;
//		this.roleRepo = roleRepo;
//    }
//
//   /* public UserEntity signup(UserEntity userEntity) {
//		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//		return userRepository.save(userEntity);
//	}*/
//
//	@Override
//	public boolean isEmailAlreadyExist(String email) {
//		return teacherRepo.existsByEmail(email);
//	}
//
//	public GenericDTO signup(TeacherSignupRequestDTO teacherSignupRequestDTO) {
//		// Encode the password
//		teacherSignupRequestDTO.setPassword(passwordEncoder.encode(teacherSignupRequestDTO.getPassword()));
//		// Fetch the RoleEntity by role name (e.g., "ROLE_SCHOOL_ADMIN")
//		RoleEntity role = roleRepo.findByName(teacherSignupRequestDTO.getRole())
//				.orElseThrow(() -> new IllegalArgumentException("Invalid role specified"));
//
//		TeacherEntity teacherEntity = (TeacherEntity) teacherMapperNormal.dtoToEntity(teacherSignupRequestDTO);
//
//		// Set the role in the OrgClientEntity
//		teacherEntity.setRoleEntity(role);
//
//		// Save the OrgClientEntity with the assigned role
//		TeacherEntity saveEntity = teacherRepo.save(teacherEntity);
//		GenericDTO genericDTO = teacherMapperNormal.entityToDto(saveEntity);
//		return genericDTO;
//	}
//
//	public String login(String phoneNumber, String password) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(phoneNumber, password)
//		);
//		return jwtTokenProvider.generateToken(phoneNumber);
//	}
//
//	public void logout() {
//		// Handle logout if needed (e.g., invalidate tokens on client-side).
//	}
//
//	public UserDetails loadUserByUsername(String username) {
//
//        return null;
//    }
//
//	public boolean isMobileNumberExist(final String mobileNumber) {
//		return teacherRepo.existsByMobileNumber(mobileNumber);
//	}
//
//	@Override
//	public JpaRepository getRepo() {
//		return teacherRepo;
//	}
//
//	@Override
//	public IMapperNormal getMapper() {
//		return teacherMapperNormal;
//	}
//}
