package com.ms.vidhyalebox.orgclient;

import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.addadmin.SchAdminEntity;
import com.ms.vidhyalebox.addadmin.SchAdminRepo;
import com.ms.vidhyalebox.role.RoleEntity;
import com.ms.vidhyalebox.role.RoleRepo;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.IUserService;
import com.ms.vidhyalebox.user.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrgClientServiceImpl extends GenericService<GenericEntity, Long> implements IOrgClientService {


    private final IOrgClientRepo orgClientRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrgClientMapper orgClientMapper;
    private final IUserRepo userRepo;
    private final SchAdminRepo schAdminRepo;
    //private final IUserService userService;

    private final RoleRepo roleRepo;

    @Autowired
    public OrgClientServiceImpl(IOrgClientRepo orgClientRepository,
                                PasswordEncoder passwordEncoder, OrgClientMapper orgClientMapper, RoleRepo roleRepo, IUserRepo userRepo, SchAdminRepo schAdminRepo) {
        this.orgClientRepository = orgClientRepository;
        this.passwordEncoder = passwordEncoder;
        this.orgClientMapper = orgClientMapper;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.schAdminRepo = schAdminRepo;
    }

   //Transaction and error handling
    public GenericDTO signup(OrgSignupRequestDTO orgSignupRequestDTO, String token) {
        // Encode the password
        //  orgSignupRequestDTO.setPassword(passwordEncoder.encode(orgSignupRequestDTO.getPassword()));
        // Fetch the RoleEntity by role name (e.g., "ROLE_SCHOOL_ADMIN")
        RoleEntity role = roleRepo.findByName(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getRole())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role specified"));

        OrgClientEntity orgClientEntity = (OrgClientEntity) orgClientMapper.dtoToEntity(orgSignupRequestDTO);

        // Set the role in the OrgClientEntity
        //   orgClientEntity.setRoleEntity(role);
        // Save the OrgClientEntity with the assigned role
        OrgClientEntity saveEntity = orgClientRepository.save(orgClientEntity);
        if (orgSignupRequestDTO.getOrgAdminSignupRequestDTO() != null) {
            UserEntity user = new UserEntity();
            user.setAddress(orgSignupRequestDTO.getAddress());
            user.setEmail(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getEmailAddress());
            user.setFirstName(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getFirstname());
            user.setLastName(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getLastname());
            user.setMobileNumber(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getMobileNumber());
            user.setPassword(passwordEncoder.encode(orgSignupRequestDTO.getOrgAdminSignupRequestDTO().getMobileNumber()));
            user.setSchool(saveEntity);
            user.setRole(role.getName());
            user.setVerified(false);
            user.setVerificationToken(token);
            UserEntity userEntity = userRepo.save(user);
            SchAdminEntity admin = new SchAdminEntity();
            admin.setCustomfield1("test1");
            admin.setUser(userEntity);
            admin.setSchool(saveEntity);
            SchAdminEntity schadmin = schAdminRepo.save(admin);
        }
        GenericDTO genericDTO = orgClientMapper.entityToDto(saveEntity);
        return genericDTO;
    }

    public boolean isEmailAlreadyExist(final String emailAddress) {
        return orgClientRepository.existsByEmailAddress(emailAddress);
    }

    public boolean isMobileNumberExist(final String mobileNumber) {
        return orgClientRepository.existsByMobileNumber(mobileNumber);
    }

    public void logout() {
        // Handle logout if needed (e.g., invalidate tokens on client-side).
    }


    @Override
    public JpaRepository getRepo() {
        return orgClientRepository;
    }

    @Override
    public IMapperNormal getMapper() {
        return orgClientMapper;
    }
}
