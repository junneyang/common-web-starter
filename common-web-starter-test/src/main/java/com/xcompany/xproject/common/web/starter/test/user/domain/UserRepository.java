package com.xcompany.xproject.common.web.starter.test.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	public User findById(String id);
	
//	@Query(value = "SELECT "
//			+ "new com.xcompany.xproject.common.web.starter.test.user.service.QueryUserSerializer(u.id, u.name, u.createtime, u.updatetime, ud.address) "
//			+ "FROM User u, UserDetail ud "
//			+ "WHERE u.id = ud.user AND u.name LIKE %?1% AND u.createtime BETWEEN ?2 AND ?3"//,
//			//countQuery = "SELECT COUNT(u.id) FROM User u WHERE u.name LIKE %?1% AND u.createtime BETWEEN ?2 AND ?3"//,
//			//nativeQuery = true
//			)
//	public Page<QueryUserSerializer> listUserNative(@Param("name") String name, 
//			@Param("starttime") Timestamp starttime, @Param("endtime") Timestamp endtime,
//			Pageable pageable
//			);
}
