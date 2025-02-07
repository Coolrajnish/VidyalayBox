package com.ms.vidhyalebox.orgclient;

import com.ms.vidhyalebox.sharedapi.OrgSignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface IOrgClientService extends IGenericService<GenericEntity, Long> {
    public GenericDTO signup(OrgSignupRequestDTO orgSignupRequestDTO, String token);
    public boolean isMobileNumberExist(final String phoneNumber);
    public boolean isEmailAlreadyExist(final String emailAddress);
    public void logout();
}
