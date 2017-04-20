package com.xcompany.xproject.common.web.starter.test.user.domain;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.xcompany.xproject.common.web.starter.test.user.serializers.UserDetailSerializer;
import com.xcompany.xproject.common.web.starter.test.user.serializers.UserSerializer;

@Repository
public class UserRepositoryCustom {
	// Single Table
	@Autowired
	private UserRepository userRepository;
	
	// Multi Table
	//@PersistenceContext
	//private EntityManager entityManager;
	// Replace JPA EntityManager With SessionFactory To Use The setResultTransformer
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Page<UserSerializer> listUser(final String name, 
			final Timestamp startTime, final Timestamp endTime,
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
				if (null != startTime && null != endTime) {
					predicates.add(cb.between(root.get("createTime").as(Timestamp.class), startTime, endTime));
				}
				
//				Join<Object, Object> user = root.join("userDetail", JoinType.LEFT);
//				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//				query.distinct(true);
//				return query.getRestriction();
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
		
		Page<User> users = this.userRepository.findAll(specification , pageable);
		List<UserSerializer> results = new ArrayList<UserSerializer>();
		for (User user : users ) {
			results.add(new UserSerializer(user.getId(), user.getName(), 
					user.getCreateTime(), user.getUpdateTime()));
		}
		
		return new PageImpl<UserSerializer>(results, pageable, users.getTotalElements());
	}
	
    @SuppressWarnings("unchecked")
	public Page<List<UserDetailSerializer>> listUserCustom(final String name, 
			final Timestamp startTime, final Timestamp endTime,
			Pageable pageable) {
    	
//    	String sql = "SELECT "
//    			+ "u.id, u.name, u.createtime, u.updatetime, ud.address "
//    			+ "FROM tbl_user u "
//    			+ "LEFT OUTER JOIN tbl_user_detail ud "
//    			+ "ON u.id = ud.user "
//    			+ "WHERE u.name LIKE ?1 AND u.createtime BETWEEN ?2 AND ?3";
    	
		
    	StringBuilder sql = new StringBuilder();
    	StringBuilder sql_count = new StringBuilder();
    	
    	String common = "FROM user u "
    			+ "LEFT OUTER JOIN user_detail ud "
    			+ "ON u.id = ud.user_id "
    			+ "WHERE 1 = 1 ";
    	
    	sql.append("SELECT u.id AS id, u.name AS name, u.create_time as createTime, u.update_time AS updateTime, ud.address AS address ");
    	sql_count.append("SELECT COUNT(*) ");
    	
    	sql.append(common);
    	sql_count.append(common);

    	if (null != name) {
    		String tmp = "AND u.name LIKE :name ";
    		sql.append(tmp);
    		sql_count.append(tmp);
    	}
    	if (null != startTime && null != endTime) {
    		String tmp = "AND u.create_time BETWEEN :startTime AND :endTime ";
    		sql.append(tmp);
    		sql_count.append(tmp);
    	}
    	Sort sort = pageable.getSort();
    	if (null != sort) {
    		String tmp = "ORDER BY " + sort.toString().replace(":", "");
			sql.append(tmp);
		}

    	     
    	SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
    	SQLQuery countQuery = sessionFactory.getCurrentSession().createSQLQuery(sql_count.toString());
    	
//		Query query = entityManager.createNativeQuery(sql.toString());
//		Query countQuery = entityManager.createNativeQuery(sql_count.toString());
		
		if (null != name) {
			query.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}
		if (null != startTime && null != endTime) {
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);
			countQuery.setParameter("startTime", startTime);
			countQuery.setParameter("endTime", endTime);
		}

		
		query.setMaxResults(pageable.getPageSize());
		query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
		
		
		//Long total = ((BigInteger)countQuery.getSingleResult()).longValue();
		Long total = ((BigInteger)countQuery.uniqueResult()).longValue();
		
//		List<QueryUserDetailSerializer> results = new ArrayList<QueryUserDetailSerializer>();
//		
//		List<?> rows = query.getResultList();
//		for (Object row : rows) {
//			Object[] cells = (Object[]) row;
//			String id = (String) cells[0];
//			String namet = (String) cells[1];
//			Timestamp createTime = (Timestamp) cells[2];
//			Timestamp updateTime = (Timestamp) cells[3];
//			String address = (String) cells[4];
//			results.add(new QueryUserDetailSerializer(id, namet, createTime, updateTime, address));
//		}
	
		//return new PageImpl<Object[]>(query.getResultList(), pageable, total);
		query.setResultTransformer(new AliasToBeanResultTransformer(UserDetailSerializer.class));
		return new PageImpl<List<UserDetailSerializer>>(query.list(), pageable, total);
    }
	
}
