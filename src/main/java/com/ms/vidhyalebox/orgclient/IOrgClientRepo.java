package com.ms.vidhyalebox.orgclient;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface IOrgClientRepo extends GenericRepo<OrgClientEntity,Long> {
    Optional<OrgClientEntity> findByEmailAddress(String email);

    Optional<OrgClientEntity> findByOrgUniqId(String orgclientid);

    Boolean existsByEmailAddress(final String email);

    boolean existsByMobileNumber(final String mobileNumber);
}
