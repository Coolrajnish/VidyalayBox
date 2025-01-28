package com.ms.vidhyalebox.student;

import com.ms.shared.util.util.repo.GenericRepo;
import com.ms.vidhyalebox.shift.ShiftEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends GenericRepo<StudentEntity, Long> {
}
