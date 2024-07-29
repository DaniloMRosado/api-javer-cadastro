package br.com.consulting_delivery.javer_api_client.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoSpringDoc {
    public ConfiguracaoSpringDoc() {
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return (new OpenAPI()).components((new Components()).addSecuritySchemes("bearer-key", (new SecurityScheme()).type(Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info((new Info()).title("Javer Banco API").description("API Rest da aplicação Javer Data Manager, contendo as funcionalidades de CRUD de clientes"));
    }
}
