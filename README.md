<p align="center">
  <img src="https://github.com/Beforg/assets/raw/main/lumostudy/logo.png" alt="Logo">
</p>

<h1 align="center">API-Lumostudy</h1>

<p align="center">
  <img src="https://img.shields.io/badge/backend-java%20spring-%236DB33F?style=for-the-badge&logo=spring" alt="Backend Badge">
  <img src="https://img.shields.io/badge/database-postgresql-%23336791.svg?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL Badge">
</p>

## Sobre o Projeto

API desenvolvida para o projeto Lumostudy, um aplicativo para auxiliar e motivar estudantes a manterem o foco e a organização nos estudos.
## 🚀 Como Rodar o Projeto

### 1. Clone o repositório

```bash
 git clone https://github.com/Beforg/API-TaskPP.git
```

### 2. Acesse o diretório do projeto


### 3. Configure o `application.properties`

O arquivo `src/main/resources/application.properties` deve ser configurado com suas credenciais do banco de dados PostgreSQL e do e-mail que será utilizado para ações de recuperação de senha e ativação da conta.:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=email-servico
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

```

## 🚀 Tecnologias Utilizadas

- **Front-End:** React, JavaScript, HTML, CSS
- **Back-End:** Java 17, Spring Boot
- **Banco de Dados:** PostgreSQL

## 📌 Considerações Finais

Projeto em desenvolvimento. 
Desenvolvido por Bruno Forgiarini.
---

