package com.xcompany.xproject.common.web.starter.test.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_user_detail") 
public class UserDetail {

		@Id
		private String user;
		
		@Column(name = "address", nullable=false, length=128)
		//@NotNull
		@NotEmpty
		//@NotBlank
		private String address;


		@Column(name = "createtime", updatable = false)
		@Temporal(TemporalType.TIMESTAMP)  
		@CreationTimestamp
		private Date createtime;
		
		@Column(name = "updatetime", nullable=false)
		//@Temporal(TemporalType.TIMESTAMP)  
		@UpdateTimestamp
		private Date updatetime;

		public String getAddress() {
			return address;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public void setAddress(String address) {
			this.address = address;
		}

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

		@Override
		public String toString() {
			return "UserDetail [address=" + address + ", user=" + user
					+ ", createtime=" + createtime + ", updatetime="
					+ updatetime + "]";
		}
	
}
