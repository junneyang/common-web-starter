package com.xcompany.xproject.common.web.starter.test.user.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xcompany.xproject.common.web.starter.test.user.domain.User;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserDetail;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserDetailRepository;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserRepository;
import com.xcompany.xproject.common.web.starter.test.user.domain.UserRepositoryCustom;
import com.xcompany.xproject.common.web.starter.test.user.serializers.UserDetailSerializer;
import com.xcompany.xproject.common.web.starter.test.user.serializers.UserSerializer;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Autowired
	private UserRepositoryCustom userRepositoryCustom;

	
	public Page<UserSerializer> listUser(final String name, 
			final Timestamp startTime, final Timestamp endTime,
			Pageable pageable) {
		
		return this.userRepositoryCustom.listUser(name, startTime, endTime, pageable);
		
	}
	
	public Page<List<UserDetailSerializer>> listUserCustom(final String name, 
			final Timestamp startTime, final Timestamp endTime,
			Pageable pageable) {
		
		return this.userRepositoryCustom.listUserCustom(name, startTime, endTime, pageable);
		
	}
	
	public UserSerializer getUser(String id) {
		
		User user = this.userRepository.findById(id);
		return new UserSerializer(user.getId(), user.getName(), user.getCreateTime(), user.getUpdateTime());
	}
	
	public UserSerializer addUser(String name, String password) {
		
		User user = new User(name, password);
		User saveUser = this.userRepository.saveAndFlush(user);
		
		UserDetail userDetail = new UserDetail();
		userDetail.setUserId(saveUser.getId());
		userDetail.setAddress(saveUser.getName() + "-ADDRESS");
		this.userDetailRepository.save(userDetail);
		
		return new UserSerializer(saveUser.getId(), saveUser.getName(), saveUser.getCreateTime(), saveUser.getUpdateTime());
		
	}
}
