package com.xcompany.xproject.common.web.starter.test.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long>, JpaSpecificationExecutor<UserDetail> {
	
}
