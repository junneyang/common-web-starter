package com.xcompany.xproject.common.web.starter.test.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_user") 
public class User {
	// REF: https://blog.philipphauer.de/uuids-hibernate-mysql/
	@Id
	@GenericGenerator(name="uuid2", strategy="uuid2")
	@GeneratedValue(generator="uuid2")
	private String id;
	
	@Column(name = "name", nullable=false, length=32)
	//@NotNull
	@NotEmpty
	//@NotBlank
	private String name;
	
	@Column(name = "password", nullable=false, length=32)
	//@NotNull
	//@NotEmpty
	@NotBlank
	private String password;
	
	
	@Column(name = "createtime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)  
	@CreationTimestamp
	private Date createtime;
	
	@Column(name = "updatetime", nullable=false)
	//@Temporal(TemporalType.TIMESTAMP)  
	@UpdateTimestamp
	private Date updatetime;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
//	public UserDetail getUserDetail() {
//		return userDetail;
//	}
//	public void setUserDetail(UserDetail userDetail) {
//		this.userDetail = userDetail;
//	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", password=" + password
//				+ ", userDetail=" + userDetail + ", createtime=" + createtime
//				+ ", updatetime=" + updatetime + "]";
//	}
	
}
