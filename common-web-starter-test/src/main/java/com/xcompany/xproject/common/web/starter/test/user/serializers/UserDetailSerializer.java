package com.xcompany.xproject.common.web.starter.test.user.serializers;

import java.sql.Timestamp;


public class UserDetailSerializer {
	
		private String id;
		private String name;
		private Timestamp createTime;
		private Timestamp updateTime;
		private String address;

		public UserDetailSerializer() {
			// TODO Auto-generated constructor stub
		}
		
		public UserDetailSerializer(String id, String name,
				Timestamp createTime, Timestamp updateTime, String address) {
			super();
			this.id = id;
			this.name = name;
			this.createTime = createTime;
			this.updateTime = updateTime;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "QueryUserDetailSerializer [id=" + id + ", name=" + name
					+ ", createTime=" + createTime + ", updateTime="
					+ updateTime + ", address=" + address + "]";
		}
		
		

}
