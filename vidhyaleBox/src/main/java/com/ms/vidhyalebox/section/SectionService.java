package com.ms.vidhyalebox.section;

import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import org.springframework.data.domain.Page;

public interface SectionService extends IGenericService<GenericEntity, Long> {

    public Page<SectionEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);

}
