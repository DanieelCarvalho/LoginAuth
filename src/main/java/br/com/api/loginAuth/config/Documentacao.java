package br.com.api.loginAuth.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Define que esta classe contém configurações para a aplicação Spring
@Configuration
public class Documentacao {

        // Define um bean do tipo OpenAPI que será gerenciado pelo Spring
        @Bean
        public OpenAPI customOpenAPI() {
                // Cria uma instância de OpenAPI com informações personalizadas da sua API
                return new OpenAPI()
                                .info(new Info()
                                                // Título da documentação da API que aparecerá no Swagger UI
                                                .title("API de cadastro e login")
                                                // Descrição da API, explicando seu propósito
                                                .description("API completa para gerenciamento de usuários")
                                                // Versão da API (importante para controle e versionamento)
                                                .version("1.0")
                                                // Informações de contato do desenvolvedor ou da equipe responsável
                                                .contact(new Contact()
                                                                .name("Daniel Carvalho") // Nome do responsável
                                                                .email("danielscg201@gmail.com") // E-mail de contato
                                                                .url("https://github.com/DanieelCarvalho")
                                                                .url("https://www.linkedin.com/in/daniel-carvalho-dev/") // Site
                                                                                                                         // ou
                                                                                                                         // link
                                                                                                                         // de
                                                                                                                         // referência
                                                ));
        }
}