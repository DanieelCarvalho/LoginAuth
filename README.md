# API de Autenticação

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![JWT](https://img.shields.io/badge/JWT-Auth0-ff69b4)

Este projeto é uma API de autenticação desenvolvida com **Java**, **Spring Boot**, **Spring Security**, **JWT** e **Flyway**, utilizando **MySQL** como banco de dados.

A API tem como objetivo demonstrar como estruturar um sistema de autenticação simples e seguro com registro, login e proteção de rotas, utilizando boas práticas e controle por perfis de acesso.

---

## Índice

- [Instalação](#instalação)  
- [Configuração](#configuração)  
- [Uso](#uso)  
- [Endpoints da API](#endpoints-da-api)  
- [Banco de Dados](#banco-de-dados)  
- [Contribuindo](#contribuindo)

---

## 🛠️ Instalação

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repo.git
```

2. Instale as dependências com o Maven:

```bash
./mvnw install
```

3. Certifique-se de que o MySQL está instalado e rodando localmente.

---

## ⚙️ Configuração

Crie um arquivo `application.properties` ou configure as variáveis de ambiente com as seguintes propriedades:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1

api.security.token.secret=sua_chave_secreta
```

---

## 🚀 Uso

Inicie a aplicação com:

```bash
./mvnw spring-boot:run
```

A API estará acessível em:  
👉 `http://localhost:8080`

---

## 🔁 Endpoints da API

A API disponibiliza os seguintes endpoints:

### Autenticação

- `POST /auth/register` – Registrar novo usuário  
- `POST /auth/login` – Efetuar login





## 🗄️ Banco de Dados

O projeto utiliza o MySql como banco de dados. As migrações de banco de dados necessárias são gerenciadas usando o Flyway.


---

## 🤝 Contribuindo

Contribuições são muito bem-vindas!  
Se encontrar bugs ou tiver sugestões de melhoria, abra uma issue ou envie um pull request.

Siga o estilo do projeto, utilize commits semânticos e trabalhe sempre em uma branch separada.
