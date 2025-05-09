package br.edu.ucs.matriculas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    /**
     * Método responsável por configurar a documentação da API utilizando OpenAPI.
     * Define informações sobre o projeto, como título, versão, descrição e contato.
     * 
     * @return Um objeto OpenAPI configurado com os metadados da API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("📚 API - Sistema de Matrículas no Ensino Superior")
                .version("1.0.0")
                .description("API desenvolvida para o projeto da disciplina **Projeto e Arquitetura de Software**.\n\nPermite consultar agregações sobre matrículas em cursos de graduação no Brasil entre 2014 e 2022.")
                .contact(new Contact()
                    .name("Felipe Paganin Castilhos")
                    .email("fpcastilhos@ucs.br")  
                    .url("https://github.com/felipepcastilhos/matriculas-backend")) 
            );
    }
}