package com.xcompany.xproject.common.web.starter.test.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.xcompany.xproject.common.web.entity.BaseEntity;

@Entity
//@Table(name = "tbl_user_detail")
public class UserDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3449016394840912139L;

	@NotNull
	@Column(nullable = false, length = 36)
	private String userId;

	@Column(nullable = false, length = 128)
	// @NotNull
	@NotEmpty
	// @NotBlank
	private String address;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
