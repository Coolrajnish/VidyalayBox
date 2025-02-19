package com.ms.vidhyalebox.studentattendance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface StudentAttendanceRepo extends GenericRepo<StudentAttendanceEntity, Long> {
	@Query(value = "SELECT * FROM student_attendance sa JOIN student s ON s.id = sa.student_id JOIN users u ON u.id = s.user_id WHERE u.org_uniq_id =:orgId  AND (u.first_name IS NULL OR LOWER(u.first_name) LIKE LOWER(CONCAT( :searchText, '%'))) OR (u.last_name IS NULL OR LOWER(u.last_name) LIKE LOWER(CONCAT( :searchText, '%'))) OR (u.identity_provider IS NULL OR LOWER(u.identity_provider) LIKE LOWER(CONCAT( :searchText, '%'))) OR (s.admission_number IS NULL OR LOWER(s.admission_number) LIKE LOWER(CONCAT( :searchText, '%')))", nativeQuery = true)
	public Page<StudentAttendanceEntity> search(@Param("orgId") String orgId, @Param("searchText") String searchText,
			Pageable pageable);
}
