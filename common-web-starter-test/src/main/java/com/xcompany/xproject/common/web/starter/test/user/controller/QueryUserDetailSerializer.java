package com.xcompany.xproject.common.web.starter.test.user.controller;

import java.util.Date;


public class QueryUserDetailSerializer {
	
		private String id;
		private String name;
		private Date createtime;
		private Date updatetime;
		
		private String address;
		
		
		
		public QueryUserDetailSerializer(String id, String name, Date createtime,
				Date updatetime, String address) {
			super();
			this.id = id;
			this.name = name;
			this.createtime = createtime;
			this.updatetime = updatetime;
			this.address = address;
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
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		
		@Override
		public String toString() {
			return "QueryUserSerializer [id=" + id + ", name=" + name
					+ ", createtime=" + createtime + ", updatetime="
					+ updatetime + ", address=" + address + "]";
		}
		
}
