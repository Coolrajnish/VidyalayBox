package com.ms.vidhyalebox.user;


import com.ms.shared.util.util.repo.GenericRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IUserRepo extends GenericRepo<UserEntity, Long> {


   // UserEntity findByMobileNumber(String mobileNumber);
//	Boolean existsByEmailAddress(final String email);
//	boolean existsByMobileNumber(final String mobileNumber);
   Optional<UserEntity> findByVerificationToken( String token);

    boolean existsByIdentityProvider(String admissionId);

    UserEntity findByUsername(String username);
  //  UserEntity findByIdentityProvider(String admissionId);

 //   List<UserEntity> findByOrgUniqId(String id);
    @Query("SELECT s FROM UserEntity s WHERE (:email IS NULL OR s.email = :email) AND (:mobileNumber IS NULL OR s.mobileNumber = :mobileNumber)")
    Optional<UserEntity> findByEmailOrMobileNumber(@Param("email") String email, @Param("mobileNumber") String mobileNumber);

    //boolean existsByPhoneNumber(String mobileNumber);
//	Optional<UserEntity> findByReferralCodeOrPhoneNumberOrEmailAddress(final String referralCode, final String phoneNumber, final String emailAddress);
//	Optional<UserEntity> findByReferralCode(final String referralCode);
//	Optional<UserEntity> findByEmailAddressOrPhoneNumber(final String email, final String phoneNumber);
//	Optional<UserEntity> findByFederatedUserIdOrEmailAddress(String id, String emailAddress);
	
//	@Query(value = "SELECT * FROM users WHERE RIGHT(phone_number, 10) = :phoneNumber " +
//            "OR email_address = :email", nativeQuery = true)
//Optional<UserEntity> findByPhoneNumberOrEmailAddress(@Param("phoneNumber") String phoneNumber,
//                                                  @Param("email") String email);
}



