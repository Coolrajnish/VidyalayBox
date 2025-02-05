package com.ms.vidhyalebox.medium;

import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface MediumService extends IGenericService<GenericEntity, Long> {

    public Page<MediumEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);

}
