package br.com.fiap.dreamcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DreamControl API")
                        .description("Uma API para o sistema de controle da saúde do sono.")
                        .summary("A API do DreamControl serve como a base para um aplicativo móvel que permite aos usuários rastrear facilmente seus padrões de sono e definir metas de sono personalizadas.")
                        .version("V1")
                        .contact(new Contact()
                                .name("Pedro e Thiago")
                                .email("rm94990@fiap.com.br")
                        )
                        .license(new License()
                                .name("MIT Open Soucer")
                                .url("http://dreamcontrol.com/licenca")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }

}
