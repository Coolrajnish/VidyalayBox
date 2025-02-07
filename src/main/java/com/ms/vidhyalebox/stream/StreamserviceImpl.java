package com.ms.vidhyalebox.stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
}
