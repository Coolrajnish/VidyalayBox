package com.ms.vidhyalebox.orgclient;

import com.ms.shared.util.util.repo.GenericRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrgClientRepo extends GenericRepo<OrgClientEntity,Long> {
    Optional<OrgClientEntity> findByEmailAddress(String email);

    Optional<OrgClientEntity> findByOrgUniqId(String orgclientid);

    Boolean existsByEmailAddress(final String email);

    boolean existsByMobileNumber(final String mobileNumber);
}
