# common-web-starter
基于Springboot自定义starter, 旨在提供完善的Web开发基础组件，对业务方屏蔽各种依赖、配置、库、日志、异常处理、权限、API文档等问题，使业务方专注于应用逻辑。项目持续开发。

### 1.1、使用:    
- Clone This Repository
```
git clone https://github.com/junneyang/common-web-starter.git
```
- New Maven Module
```
You Can Refer The `xproject-common-web-starter-test` Module
```
- Add Dependency
```
	<dependencies>
		<dependency>
			<groupId>com.xcompany.xproject</groupId>
			<artifactId>xproject-common-web-starter</artifactId>
		</dependency>
	</dependencies>
```
- Start To Enjoy Your First App !

### 1.2、特性:    
目前提供的特性如下：
- 全局日志Filter。
- 全局异常处理。
- Swagger、apiDoc文档支持。
- String与TimeStamp自动转换。
- 全局Context自动注入、引用。
- 日志、配置文件依赖/覆盖。
- 国际化支持。
- HTTP基础库。
- 请求、日志自动注入RequestID。
- Domain模型最佳实践（不引入外键约束）。
- Spring Data JPA支持最佳实践示例。
- 序列化、反序列化最佳实践示例。


待添加特性：
- OAuth2权限管理。
- 二级缓存、分布式缓存。
- 任务队列。
- 定时任务。
- 分布式事务。
- 通知中心。
- 分布式追踪。
- 横向扩展、高可用。
- 日志收集等。

