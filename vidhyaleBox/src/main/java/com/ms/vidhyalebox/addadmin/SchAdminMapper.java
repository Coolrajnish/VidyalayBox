package com.ms.vidhyalebox.addadmin;

import com.ms.shared.api.auth.OrgAdminSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.stereotype.Service;

@Service
public class SchAdminMapper implements IMapperNormal {
    @Override
    public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity){
   //     SchAdminEntity entity = genericEntity == null ? new SchAdminEntity() : (SchAdminEntity) genericEntity;

//        OrgAdminSignupRequestDTO orgAdminSignupRequestDTO = (OrgAdminSignupRequestDTO) genericDto;
//
//        entity.setFirstName(orgAdminSignupRequestDTO.getFirstname());
//        entity.setLastName(orgAdminSignupRequestDTO.getLastname());
//        entity.setEmail(orgAdminSignupRequestDTO.getEmailAddress());
//        entity.setPassword(orgAdminSignupRequestDTO.getPassword());
//        entity.setOrgUniqId(orgAdminSignupRequestDTO.getOrgUniqueId());
//        entity.setMobileNumber(orgAdminSignupRequestDTO.getMobileNumber());

        return null;
    }

    @Override
    public GenericDTO entityToDto(GenericEntity genericEntity){

//     OrgAdminEntity orgAdminEntity = (OrgAdminEntity) genericEntity;
//
//     OrgAdminSignupRequestDTO adminSignupRequestDTO = new OrgAdminSignupRequestDTO();
//
//        adminSignupRequestDTO.setFirstname(orgAdminEntity.getFirstName());
//        adminSignupRequestDTO.setLastname(orgAdminEntity.getLastName());
//        adminSignupRequestDTO.setEmailAddress(orgAdminEntity.getEmail());
//        adminSignupRequestDTO.setPassword(orgAdminEntity.getPassword());
//        adminSignupRequestDTO.setOrgUniqueId(orgAdminEntity.getOrgUniqId());
//        adminSignupRequestDTO.setMobileNumber(orgAdminEntity.getMobileNumber());

        return  null;
    }

}
