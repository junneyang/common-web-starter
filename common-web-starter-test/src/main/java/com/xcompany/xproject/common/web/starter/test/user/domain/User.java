package com.xcompany.xproject.common.web.starter.test.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.xcompany.xproject.common.web.entity.BaseEntity;

@Entity
//@Table(name = "tbl_user") 
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4501297677626543855L;

	@Column(nullable=false, length=32)
	//@NotNull
	@NotEmpty
	//@NotBlank
	private String name;
	
	@Column(nullable=false, length=32)
	//@NotNull
	//@NotEmpty
	@NotBlank
	private String password;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
