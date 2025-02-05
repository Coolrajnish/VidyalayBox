package com.ms.vidhyalebox.orgclient;

import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.auth.OrgSignupResponseDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.stereotype.Service;


@Service
public class OrgClientMapper implements IMapperNormal {

    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        OrgClientEntity entity = genericEntity == null ? new OrgClientEntity() : (OrgClientEntity) genericEntity;

        OrgSignupRequestDTO signupRequestDTO = (OrgSignupRequestDTO) genericDto;

        entity.setName(signupRequestDTO.getName());
     //   entity.setPassword(signupRequestDTO.getPassword() == null ? entity.getPassword() : signupRequestDTO.getPassword());
        entity.setEmailAddress(signupRequestDTO.getEmailAddress());
        entity.setMobileNumber(signupRequestDTO.getMobileNumber());
     //   entity.setPhoneNumber(signupRequestDTO.getPhoneNumber());
//        entity.setCountry(signupRequestDTO.getCountry());
//        entity.setState(signupRequestDTO.getState());
//        entity.setDistrict(signupRequestDTO.getDistrict());
//        entity.setAreaPin(convertToInteger(signupRequestDTO.getAreaPin()));
//        entity.setWebSiteUrl(signupRequestDTO.getWebSiteUrl());
        entity.setAddress(signupRequestDTO.getAddress());

        // Setting temporary values for account status
       // entity.setAccountNonLocked(true);
       // entity.setAccountNonExpired(true);
      //  entity.setCredentialsNonExpired(true);
     //   entity.setsignupRequestDTO.getOrgAdminSignupRequestDTO());
        return entity;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        OrgClientEntity clientEntity = (OrgClientEntity) genericEntity;

        OrgSignupResponseDTO signupResponseDTO = new OrgSignupResponseDTO();

        signupResponseDTO.setName(clientEntity.getName());
        signupResponseDTO.setEmailAddress(clientEntity.getEmailAddress());
      //  signupResponseDTO.setPhoneNumber(clientEntity.getPhoneNumber());
        signupResponseDTO.setIsActive(clientEntity.isActive());
        signupResponseDTO.setAccountNonExpired(clientEntity.isAccountNonExpired()); // If email verification logic is available, use it
        signupResponseDTO.setAccountNonLocked(clientEntity.isAccountNonLocked()); // If phone verification logic is available, use it
        signupResponseDTO.setOrgUniqId(clientEntity.getOrgUniqId()); // Set default or as per actual logic
       // signupResponseDTO.setOrgAdminSignupRequestDTO(clientEntity.getOrgAdminEntity());

        return signupResponseDTO;
    }
}
