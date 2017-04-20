package com.xcompany.xproject.common.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1440979245114832954L;

	// REF: https://blog.philipphauer.de/uuids-hibernate-mysql/
	@Id
	@GenericGenerator(name="uuid2", strategy="uuid2")
	@GeneratedValue(generator="uuid2")
	//@Column(name = "id", length=36)
	@Column(length=36)
	private String id;
	
	//@Column(name = "create_time", updatable = false)
	@Column(updatable = false)
	//@Temporal(TemporalType.TIMESTAMP)  
	@CreationTimestamp
	private Timestamp createTime;
	
	//@Column(name = "update_time", nullable=false)
	@Column(nullable=false)
	//@Temporal(TemporalType.TIMESTAMP)  
	@UpdateTimestamp
	private Timestamp updateTime;
	
	//@Column(name = "is_deleted", nullable=false)
	@Column(nullable=false)
	//@Column(name = "is_deleted", nullable=false, columnDefinition="bit(1) NOT NULL DEFAULT b'1'")
	//@ColumnDefault("false")
	// default false
	private boolean isDeleted = false;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
