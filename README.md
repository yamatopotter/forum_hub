# Forum Hub - Oracle Next Education

## Objetivo
Desenvolver uma API Rest para um sistema de forum.

## Sobre o projeto
O Projeto foi desenvolvido usando o SpringBoot 3, Flyway para controle das migrations, MySQL como banco de dados e documentado com OpenAPI

### Link da documentação
link-da-api/swagger-ui/index.html

## Capacidades da aplicação

- Total gerenciamento de contas
  - Criar contas de usuário padrão através da rota /cadastro
  - Administração das contas através da rota de /usuario
  - Possui 3 perfils de usuário: Administrador, Moderador e Usuário
- Gerenciamento de cursos
  - Criar, Atualizar, Excluir e Alterar (Administradores)
- Gerenciamento de tópicos
  - Criar, Atualizar, Excluir, Alterar, Listagem única e geral dos tópicos, Responder a tópicos
- Gerenciamento de respostas
  - Criar, Atualizar, Excluir, Alterar, Listagem única, Marcar resposta como solução do tópico

Dentro das capacidades da aplicação, a depender do nivel de acesso do usuário ele terá ou não permissão de remover itens, alterar e etc.

## Variáveis de ambiente
Para iniciar o projeto em seu ambiente local é preciso configurar as seguintes variáveis de ambiente:

- DB_URL - ex.: jdbc:mysql://localhost:3306/forum_hub
- DB_USER - ex.: root
- DB_PASSWORD - ex.: 12345678
- JWT_SECRET - ex.: esse-e-um-secret

Caso as configurções padrões sejam as mesmas que for usar, não é preciso configurar nada.

## Problemas e sugestões
Encontrou algum bug ou quer indicar alguma melhoria, abra uma issue e vamos ver isso juntos :) Será legal ver esse projeto evoluindo.