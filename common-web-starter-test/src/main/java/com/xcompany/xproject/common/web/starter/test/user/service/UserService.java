package com.xcompany.xproject.common.web.starter.test.user.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xcompany.xproject.common.web.starter.test.user.serializers.UserDetailSerializer;
import com.xcompany.xproject.common.web.starter.test.user.serializers.UserSerializer;


public interface UserService {

	public Page<UserSerializer> listUser(final String name, 
			final Timestamp startTime, final Timestamp endTime,
			Pageable pageable);
	
	public Page<List<UserDetailSerializer>> listUserCustom(final String name, 
			final Timestamp startTime, final Timestamp endTime,
			Pageable pageable);
	
	public UserSerializer getUser(String id);
	
	public UserSerializer addUser(String name, String password);

}
