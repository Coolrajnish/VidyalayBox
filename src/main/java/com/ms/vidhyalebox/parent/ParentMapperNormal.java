package com.ms.vidhyalebox.parent;

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

import jakarta.persistence.EntityNotFoundException;

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

        OrgClientEntity org = orgClientRepository.findByOrgUniqId(parentDTO.getOrgUniqueId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        UserEntity users  = genericEntity == null ? new UserEntity() : entity.getUser();
        if( parentDTO.getParentMobile() != null) {
        	 users = userRepo.findByEmailOrMobileNumber(null, parentDTO.getParentMobile()).get();
        }
       

        if (users != null) {
            UserEntity user = users;
            
            // Add null checks for partial updates
            if (parentDTO.getAddress() != null) {
                user.setAddress(parentDTO.getAddress());
            }
            if (parentDTO.getParentEmail() != null) {
                user.setEmail(parentDTO.getParentEmail());
            }
            if (parentDTO.getParentFirstName() != null) {
                user.setFirstName(parentDTO.getParentFirstName());
            }
            if (parentDTO.getParentLastName() != null) {
                user.setLastName(parentDTO.getParentLastName());
            }
            if (parentDTO.getParentMobile() != null) {
                user.setMobileNumber(parentDTO.getParentMobile());
                user.setPassword(passwordEncoder.encode(parentDTO.getParentMobile()));  // Mobile as password
            }
            if (org != null) {
                user.setSchool(org);
            }
            if(parentDTO.getParentRole() != null) {
            	 user.setRole(parentDTO.getParentRole());
            }
           

            users = userRepo.save(user);
        } 

        // Setting custom fields and associated entities
        if(entity.getCustomfield1() != null) {
        	 entity.setCustomfield1(parentDTO.getCustomfield1());
        }
       
        entity.setUser(users);
        if (org != null) {
            entity.setSchool(org);
        }

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
