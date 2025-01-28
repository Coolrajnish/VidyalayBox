package com.ms.vidhyalebox.orgclient;

import com.ms.shared.api.auth.OrgSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;

public interface IOrgClientService extends IGenericService<GenericEntity, Long> {
    public GenericDTO signup(OrgSignupRequestDTO orgSignupRequestDTO);
    public boolean isMobileNumberExist(final String phoneNumber);
    public boolean isEmailAlreadyExist(final String emailAddress);
    public void logout();
}
