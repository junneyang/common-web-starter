# common-web-starter
基于Springboot自定义starter, 旨在提供完善的Web开发基础组件，对业务方屏蔽各种依赖、配置、库、日志、异常处理、权限、API文档等问题，使业务方专注于应用逻辑。项目持续开发。

## 1.使用:    
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

## 2.特性:    
目前提供的以及计划支持的特性如下：

### 2.1.通用部分
- [x] 日志Filter。
- [x] 消息头、日志自动注入X-RequestId。
- [x] 异常处理、异常基础类。
- [x] Context自动注入、引用。
- [x] Swagger、apiDoc文档支持。
- [x] 国际化支持。
- [x] 日志、配置文件依赖/覆盖。
- [x] 模块化架构开发。

### 2.2.Controller部分    
- [x] String与TimeStamp自动转换。
- [x] HTTP请求、响应、状态码基础库。

### 2.3.Domain部分    
- [x] BaseEntity支持。
- [x] Domain模型最佳实践, 不引入外键约束。
- [x] Hibernate SessionFactory As Supplement Of JPA EntityManager。
- [x] Spring Data JPA/Hibernate支持最佳实践示例，连表、分页、查询完善实例。

### 2.4.序列化部分
- [x] 序列化、反序列化最佳实践示例。

### 2.5.其他
- [ ] OAuth2权限管理。
- [ ] 二级缓存、分布式缓存。
- [ ] 任务队列。
- [ ] 定时任务。
- [ ] 分布式事务。
- [ ] 通知中心。
- [ ] 分布式追踪。
- [ ] 横向扩展、高可用。
- [ ] Base Entity（is_deleted、时间）。
- [ ] RPC（Thrift/gRPC）。
- [ ] 同步、异步。
- [ ] 进程管理、Docker、K8S集成。
- [ ] CQRS、Axon框架。
- [ ] 失败重试。
- [ ] 日志收集等。

