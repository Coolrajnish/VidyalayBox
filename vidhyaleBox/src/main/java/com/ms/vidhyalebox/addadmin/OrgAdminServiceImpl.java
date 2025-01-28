//package com.ms.vidhyalebox.addadmin;
//
//import com.ms.shared.util.util.bl.GenericService;
//import com.ms.shared.util.util.bl.IMapperNormal;
//import com.ms.shared.util.util.domain.GenericEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrgAdminServiceImpl extends GenericService<GenericEntity, Long> implements OrgAdminService{
//
//    private final OrgAdminRepo orgAdminRepo;
//
//    private final OrgAdminMapper orgAdminMapper;
//
//    public OrgAdminServiceImpl(OrgAdminRepo orgAdminRepo, OrgAdminMapper orgAdminMapper) {
//        this.orgAdminRepo = orgAdminRepo;
//        this.orgAdminMapper = orgAdminMapper;
//    }
//
//    @Override
//    public JpaRepository getRepo() {
//        return orgAdminRepo;
//    }
//
//    @Override
//    public IMapperNormal getMapper() {
//        return orgAdminMapper;
//    }
//}
