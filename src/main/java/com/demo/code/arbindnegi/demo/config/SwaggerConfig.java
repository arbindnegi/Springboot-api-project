/**
 * 
 */
package com.demo.code.arbindnegi.demo.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Arbind Negi 29-May-2022
 *
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docketApi() {
	
	return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(getApiInfo())
		.select()
		.apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any())
		.build();
    }
    
    private ApiInfo getApiInfo() {
	
	return new ApiInfo("Springboot API: Backend Services",
		"This project is use to create backend API's",
		"Version:1.0",
		"TOS (Terms of Service)",
		new Contact("Arbind", "https://github.com/arbindnegi", "@email"),
		"No License",
		"No URL",
		Collections.emptyList());
    }
}
