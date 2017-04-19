package com.xcompany.xproject.common.web.starter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Spring Profiles provide a way to segregate parts of your application configuration 
//and make it only available in certain environments. 
//Any @Component or @Configuration can be marked with @Profile to limit when it is loaded
//@Configuration // or @Component
//@Profile("prod")

@Controller
@RequestMapping("/")
public class CommonWebConfigurationApiDoc {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonWebConfigurationApiDoc.class);
	
	@RequestMapping("doc/")
	public String apiDoc() {
		LOGGER.info("/doc/index.html");
		//return "redirect:/swagger-ui.html";
		return "redirect:/doc/index.html";
    }
	
	@RequestMapping("test/")
	public String apiTest() {
		LOGGER.info("/swagger-ui.html");
		return "redirect:/swagger-ui.html";
    }
}


