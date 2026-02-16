/*
 * package com.dhatvibs.config;
 * 
 * import io.swagger.v3.oas.models.OpenAPI; import
 * io.swagger.v3.oas.models.info.Info; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * @Configuration public class SwaggerConfig {
 * 
 * @Bean public OpenAPI customOpenAPI() { return new OpenAPI() .info(new Info()
 * .title("Executives Tracking API") .version("1.0")
 * .description("Role Based Authentication APIs")); } }
 */ 

package com.dhatvibs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = "cookieAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Executives Tracking API")
                        .version("1.0")
                        .description("Role Based Authentication APIs"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.COOKIE)
                                        .name("JSESSIONID")
                        )
                );
    }
}
