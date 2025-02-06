//package com.ms.vidhyalebox.teacher;
//
//
//import com.ms.shared.util.util.repo.GenericRepo;
//import com.ms.vidhyalebox.staff.StaffEntity;
//import com.ms.vidhyalebox.user.UserEntity;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Repository
//public interface ITeacherRepo extends GenericRepo<TeacherEntity, Long> {
//
//
//	Boolean existsByEmail(final String email);
//	boolean existsByMobileNumber(final String mobileNumber);
//
//   // Optional<TeacherEntity> findByEmailOrMobileNumber(String username);
//
//	    @Query("SELECT s FROM TeacherEntity s WHERE (:email IS NULL OR s.email = :email) AND (:mobileNumber IS NULL OR s.mobileNumber = :mobileNumber)")
//		Optional<TeacherEntity> findByEmailOrMobileNumber(@Param("email") String email, @Param("mobileNumber") String mobileNumber);
//
//    List<TeacherEntity> findByOrgUniqId(String id);
//
//    //boolean existsByPhoneNumber(String phoneNumber);
////	Optional<UserEntity> findByReferralCodeOrPhoneNumberOrEmailAddress(final String referralCode, final String phoneNumber, final String emailAddress);
////	Optional<UserEntity> findByReferralCode(final String referralCode);
////	Optional<UserEntity> findByEmailAddressOrPhoneNumber(final String email, final String phoneNumber);
////	Optional<UserEntity> findByFederatedUserIdOrEmailAddress(String id, String emailAddress);
//
////	@Query(value = "SELECT * FROM users WHERE RIGHT(phone_number, 10) = :phoneNumber " +
////            "OR email_address = :email", nativeQuery = true)
////Optional<UserEntity> findByPhoneNumberOrEmailAddress(@Param("phoneNumber") String phoneNumber,
////                                                  @Param("email") String email);
//}
//
//
//
