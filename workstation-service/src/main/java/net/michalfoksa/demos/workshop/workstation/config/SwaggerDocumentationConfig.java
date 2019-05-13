package net.michalfoksa.demos.workshop.workstation.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.michalfoksa.demos.workshop.workstation.context.RuntimeContext;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerDocumentationConfig {

    @Inject
    private RuntimeContext runtimeContext;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(runtimeContext.getApplication() + " API")
                // .description("API for managing user tasks")
                // .license("")
                // .licenseUrl("http://unlicense.org")
                // .termsOfServiceUrl("")
                // .version("v1")
                // .contact(new Contact("","", ""))
                .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.michalfoksa.demos.workshop.workstation.http.rest"))
                .build()
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}
