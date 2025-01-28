package com.ms.shared.util.util.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
/*
 *Company:mithlaSoftech Creation Date:2024
 *@author sumit kumar
 *@version 1.0
 */
@NoRepositoryBean
public interface GenericRepo<GenericEntity, Object> extends JpaRepository<GenericEntity, Object> {

}
