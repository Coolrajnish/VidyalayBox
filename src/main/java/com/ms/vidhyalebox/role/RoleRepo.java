package com.ms.vidhyalebox.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(final String roleName) ;

}
