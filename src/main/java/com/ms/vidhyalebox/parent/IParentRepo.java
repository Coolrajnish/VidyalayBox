package com.ms.vidhyalebox.parent;

import com.ms.shared.util.util.repo.GenericRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IParentRepo extends GenericRepo<ParentEntity, Long> {

//ParentEntity findByMobileNumber(final String mobileNumber);
//	Boolean existsByEmail(final String email);
	//boolean existsByMobileNumber(final String mobileNumber);

//	@Query("SELECT s FROM ParentEntity s WHERE (:email IS NULL OR s.email = :email) AND (:mobileNumber IS NULL OR s.mobileNumber = :mobileNumber)")
   // Optional<ParentEntity> findByEmailOrMobileNumber(@Param("email") String email, @Param("mobileNumber") String mobileNumber);

  //  List<ParentEntity> findByOrgUniqId(String id);
}
