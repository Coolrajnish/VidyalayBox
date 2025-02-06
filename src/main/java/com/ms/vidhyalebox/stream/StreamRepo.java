package com.ms.vidhyalebox.stream;

import com.ms.shared.util.util.repo.GenericRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;

@Repository
public interface StreamRepo extends GenericRepo<StreamEntity, Long> {
}
