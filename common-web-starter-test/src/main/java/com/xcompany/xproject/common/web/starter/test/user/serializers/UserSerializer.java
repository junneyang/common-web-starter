package com.xcompany.xproject.common.web.starter.test.user.serializers;

import java.sql.Timestamp;


public class UserSerializer {
	
		private String id;
		private String name;
		private Timestamp createTime;
		private Timestamp updateTime;
		
		public UserSerializer(String id, String name,
				Timestamp createTime, Timestamp updateTime) {
			super();
			this.id = id;
			this.name = name;
			this.createTime = createTime;
			this.updateTime = updateTime;
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

		@Override
		public String toString() {
			return "QueryUserSerializer [id=" + id + ", name=" + name
					+ ", createTime=" + createTime + ", updateTime="
					+ updateTime + "]";
		}
		
		
		
}
