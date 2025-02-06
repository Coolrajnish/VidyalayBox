package com.ms.vidhyalebox.parent;

import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.auth.JwtTokenProvider;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.role.RoleEntity;
import com.ms.vidhyalebox.role.RoleRepo;
import com.ms.vidhyalebox.user.UserMapperNormal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImpl  extends GenericService<GenericEntity, Long> implements IParentService{

    @Override
    public JpaRepository getRepo() {
        return _iParentRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return _parentMapperNormal;
    }
    private final IParentRepo _iParentRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final ParentMapperNormal _parentMapperNormal;
    private final RoleRepo roleRepo;


     public ParentServiceImpl(IParentRepo iParentRepo, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserMapperNormal userMapperNormal, ParentMapperNormal parentMapperNormal, RoleRepo roleRepo) {
            _iParentRepo = iParentRepo;
            this.authenticationManager = authenticationManager;
            this.jwtTokenProvider = jwtTokenProvider;
            this.passwordEncoder = passwordEncoder;
            _parentMapperNormal = parentMapperNormal;
            this.roleRepo = roleRepo;

        }
    public String login(String phoneNumber, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password)
        );
        return jwtTokenProvider.generateToken(phoneNumber);
    }

    public GenericDTO signup(ParentSignupRequestDTO parentSignupRequestDTO) {
        // Encode the password
        parentSignupRequestDTO.setPassword(passwordEncoder.encode(parentSignupRequestDTO.getPassword()));
        // Fetch the RoleEntity by role name (e.g., "ROLE_SCHOOL_ADMIN")
        RoleEntity role = roleRepo.findByName(parentSignupRequestDTO.getParentRole())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role specified"));

        ParentEntity parentEntity = (ParentEntity) _parentMapperNormal.dtoToEntity(parentSignupRequestDTO);

        // Set the role in the OrgClientEntity
       // parentEntity.setRole(role);

        // Save the OrgClientEntity with the assigned role
        ParentEntity saveEntity = _iParentRepo.save(parentEntity);
        GenericDTO genericDTO = _parentMapperNormal.entityToDto(saveEntity);
        return genericDTO;
    }

   /* public ParentEntity signup(ParentEntity parentEntity) {
		parentEntity.setPassword(passwordEncoder.encode(parentEntity.getPassword()));
		return _iParentRepo.save(parentEntity);
	}*/
//    public boolean isEmailAlreadyExist(final String email) {
//        return _iParentRepo.existsByEmail(email);
//    }
//
//    public boolean isMobileNumberExist(final String mobileNumber) {
//        return _iParentRepo.existsByMobileNumber(mobileNumber);
//    }
    public void logout() {
        // Handle logout if needed (e.g., invalidate tokens on client-side).
    }

    public UserDetails loadUserByUsername(String username) {

        return null;
    }
}
