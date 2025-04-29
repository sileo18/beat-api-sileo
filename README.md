# Beat API Sileo

🌟 **Beat API Sileo** é uma aplicação que permite produtores de música gerenciar suas tracks de forma simples e eficiente. Os usuários podem criar contas, fazer login, e fazer o upload de suas músicas para uma plataforma pública. A API oferece autenticação JWT e armazenamento de arquivos na AWS, além de fornecer uma interface para visualizar as tracks mais recentes.

---

## 📖 Visão Geral

**Beat API Sileo** foi desenvolvida para facilitar o processo de upload e compartilhamento de tracks de produtores musicais. A aplicação permite que os produtores se registrem, façam login e carreguem suas faixas, com a página inicial exibindo as tracks mais recentes. O projeto utiliza **Spring Boot** para desenvolvimento da API, **PostgreSQL** para armazenamento de dados e **AWS S3** para o armazenamento de arquivos.

---

## 🛠️ Tecnologias Utilizadas

- **Spring Boot**: Framework utilizado para desenvolvimento rápido e integração de APIs.
- **Spring Security**: Usado para gerenciar autenticação e segurança com JWT.
- **PostgreSQL**: Banco de dados relacional utilizado para persistência de dados.
- **AWS S3**: Armazenamento de arquivos (imagens e tracks) na nuvem.
- **Flyway**: Ferramenta de migração de banco de dados.
- **Docker**: Contêinerização da aplicação para fácil implantação.
- **JWT (JSON Web Tokens)**: Utilizado para autenticação segura de usuários.

---

## ⚙️ Funcionalidades Implementadas

### Gerenciamento de Usuários:
- 🆕 **Registro de Usuário**: Criação de conta com validação de dados (nome, e-mail, senha).
- 🆕 **Autenticação JWT**: Login de usuários com autenticação utilizando JSON Web Tokens.
- 📸 **Upload de Imagens**: Envio de imagens de perfil do usuário para AWS S3.

---

## 🚀 Funcionalidades há Implementar

- 🆕 **Cadastro de Tracks**: Implementar o registro detalhado das tracks, incluindo metadados como título, artista, e gênero.
- 🛠️ **Filtros de Exibição**: Adicionar filtros para pesquisa e visualização de tracks (por gênero, data, popularidade, etc).
- 🐳 **Dockerização Completa**: Completar a aplicação com Docker para facilitar a implantação.
- 🔐 **Controle de Acesso e Permissões**: Implementar diferentes níveis de acesso para usuários (produtores, administradores, etc.).
- 🛠️ **Documentação**: Documentar endpoint's através do swagger.

---
