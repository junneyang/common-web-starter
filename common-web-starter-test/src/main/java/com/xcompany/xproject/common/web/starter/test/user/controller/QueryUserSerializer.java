package com.xcompany.xproject.common.web.starter.test.user.controller;

import java.util.Date;


public class QueryUserSerializer {
	
		private String id;
		private String name;
		private Date createtime;
		private Date updatetime;
		
		
		public QueryUserSerializer(String id, String name, Date createtime,
				Date updatetime) {
			super();
			this.id = id;
			this.name = name;
			this.createtime = createtime;
			this.updatetime = updatetime;
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
		@Override
		public String toString() {
			return "QueryUserSerializer [id=" + id + ", name=" + name
					+ ", createtime=" + createtime + ", updatetime="
					+ updatetime + "]";
		}
		
		
		
}
