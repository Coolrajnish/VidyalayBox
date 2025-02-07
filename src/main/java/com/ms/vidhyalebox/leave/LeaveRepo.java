package com.ms.vidhyalebox.leave;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface LeaveRepo extends GenericRepo<LeaveEntity, Long> {
}
