//package com.ms.vidhyalebox.dashboard;
//
//import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
//import com.ms.vidhyalebox.parent.IParentRepo;
//import com.ms.vidhyalebox.parent.ParentEntity;
//import com.ms.vidhyalebox.staff.IStaffRepo;
//import com.ms.vidhyalebox.staff.StaffEntity;
//import com.ms.vidhyalebox.teacher.ITeacherRepo;
//import com.ms.vidhyalebox.teacher.TeacherEntity;
//import com.ms.vidhyalebox.user.IUserRepo;
//import com.ms.vidhyalebox.user.UserEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class DashboardServiceImpl implements DashboardService{
//
//    private final IParentRepo _IParentRepo;
//    private final IStaffRepo _IStaffRepo;
//    private final ITeacherRepo _ITeacherRepo;
//    private final IUserRepo _IUserRepo;
//
//    public DashboardServiceImpl(IParentRepo iParentRepo, IStaffRepo iStaffRepo, ITeacherRepo iTeacherRepo, IUserRepo iUserRepo) {
//        _IParentRepo = iParentRepo;
//        _IStaffRepo = iStaffRepo;
//        _ITeacherRepo = iTeacherRepo;
//        _IUserRepo = iUserRepo;
//    }
//
//
//
//
//    public Map<String,Integer> getOrgMemberDetails(String id){
//        Map<String , Integer>details =new HashMap<>();
//        List<StaffEntity> staffEntities = _IStaffRepo.findByOrgUniqId(id);
//        List<TeacherEntity> teacherEntities = _ITeacherRepo.findByOrgUniqId(id);
//        List<ParentEntity> parentEntities = _IParentRepo.findByOrgUniqId(id);
//        List<UserEntity> userEntities = _IUserRepo.findByOrgUniqId(id);
//
//        details.put("Staff",staffEntities.size());
//        details.put("Teacher" ,teacherEntities.size());
//        details.put("Parent",parentEntities.size());
//        details.put("Student",userEntities.size());
//        return details;
//    }
//}