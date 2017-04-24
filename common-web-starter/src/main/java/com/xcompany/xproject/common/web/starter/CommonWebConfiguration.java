package com.xcompany.xproject.common.web.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CommonWebConfigurationProperties.class)
@ConditionalOnProperty(prefix="common.web", value = {"enabled"}, havingValue = "true", matchIfMissing = false)
@ConditionalOnClass(CommonWebConfigurationService.class)
public class CommonWebConfiguration {
	@Autowired
	private CommonWebConfigurationProperties properties;
	
	@Bean
	@ConditionalOnMissingBean(CommonWebConfigurationService.class)
	public CommonWebConfigurationService createService() {
		CommonWebConfigurationService commonWebAutoConfigurationService = new CommonWebConfigurationService();
		commonWebAutoConfigurationService.setMessage(properties.getMessage());
		return commonWebAutoConfigurationService;
	}
}

