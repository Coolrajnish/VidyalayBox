//package com.ms.vidhyalebox;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ms.vidhyalebox.student.StudentEntity;
//import com.ms.vidhyalebox.student.StudentRepo;
//
//import java.util.List;
//
//@Service
//public class BulkInsertService {
//
//    @Autowired
//    private StudentRepo repository;
//
//    @Async("taskExecutor")
//    @Transactional
//    public void bulkInsert(List<StudentEntity> entities) {
//        repository.saveAll(entities);
//    }
//}
