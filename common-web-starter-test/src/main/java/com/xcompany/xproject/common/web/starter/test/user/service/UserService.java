package com.xcompany.xproject.common.web.starter.test.user.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xcompany.xproject.common.web.starter.test.user.controller.QueryUserDetailSerializer;
import com.xcompany.xproject.common.web.starter.test.user.controller.QueryUserSerializer;
import com.xcompany.xproject.common.web.starter.test.user.domain.User;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserDetail;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserDetailRepository;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserRepository;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserRepositoryCustom;


@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Autowired
	private UserRepositoryCustom userRepositoryCustom;

	
	public Page<QueryUserSerializer> listUser(final String name, 
			final Timestamp starttime, final Timestamp endtime,
			Pageable pageable) {
		
		Page<User> result = userRepositoryCustom.listUser(name, starttime, endtime, pageable);
		List<QueryUserSerializer> results = new ArrayList<QueryUserSerializer>();
		
		List<User> users = result.getContent();
		for (User user : users ) {
			results.add(new QueryUserSerializer(user.getId(), user.getName(), 
					user.getCreatetime(), user.getUpdatetime()));
		}
		
		return new PageImpl<QueryUserSerializer>(results, pageable, result.getTotalElements());
	}
	
	public Page<QueryUserDetailSerializer> listUserCustom(final String name, 
			final Timestamp starttime, final Timestamp endtime,
			Pageable pageable) {
		Page<Object> result = this.userRepositoryCustom.listUserCustom(name, starttime, endtime, pageable);
		
		List<QueryUserDetailSerializer> results = new ArrayList<QueryUserDetailSerializer>();
		
		List<?> rows = result.getContent();
		for (Object row : rows) {
			Object[] cells = (Object[]) row;
			String id = (String) cells[0];
			String namet = (String) cells[1];
			Timestamp createtime = (Timestamp) cells[2];
			Timestamp updatetime = (Timestamp) cells[3];
			String address = (String) cells[4];
			results.add(new QueryUserDetailSerializer(id, namet, createtime, updatetime, address));
		}
		
		return new PageImpl<QueryUserDetailSerializer>(results, pageable, result.getTotalElements());
	}
	
	public QueryUserSerializer getUser(String id) {
		User user = this.userRepository.findById(id);
		
		QueryUserSerializer queryUserSerializer = new QueryUserSerializer(user.getId(),
				user.getName(), user.getCreatetime(), user.getUpdatetime());
		return queryUserSerializer;
	}
	
	public QueryUserSerializer addUser(String name, String password) {
		
		User user = new User(name, password);
		User saveUser = this.userRepository.save(user);
		
		UserDetail userDetail = new UserDetail();
		userDetail.setUser(saveUser.getId());
		userDetail.setAddress(saveUser.getName() + "-ADDRESS");
		this.userDetailRepository.saveAndFlush(userDetail);
		
		QueryUserSerializer queryUserSerializer = new QueryUserSerializer(saveUser.getId(),
				saveUser.getName(), saveUser.getCreatetime(), saveUser.getUpdatetime());
		return queryUserSerializer;
	}
}
