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
目前提供的以及计划支持的特性如下：

- [ ] 日志Filter。
- [ ] 消息头、日志自动注入X-RequestId。
- [ ] 异常处理、异常基础类。
- [ ] Context自动注入、引用。
- [ ] Swagger、apiDoc文档支持。
- [ ] 国际化支持。
- [ ] 日志、配置文件依赖/覆盖。
- [ ] 模块化架构开发。

- [ ] String与TimeStamp自动转换。
- [ ] HTTP请求、响应、状态码基础库。

- [ ] BaseEntity支持。
- [ ] Domain模型最佳实践, 不引入外键约束。
- [ ] Hibernate SessionFactory As Supplement Of JPA EntityManager。
- [ ] Spring Data JPA/Hibernate支持最佳实践示例，连表、分页、查询完善实例。

- [ ] 序列化、反序列化最佳实践示例。

- [x] OAuth2权限管理。
- [x] 二级缓存、分布式缓存。
- [x] 任务队列。
- [x] 定时任务。
- [x] 分布式事务。
- [x] 通知中心。
- [x] 分布式追踪。
- [x] 横向扩展、高可用。
- [x] Base Entity（is_deleted、时间）。
- [x] RPC（Thrift/gRPC）。
- [x] 同步、异步。
- [x] 进程管理、Docker、K8S集成。
- [x] CQRS、Axon框架。
- [x] 失败重试。
- [x] 日志收集等。

