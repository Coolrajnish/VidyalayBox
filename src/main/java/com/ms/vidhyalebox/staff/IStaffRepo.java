package com.ms.vidhyalebox.staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface IStaffRepo extends GenericRepo<StaffEntity, Long> {
//
//	StaffEntity findByMobileNumber(final String mobileNumber);
//
//	Boolean existsByEmail(final String email);
//
//	boolean existsByMobileNumber(final String MobileNumber);
//
//	@Query("SELECT s FROM StaffEntity s WHERE (:email IS NULL OR s.email = :email) AND (:mobileNumber IS NULL OR s.mobileNumber = :mobileNumber)")
//	Optional<StaffEntity> findByEmailOrMobileNumber(@Param("email") String email,
//			@Param("mobileNumber") String mobileNumber);
//
//	List<StaffEntity> findByOrgUniqId(String id);
//
//	Optional<UserEntity> findByReferralCodeOrPhoneNumberOrEmailAddress(final String referralCode,
//			final String phoneNumber, final String emailAddress);
//
//	Optional<UserEntity> findByReferralCode(final String referralCode);
//
//	Optional<UserEntity> findByEmailAddressOrPhoneNumber(final String email, final String phoneNumber);
//
//	Optional<UserEntity> findByFederatedUserIdOrEmailAddress(String id, String emailAddress);
//
//	@Query(value = "SELECT * FROM users WHERE RIGHT(phone_number, 10) = :phoneNumber "
//			+ "OR email_address = :email", nativeQuery = true)
//	Optional<UserEntity> findByPhoneNumberOrEmailAddress(@Param("phoneNumber") String phoneNumber,
//			@Param("email") String email);
	
  @Query(value = "SELECT * FROM staff p JOIN users u on u.id = p.user_id WHERE u.org_uniq_id =:orgId  AND LOWER(u.first_name) LIKE LOWER(CONCAT(:searchText, '%') OR LOWER(u.last_name) LIKE LOWER(CONCAT( :searchText, '%') OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(u.mobile_number) LIKE LOWER(CONCAT( :searchText, '%'))", nativeQuery = true)
  public Page<StaffEntity> search(@Param("orgId") String orgId,
          @Param("searchText") String searchText,
          Pageable pageable);
}
