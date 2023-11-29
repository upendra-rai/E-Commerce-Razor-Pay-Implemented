package com.upendra.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String DELETE = "DELETE";
	private static final String PUT = "PUT";
	private static final String PATCH = "PATCH";

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			public void addCorsMapping(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods(GET, POST, DELETE, PUT, PATCH).allowedHeaders("*")
						.allowedOriginPatterns("*").allowCredentials(true);

			}
		};

	}

}
