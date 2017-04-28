package com.xcompany.xproject.common.web.starter.aaa;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcompany.xproject.common.web.starter.EnableCommonWebConfiguration;

//same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
@RestController
@EnableCommonWebConfiguration
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@RequestMapping("/public")
    public String publicx(){
        return "Hello public!";
    }
	
	@PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/private")
    public String privatex(){
        return "Hello private!";
    }
}
