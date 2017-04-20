package com.xcompany.xproject.common.web.starter.test.user.serializers;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class AddUserSerializer {
	@NotEmpty
	@Length(max=32)
	private String name;
	
	@NotBlank
	@Length(max=32)
	private String password;

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
