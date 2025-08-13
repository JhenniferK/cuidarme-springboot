package br.edu.ifpb.es.cuidarme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfiguration {
	
	@Bean
	OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .info(new Info()
	                    .title("Sistema Cuidar.me")
	                    .description("Projeto do P4 do curso de ADS - IFPB Esperança")
	                    .version("1.0.0")
	                    .contact(new Contact()
	                            .name("Código no github")
	                            .url("https://github.com/JhenniferK/cuidar.me-springboot"))
	                    .license(new License()
	                            .name("MIT")
	                            .url("https://choosealicense.com/licenses/mit/")));
    }
}
