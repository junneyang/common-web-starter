package com.xcompany.xproject.common.web.starter.test.user.domain;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustom {
	// Single Table
	@Autowired
	private UserRepository userRepository;
	
	// Multi Table
	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<User> listUser(final String name, 
			final Timestamp starttime, final Timestamp endtime,
			Pageable pageable) {
		
		Specification<User> specification = new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (null != name) {
					predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
				}
				if (null != starttime && null != endtime) {
					predicates.add(cb.between(root.get("createtime").as(Timestamp.class), starttime, endtime));
				}
				
//				Join<Object, Object> user = root.join("userDetail", JoinType.LEFT);
//				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//				query.distinct(true);
//				return query.getRestriction();
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
		Page<User> users = this.userRepository.findAll(specification , pageable);
		return users;
	}
	
    @SuppressWarnings("unchecked")
	public Page<Object> listUserCustom(final String name, 
			final Timestamp starttime, final Timestamp endtime,
			Pageable pageable) {
    	
//    	String sql = "SELECT "
//    			+ "u.id, u.name, u.createtime, u.updatetime, ud.address "
//    			+ "FROM tbl_user u "
//    			+ "LEFT OUTER JOIN tbl_user_detail ud "
//    			+ "ON u.id = ud.user "
//    			+ "WHERE u.name LIKE ?1 AND u.createtime BETWEEN ?2 AND ?3";
    	
    	StringBuilder sql = new StringBuilder();
    	StringBuilder sql_count = new StringBuilder();
    	
    	String common = "FROM tbl_user u "
    			+ "LEFT OUTER JOIN tbl_user_detail ud "
    			+ "ON u.id = ud.user "
    			+ "WHERE 1 = 1 ";
    	
    	sql.append("SELECT u.id, u.name, u.createtime, u.updatetime, ud.address ");
    	sql_count.append("SELECT COUNT(*) ");
    	
    	sql.append(common);
    	sql_count.append(common);

    	if (null != name) {
    		String tmp = "AND u.name LIKE ?1 ";
    		sql.append(tmp);
    		sql_count.append(tmp);
    	}
    	if (null != starttime && null != endtime) {
    		String tmp = "AND u.createtime BETWEEN ?2 AND ?3 ";
    		sql.append(tmp);
    		sql_count.append(tmp);
    	}
    	     
        
		Query query = entityManager.createNativeQuery(sql.toString());
		Query countQuery = entityManager.createNativeQuery(sql_count.toString());
		
		if (null != name) {
			query.setParameter(1, "%" + name + "%");
			countQuery.setParameter(1, "%" + name + "%");
		}
		if (null != starttime && null != endtime) {
			query.setParameter(2, starttime);
			query.setParameter(3, endtime);
			countQuery.setParameter(2, starttime);
			countQuery.setParameter(3, endtime);
		}
		
		Long total = ((BigInteger)countQuery.getSingleResult()).longValue();
		
		query.setMaxResults(pageable.getPageSize());
		query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
		
//		List<QueryUserSerializer> results = new ArrayList<QueryUserSerializer>();
//		
//		List<?> rows = query.getResultList();
//		for (Object row : rows) {
//			Object[] cells = (Object[]) row;
//			String id = (String) cells[0];
//			String namet = (String) cells[1];
//			Timestamp createtime = (Timestamp) cells[2];
//			Timestamp updatetime = (Timestamp) cells[3];
//			String address = (String) cells[4];
//			results.add(new QueryUserSerializer(id, namet, createtime, updatetime, address));
//		}
		
		return new PageImpl<Object>(query.getResultList(), pageable, total);
    }
	
}
