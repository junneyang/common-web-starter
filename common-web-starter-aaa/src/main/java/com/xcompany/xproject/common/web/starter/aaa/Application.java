package com.xcompany.xproject.common.web.starter.aaa;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcompany.xproject.common.web.starter.EnableCommonWebConfiguration;

//same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
@RestController
@EnableCommonWebConfiguration
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}
	
	//@PreAuthorize("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
	@RequestMapping("/user")
	public Principal user(Principal user) {
		LOGGER.info(user.toString());
		return user;
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login_get() {
//        return "login";
//    }
	
//	@RequestMapping(value="/login",method = RequestMethod.POST)
//	public String login_post(){
//		return "j_spring_security_check";
//	}
	
//	@PreAuthorize("#oauth2.hasScope('read')")
//	@RequestMapping("/read")
//    public String read(){
//        return "Hello Read!";
//    }
//	
//	@PreAuthorize("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
//    @RequestMapping("/write")
//    public String write(){
//        return "Hello Write!";
//    }
	
}
