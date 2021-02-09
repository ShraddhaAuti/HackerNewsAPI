package com.hackernews.api.HackerNewsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableCaching
@SpringBootApplication
@ComponentScan("com.hackernews.api")
public class HackerNewsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackerNewsApiApplication.class, args);
	}
	  @Bean
	  public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurer() {
	          @Override
	          public void addCorsMappings(CorsRegistry registry) {
	              registry.addMapping("/**")
	                      .allowedOrigins("*")
	                      .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
	          }
	      };
	  }
	  
	  @Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
}
