package com.xcompany.xproject.common.web.starter.test.user.controller;

import java.sql.Timestamp;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xcompany.xproject.common.web.starter.constant.ResponseCode;
import com.xcompany.xproject.common.web.starter.exception.ParamsException;
import com.xcompany.xproject.common.web.starter.http.APIResponse;
import com.xcompany.xproject.common.web.starter.test.user.service.UserService;

//Spring Profiles provide a way to segregate parts of your application configuration 
//and make it only available in certain environments. 
//Any @Component or @Configuration can be marked with @Profile to limit when it is loaded
//@Configuration // or @Component
//@Profile("prod")

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private Environment environment;
	@Autowired
	private UserService userService;
	
	/**
	 * @apiDefine CommonResponseParams
	 * @apiSuccess (Response-Params) {Number} status 标准HTTP状态码
	 * @apiSuccess (Response-Params) {Number} code 详细API错误码
	 * @apiSuccess (Response-Params) {String} msg 错误简要描述信息 , 根据 `Accept-Language` 返回对应语言的错误描述
	 * @apiSuccess (Response-Params) {Object[]} errors 错误列表，包含错误的Field、Message
	 * @apiSuccess (Response-Params) {String} errors.field 错误的Field
	 * @apiSuccess (Response-Params) {String} errors.message 错误的Message
	 * @apiSuccess (Response-Params) {Number} timestamp 返回时刻时间戳
	 * @apiSuccess (Response-Params) {String} app 服务/模块/应用的名称
	 */
	
	/**
	 * @apiGroup Test
	 * @apiName test-name
	 * @api {get} /user/v1/test/?name={name} test-desc
	 * @apiVersion 1.0.0
	 * 
	 * @apiPermission None
	 * @apiParam (Request-Params) {String} name field-desc
	 * 
	 * @apiParamExample {json} Request-Example:
	 * /user/v1/test/?name=xxx
	 *
	 * @apiSuccessExample Response-Example(Success)
	 *     {
	 *     "code": 2000,
	 *     "msg": "成功",
	 *     "data": "TEST",
	 *     "errors": null,
	 *     "timestamp": 1491805791497,
	 *     "app": "myproject-demoservice"
	 *     }
	 * @apiSuccessExample Success-Code
	 * | code  | msg	
	 * | 2000  | SUCCESS
	 *
	 *
	 * @apiErrorExample Response-Example(Error)
	 *     {
	 *     "code": -1,
	 *     "msg": "some error",
	 *     "data": null,
	 *     "errors": null,
	 *     "timestamp": 1491805791497,
	 *     "app": "myproject-demoservice"
	 *     }    
	 * @apiErrorExample Error-Code
	 *     | code  | msg	
	 *     | -1    | SOME_ERROR
	 *     | 3000  | 权限异常, 认证授权失败
	 *     | 4000  | 参数异常, 用户参数不合法
	 *     | 5000  | 业务异常, 业务约束条件不满足
	 */
	@RequestMapping(value="/v1/test/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object test(@RequestParam(name="name", required=false) String name) throws Exception {
		LOGGER.info(name);
		
		LOGGER.info(environment.toString());
		LOGGER.info(environment.getProperty("local.server.port"));
		LOGGER.info(environment.getProperty("spring.application.name"));
		
		if (environment.getProperty("spring.main.show-banner").equals("true")) {
			//throw new PermissionException(3000, null, null);
			//throw new ParamsException(4000, null, null, null);
			//throw new BusinessException(5000, null, null);
			//throw new IOException(6000, null, null);
			//throw new NetworkException(7000, null, null);
			//throw new Exception("SOME_ERROR");
		}

		return new APIResponse().createBuilder()
				.setCode(ResponseCode.SUCCESS)
				.setObjects(null)
				.setData(environment.getProperty("spring.profiles.active"))
				.setErrors(null)
				.build().getEntry();
	}
	
	//int: [0-9]+
	//uuid2: [0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}
	//uuid: [0-9a-z]{32}
	@RequestMapping(value="/v1/{id:[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}}/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getUser(@PathVariable UUID id) throws Exception {	

		return new APIResponse().createBuilder().
				setCode(ResponseCode.SUCCESS)
				.setObjects(null)
				.setData(this.userService.getUser(id.toString()))
				.setErrors(null)
				.build().getEntry();
	}
	
	
	@RequestMapping(value="/v1/", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object addUser(@Valid @RequestBody AddUserSerializer userSerializer, BindingResult bindingResult) throws Exception {	
		
		if (bindingResult.hasErrors()) {
			throw new ParamsException(4001, null, null, bindingResult);
		}
		
		LOGGER.info(userSerializer.toString());
		QueryUserSerializer queryUserSerializer = this.userService.addUser(userSerializer.getName(), userSerializer.getPassword());
		LOGGER.info(queryUserSerializer.toString());

		return new APIResponse().createBuilder().
				setCode(ResponseCode.SUCCESS)
				.setObjects(null)
				.setData(queryUserSerializer)
				.setErrors(null)
				.build().getEntry();
	}
	
	//?size=10&page=0&sort=name,asc&sort=createtime,asc&name=bbb&starttime=1492417764000&endtime=1492417767000
	@RequestMapping(value="/v1/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object listUser(
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="starttime", required=false) Timestamp starttime,
			@RequestParam(name="endtime", required=false) Timestamp endtime,
			
			@PageableDefault(page=0, size=10, sort = { "id" }, direction = Sort.Direction.DESC) 
			Pageable pageable
			) throws Exception {	

		return new APIResponse().createBuilder().
				setCode(ResponseCode.SUCCESS)
				.setObjects(null)
				.setData(userService.listUser(name, starttime, endtime, pageable))
				.setErrors(null)
				.build().getEntry();
		
	}
	
	//?size=2&page=0&sort=name,asc&sort=createtime,asc&starttime=1492417764000&endtime=2492417764000&name=bb
	@RequestMapping(value="/v1/custom/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object listUserCustom(
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="starttime", required=false) Timestamp starttime,
			@RequestParam(name="endtime", required=false) Timestamp endtime,
			
			@PageableDefault(page=0, size=10, sort = { "id" }, direction = Sort.Direction.DESC) 
			Pageable pageable
			) throws Exception {	

		LOGGER.info(pageable.toString());
		return new APIResponse().createBuilder().
				setCode(ResponseCode.SUCCESS)
				.setObjects(null)
				.setData(userService.listUserCustom(name, starttime, endtime, pageable))
				.setErrors(null)
				.build().getEntry();
		
	}
	
}
