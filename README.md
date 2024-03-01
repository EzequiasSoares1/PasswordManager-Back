# Password Manager - Back

O Gerenciador de Senha é uma aplicação desenvolvida em Java com o framework Spring Boot, destinada a fornecer uma solução segura e eficiente para armazenamento e gerenciamento de senhas. Esta aplicação foi construída utilizando conhecimentos atualizados em Java, Spring Boot, JUnit 5, Docker, Spring Security e JWT (JSON Web Token), com foco especial em práticas de segurança, incluindo criptografia utilizando Bcrypt para gerenciamento de usuários e criptografia padrão com hash para armazenamento de senhas.

## Estrutura do Projeto

O projeto segue o padrão MVC (Model-View-Controller) para organização e estruturação do código, dividido em diferentes camadas:

- **Config**: Contém as configurações do projeto, como configurações do Spring Security e JWT.
- **Controller**: Responsável por receber as requisições HTTP e delegar para os serviços adequados.
- **Model**:
  - **DTO (Data Transfer Object)**: Objetos utilizados para transferir dados entre a camada de apresentação e a camada de serviço.
  - **Entity**: Representa as entidades do domínio que são mapeadas para as tabelas do banco de dados.
  - **Repositories**: Interfaces que definem operações de acesso ao banco de dados.
  - **Service**: Contém a lógica de negócio e manipulação de dados.
- **Security**: Contém a logica de segurança e criptografia.

## Implementação da Segurança

A segurança é uma parte fundamental do Gerenciador de Senha e foi implementada utilizando várias práticas e tecnologias:

- **Spring Security**: Utilizado para fornecer autenticação e autorização robustas para os endpoints da aplicação.
- **JWT (JSON Web Token)**: Utilizado para autenticação baseada em token, permitindo aos usuários acessarem os endpoints de forma segura.
- **Bcrypt**: Utilizado para criptografar as senhas dos usuários durante o processo de registro e autenticação, garantindo a segurança das informações.
- **Hash para armazenamento de senhas**: As senhas dos usuários são armazenadas no banco de dados de forma segura, utilizando técnicas de hash para proteger contra vazamento de dados.
- **Relacionamento do Banco de Dados**: As senhas mantêm referências aos usuários para evitar sobrecarga nos endpoints de usuário durante o processo de login e garantir a segurança dos dados.

## Endpoints

#### AuthenticationController

Este controlador lida com operações relacionadas à autenticação de usuários. Ele fornece endpoints para fazer login, verificar e atualizar tokens JWT.

#### MyPasswordController

Este controlador é responsável por operações CRUD relacionadas às senhas dos usuários. Todos os acessos e modificações são realizados por meio de tokens JWT, garantindo que apenas o usuário autenticado possa acessar e gerenciar suas próprias senhas.

#### UserController

O UserController gerencia operações relacionadas ao cadastro e gerenciamento de usuários. Ele oferece endpoints para registrar novos usuários e atualizar ou excluir informações de usuários existentes. 

## Driagrama de relacionamento do Banco
![image](https://github.com/EzequiasSoares1/PasswordManager-Back/assets/87997012/ca6342c5-13bb-475a-93c8-aecf1e140e54)


## Instruções para Rodar a Aplicação

1. **Clonar o Repositório**: git clone https://github.com/EzequiasSoares1/PasswordManager-Back

2. **Acessar o Diretório do Projeto**: PasswordManager-Back
   
3. **Executar o Docker Desktop**: Certifique-se de ter o Docker instalado e execute o Docker para iniciar o banco de dados

5. **Executar a Aplicação**: Execute a aplicação Spring Boot na sua IDE ou utilize o Maven para construir e executar a aplicação a partir da linha de comando

6. **Testar os Endpoints**: Os endpoints estarão disponíveis em `http://localhost:8080`. Você pode usar uma ferramenta como Postman ou insomnia para testar os endpoints fornecidos pelos controladores.




