package com.asaas.hackaton.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        ArrayList<Server> servers = new ArrayList<>();
        Server server = new Server();
        server.url("http://localhost:8080");
        server.description("Development server");
        servers.add(server);

        return new OpenAPI()
                .info(new Info()
                        .title("Swagger aplicação Java - Asaas Tech Lab")
                        .version("1.0")
                        .description("Esta é a documentação da REST API feita em Java para o desafio da Asaas Tech Lab."))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .servers(servers);
    }
}
