package com.sistema_registro_escolar_mid.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {

    @Value("${sre.api.dev-url}")
    private String devUrl;

    @Value("${sre.api.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myAPI() {

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("daniel_aristizabal@outlook.com");
        contact.setName("daniel Aristizabal Bedoya");
        contact.setUrl("https://sre-api.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("sre API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to SurgicalStay Manager.").termsOfService("https://www.sre.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}