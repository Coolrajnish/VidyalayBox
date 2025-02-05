package com.ms.vidhyalebox.stream;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.repo.GenericRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
}
