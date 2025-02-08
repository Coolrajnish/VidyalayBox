package com.ms.vidhyalebox.stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class StreamserviceImpl extends GenericService<GenericEntity, Long> implements StreamService {

    private final StreamRepo streamRepo;
    private final StreamMapperNormal streamMapperNormal;

    public StreamserviceImpl(StreamRepo streamRepo, StreamMapperNormal streamMapperNormal) {
        this.streamRepo = streamRepo;
        this.streamMapperNormal = streamMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return streamRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return streamMapperNormal;
    }

    @Transactional
	@Override
	public Page<StreamEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		// TODO Auto-generated method stub
		Pageable pageable = null;
		if(sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		}else {
			pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc")?
					Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());	
		}
		if(!orgId.isEmpty()) {
			return streamRepo.search(orgId, searchText, pageable);
		}else {
			return streamRepo.findAll(pageable);
		}
	}
}
