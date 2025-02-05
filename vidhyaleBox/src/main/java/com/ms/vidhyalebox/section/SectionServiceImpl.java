package com.ms.vidhyalebox.section;

import com.ms.shared.util.util.bl.GenericService;
import com.ms.shared.util.util.bl.IMapperNormal;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.repo.GenericRepo;
import com.ms.vidhyalebox.medium.MediumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends GenericService<GenericEntity, Long> implements SectionService{

    private  final SectionRepo sectionRepo;
    private final SectionMapperNormal sectionMapperNormal;

    public SectionServiceImpl(SectionRepo sectionRepo, SectionMapperNormal sectionMapperNormal) {
        this.sectionRepo = sectionRepo;
        this.sectionMapperNormal = sectionMapperNormal;
    }

    @Override
    public JpaRepository getRepo() {
        return sectionRepo;
    }

    @Override
    public IMapperNormal getMapper() {
        return sectionMapperNormal;
    }

    public Page<SectionEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = null;
        if(sortBy.isEmpty()){
            pageable = PageRequest.of(page, size);
        } else {
            pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        }
        if(!orgId.isEmpty() ){
            return sectionRepo.search(orgId, searchText, pageable);
        } else {
            return sectionRepo.findAll(pageable);
        }
    }
}
