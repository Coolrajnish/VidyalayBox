package com.ms.shared.util.util.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

/*
 *Company:mithlaSoftech Creation Date:2024
 *@author sumit kumar
 *@version 1.0
 */
@NoRepositoryBean
public interface GenericRepo<GenericEntity, Object> extends JpaRepository<GenericEntity, Object> {



}
