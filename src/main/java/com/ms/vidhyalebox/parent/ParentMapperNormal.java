package com.ms.vidhyalebox.parent;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.orgclient.OrgClientMapper;
import com.ms.vidhyalebox.sharedapi.ParentSignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.ParentSignupResponseDTO;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.user.IUserRepo;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class ParentMapperNormal implements IMapperNormal {

    private final IOrgClientRepo orgClientRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrgClientMapper orgClientMapper;
    private final IUserRepo userRepo;

    public ParentMapperNormal(IOrgClientRepo orgClientRepository, PasswordEncoder passwordEncoder, OrgClientMapper orgClientMapper, IUserRepo userRepo) {
        this.orgClientRepository = orgClientRepository;
        this.passwordEncoder = passwordEncoder;
        this.orgClientMapper = orgClientMapper;
        this.userRepo = userRepo;
    }


    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        ParentEntity entity = genericEntity == null ? new ParentEntity() : (ParentEntity) genericEntity;
        ParentSignupRequestDTO parentDTO = (ParentSignupRequestDTO) genericDto;

        OrgClientEntity org = orgClientRepository.findByOrgUniqId(parentDTO.getOrgUniqueId()).get();
        Optional<UserEntity> users = userRepo.findByEmailOrMobileNumber(null, parentDTO.getParentMobile());
        UserEntity userEntity ;
        if(!users.isPresent()) {
            UserEntity user = new UserEntity();
            user.setAddress(parentDTO.getAddress());
            user.setEmail(parentDTO.getParentEmail());
            user.setFirstName(parentDTO.getParentFirstName());
            user.setLastName(parentDTO.getParentLastName());
            user.setMobileNumber(parentDTO.getParentMobile());
            user.setPassword(passwordEncoder.encode(parentDTO.getParentMobile()));  //As of now mobile is the password for parent
            user.setSchool(org);
            user.setRole("ROLE_PARENT");
            userEntity = userRepo.save(user);
        } else {
            userEntity =  users.get();
        }
        entity.setCustomfield1("test");
        entity.setUser(userEntity);
        entity.setSchool(org);
        return entity;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        ParentEntity clientEntity = (ParentEntity) genericEntity;

        ParentSignupResponseDTO signupResponseDTO = new ParentSignupResponseDTO();
        signupResponseDTO.setName(clientEntity.getUser().getFirstName().concat(" ").concat(clientEntity.getUser().getLastName()));
        signupResponseDTO.setEmailAddress(clientEntity.getUser().getEmail());
        signupResponseDTO.setMobileNumber(clientEntity.getUser().getMobileNumber());
        signupResponseDTO.setIsActive(clientEntity.getUser().isActive());
        signupResponseDTO.setAccountNonExpired(clientEntity.getUser().isAccountNonExpired()); // If email verification logic is available, use it
        signupResponseDTO.setAccountNonLocked(clientEntity.getUser().isAccountNonLocked()); // If phone verification logic is available, use it
        signupResponseDTO.setOrgUniqId(clientEntity.getSchool().getOrgUniqId()); // Set default or as per actual logic

        return signupResponseDTO;
    }
}
