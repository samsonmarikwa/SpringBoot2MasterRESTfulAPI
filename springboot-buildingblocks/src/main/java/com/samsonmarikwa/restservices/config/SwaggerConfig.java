package com.samsonmarikwa.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
   
   @Bean
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.samsonmarikwa.restservice"))
            .paths(PathSelectors.ant("/users/**"))
            .build();
            
   }
   
   // Swagger Metadata: http://localhost:8080/v2/api-docs
   // Swagger UI URL: http://localhost:8080/swagger-ui/
   // Editor   https://editor.swagger.io/
   // Swagger Core Annotation https://github.com/swagger-api/swagger-core/wiki/Annotations
   
   private ApiInfo getApiInfo() {
      return new ApiInfoBuilder()
            .title("Samson Marikwa - User Management Service")
            .description("This page lists all API's of User Management")
            .version("V2.0")
            .contact(new Contact(
                 "Samson","http:www.samsonmarikwa.com","sammarikwa@yahoo.com"))
            .license("License 2.0")
            .licenseUrl("http:www.samsonmarikwa.com/license.html")
            .build();
   }
}
