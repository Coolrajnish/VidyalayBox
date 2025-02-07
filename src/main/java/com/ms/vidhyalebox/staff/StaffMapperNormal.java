package com.ms.vidhyalebox.staff;

import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class StaffMapperNormal implements IMapperNormal {

	@Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
        StaffEntity entity = genericEntity == null ? new StaffEntity() : (StaffEntity) genericEntity;
//
//        StaffSignupRequestDTO signupRequestDTO = (StaffSignupRequestDTO) genericDto;
//
//        entity.setFirstName(signupRequestDTO.getFirstname());
//        entity.setLastName(signupRequestDTO.getLastname());
//        entity.setPassword(signupRequestDTO.getPassword() == null ? entity.getPassword() : signupRequestDTO.getPassword());
//        entity.setEmail(signupRequestDTO.getEmailAddress());
//        entity.setMobileNumber(signupRequestDTO.getMobileNumber().concat(signupRequestDTO.getIsdCode()));
//        entity.setAddress(signupRequestDTO.getAddress());
//       //  Setting temporary values for account status
//        entity.setAccountNonLocked(true);
//        entity.setAccountNonExpired(true);
//        entity.setCredentialsNonExpired(true);

        return entity;
    }

	@Override
    public GenericDTO entityToDto(GenericEntity genericEntity) {
        StaffEntity clientEntity = (StaffEntity) genericEntity;

//        StaffSignupResponseDTO signupResponseDTO = new StaffSignupResponseDTO();
//        signupResponseDTO.setName(clientEntity.getFirstName().concat(" ").concat(clientEntity.getLastName()));
//        signupResponseDTO.setEmailAddress(clientEntity.getEmail());
//        signupResponseDTO.setMobileNumber(clientEntity.getMobileNumber());
//        signupResponseDTO.setIsActive(clientEntity.isActive());
//        signupResponseDTO.setAccountNonExpired(clientEntity.isAccountNonExpired()); // If email verification logic is available, use it
//        signupResponseDTO.setAccountNonLocked(clientEntity.isAccountNonLocked());  //If phone verification logic is available, use it
//        signupResponseDTO.setOrgUniqId(clientEntity.getOrgUniqId());//  Set default or as per actual logic

        return null;
    }
}