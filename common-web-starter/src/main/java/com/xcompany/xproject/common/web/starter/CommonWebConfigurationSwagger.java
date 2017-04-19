package com.xcompany.xproject.common.web.starter;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.mvc.AuditEventsMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.boot.actuate.endpoint.mvc.EnvironmentMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.LoggersMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.MetricsMvcEndpoint;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2 
public class CommonWebConfigurationSwagger {
	@Autowired
	private Environment environment;
	
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class)	// exclude
                    return false;
//                if (declaringClass == CommonErrorController.class) {
//					return false;
//				}
                if (declaringClass == AuditEventsMvcEndpoint.class) {
					return false;
				}
                if (declaringClass == EndpointMvcAdapter.class) {
					return false;
				}
                if (declaringClass == EnvironmentMvcEndpoint.class) {
					return false;
				}
                if (declaringClass == HealthMvcEndpoint.class) {
					return false;
				}
                if (declaringClass == LoggersMvcEndpoint.class) {
					return false;
				}
                if (declaringClass == MetricsMvcEndpoint.class) {
					return false;
				}
                
                if(declaringClass.isAnnotationPresent((Class<? extends Annotation>) RestController.class))	// include RestController
                    return true;
                if(input.isAnnotatedWith(ResponseBody.class)) // include ResponseBody
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API Document")	//Title
            .description(environment.getProperty("spring.application.name"))	//Subtitle
            .version(environment.getProperty("spring.application.version"))		//API Version
            .build();
    }
}
