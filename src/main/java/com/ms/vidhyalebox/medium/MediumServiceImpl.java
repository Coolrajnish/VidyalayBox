package com.ms.vidhyalebox.medium;

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
public class MediumServiceImpl extends GenericService<GenericEntity, Long> implements  MediumService{

    private final MediumMapper mediumMapper;

    private final MediumRepo mediumRepo;

    public MediumServiceImpl(MediumMapper mediumMapper, MediumRepo mediumRepo) {
        this.mediumMapper = mediumMapper;
        this.mediumRepo = mediumRepo;
    }


    @Override
    public JpaRepository getRepo() {
        return mediumRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return mediumMapper;
    }

    @Transactional
    public Page<MediumEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = null;
        if(sortBy.isEmpty()){
             pageable = PageRequest.of(page, size);
        } else {
             pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        }
        if(!orgId.isEmpty() ){
            return mediumRepo.search(orgId, searchText, pageable);
        } else {
            return mediumRepo.findAll(pageable);
        }
    }
}
