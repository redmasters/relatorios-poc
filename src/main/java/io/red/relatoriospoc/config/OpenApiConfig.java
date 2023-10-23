package io.red.relatoriospoc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = createInfo();
        info.setContact(createContact());
        info.setLicense(createLicense());
        return new OpenAPI()
                .info(info)
                .servers(createServer());
    }

    private List<Server> createServer() {
        var serverLocalHost = new Server();
        serverLocalHost.setUrl("http://127.0.0.1:8080");
        serverLocalHost.setDescription("Local Host");
        return Arrays.asList(serverLocalHost);
    }

    private Info createInfo() {
        var info = new Info();
        info.setTitle("POC Relatorios");
        info.setDescription("POC Relatorios");
        info.setVersion("v1");
        return info;
    }

    private Contact createContact() {
        var contato = new Contact();
        contato.setName("ReD - Relatórios e Documentos");
        contato.setEmail("");
        contato.setUrl("");
        return contato;
    }

    private License createLicense() {
        var licenca = new License();
        licenca.setName("Copyright (C) ReD - Todos os direitos reservados ");
        licenca.setUrl("");
        return licenca;
    }
}
