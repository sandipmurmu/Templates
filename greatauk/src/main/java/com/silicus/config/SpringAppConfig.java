package com.silicus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ComponentScan(basePackages={
	"com.silicus.rest",
	"com.silicus.service"
})
public class SpringAppConfig {

}
