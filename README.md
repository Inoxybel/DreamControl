# DreamControl API

Uma API sofisticada de rastreamento do sono projetada para capacitar os usuários a alcançarem seus objetivos de sono e manterem padrões saudáveis de sono. O DreamControl oferece um conjunto abrangente de recursos e utiliza tecnologias avançadas para fornecer uma experiência de usuário perfeita. Construída como uma API REST baseada em Java, ela utiliza Spring Framework, incluindo também Spring Data, Spring Security e Spring HATEOAS.

## Visão Geral
A API do DreamControl serve como a base para um aplicativo móvel que permite aos usuários rastrear facilmente seus padrões de sono e definir metas de sono personalizadas. Ao utilizar esta API, os usuários podem:

- **Registro de Sono:** Registrar períodos de sono para manter um histórico detalhado de sono.
- **Definição de Metas de Sono**: Estabelecer metas de sono, como dormir por um número específico de horas em um intervalo de datas definido.
- **Histórico de Sono:** Acessar um registro abrangente de registros de sono para acompanhar os padrões de sono ao longo do tempo.
- **Relatório de Sono:** Gerar relatórios detalhados indicando o número de horas dormidas e o progresso em relação às metas de sono.
- **Gerenciamento de Usuários:** Registrar, fazer login e gerenciar seus perfis, garantindo uma experiência personalizada e segura.
- **Autenticação e Segurança:** Utilizar autenticação baseada em JWT para aumentar a segurança das contas de usuário.

# Recursos Principais
## Arquitetura RESTful
A API do DreamControl é construída com base nos princípios da Representação de Estado Transferível (REST), facilitando a integração com uma ampla variedade de plataformas e fornecendo um design de API consistente e intuitivo.

## Spring Framework
Aproveitando o poder do Spring Framework, a API do DreamControl oferece injeção de dependência robusta, inversão de controle e capacidades de programação orientada a aspectos. Ele promove um código modular e escalável, permitindo um desenvolvimento e manutenção rápidos.

## Spring Data
Ao utilizar o Spring Data, a API do DreamControl se integra perfeitamente a vários bancos de dados, garantindo uma persistência e gerenciamento eficientes de dados. Ele simplifica a implementação de camadas de acesso a dados, aprimorando o desempenho e a confiabilidade do sistema como um todo.

## Spring Security
O DreamControl API incorpora o Spring Security, um framework de segurança altamente personalizável e confiável, para proteger contas de usuário e dados sensíveis. A implementação de autenticação baseada em JWT aumenta a segurança, proporcionando uma experiência de login perfeita para os usuários.

## Spring HATEOAS
O DreamControl API adota os princípios de Hypermedia as the Engine of Application State (HATEOAS) usando o Spring HATEOAS. Ele enriquece as respostas da API com links de hipermídia, permitindo que os clientes naveguem na API com facilidade e descubram os recursos disponíveis.

## Desenvolvimento Orientado a Testes
Seguindo as melhores práticas, a API do DreamControl possui testes unitários abrangentes usando JUnit e Mockito. Isso garante a estabilidade e a correção do código, fornecendo uma base sólida para o desenvolvimento e manutenção futuros.

---

## Endpoints
- Usuário
  - [Cadastrar](#cadastrar)
  - [Atualizar Cadastro](#atualizar-usuario)
  - [Login](#login)
- Sono
  - [Objetivo](#objetivo)
  - [Registrar](#registrar)
  - [Deletar Registro](#deletar-registro)
  - [Histórico](#histórico)
  - [Relatório](#relatório)

---

## Cadastrar
`POST` /api/usuario/cadastrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | sim | é o nome do usuário, deve respeitar o Regex(^[a-zA-Z]{3,}$)
| email | string | sim | é o email do usuário, deve respeitar o ReGex(^[A-Za-z0-9+_.-]+@(.+)$)
| senha | string | sim | é a senha do usuário, deve ter no mínimo 8 caracteres


**Exemplo de corpo do request**
```js
{
	"nome": "Pedro Augusto",
	"email": "pedro.silva@gmail.com",
	"senha": "Senha123"
}
```

**Exemplo de corpo de response**
```js
{
    "id": 1,
    "nome": "Pedro Augusto",
    "email": "pedro.silva@gmail.com",
    "senha": "$2a$10$GqssjsKV5bTF1h9g0G13X.mEKH5WyKI6JxKNNnI1sdN4pLqA9jjqy",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/usuario/cadastrar"
        },
        "atualizar": {
            "href": "http://localhost:8080/api/usuario/2"
        },
        "logar": {
            "href": "http://localhost:8080/api/usuario/login"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 400 | Erro na requisição

---

---

## Atualizar Cadastro
`PUT` /api/usuario/{id}

**Exemplo de corpo do request**
```js
{
	"nome": "Thiago Matos",
	"email": "thdevs@live.com",
	"senha": "Senha123"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario atualizado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

## Login
`POST` /api/usuario/login

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| email | string | sim | é o email cadastrado pelo usuário
| senha | string | sim | é a senha cadastrada pelo usuário

**Exemplo de corpo do request**
```js
{
	"email": "usuario@email.com",
	"senha": "senha123"
}
```

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
|token | string | token do usuario que identifica o usuário no sistema

```js
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwZWRyb0BlbWFpbC5jb20uYnIiLCJleHAiOjE2ODQ3ODk5NTcsImlzcyI6IkRyZWFtQ29udHJvbCJ9.dii4kCQsnJEpl4ycu8Z2Mh687_0234MkyNIh_sZPPcQ",
    "type": "JWT",
    "prefix": "Bearer",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/usuario/login"
        },
        "cadastrar": {
            "href": "http://localhost:8080/api/usuario/cadastrar"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario validado com sucesso
| 401 | Usuário ou Senha incorreto

---

---

## Objetivo
`POST` /api/sono/{userId}/objetivo

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| duracao | int | sim | é o prazo em dias que o usuário deseja alcançar o objetivo, deve ser maior que zero
| objetivo | int | sim | é o objetivo em horas que o usuário pretende alcançar no tempo definido, deve ser maior que zero e menor ou igual que ***duracao * 16***


**Exemplo de corpo do request**
```js
{
	"duracao": 15,
 	"objetivo": 120
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Objetivo cadastrado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado
| 422 | Erro ao processar a requisição

---

`GET` /api/sono/{userId}/objetivo

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
| duracao | int | é o prazo em dias que o usuário deseja alcançar o objetivo, deve ser maior que zero
| objetivo | int | é o objetivo em horas que o usuário pretende alcançar no tempo definido
| dataCriacao | é a data em que o objetivo foi criado

```js
{
    "duracao": 15,
    "objetivo": 120,
    "dataCriacao": "2023-05-22",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/sono/1/objetivo"
        },
        "cadastrar": {
            "href": "http://localhost:8080/api/sono/1/objetivo"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Objetivo recuperado com sucesso
| 404 | Usuario não encontrado
| 404 | Objetivo não encontrado
| 400 | Erro na requisição

---

---

## Registrar
`POST` /api/sono/{userId}/registrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| data | LocalDate | sim | é a data que o usuário quer fazer o registro
| tempo | LocalTime | sim | é o período de sono do usuário na data em questão

**Exemplo de corpo do request**
```js
{
	"data": "2023-03-02",
	"tempo": "22:00:00"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

## Deletar Registro
`DELETE` /api/sono/{userId}/deletar/{registroId}

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 204 | Objeto deletado com sucesso
| 404 | Usuario não encontrado
| 404 | Objeto não encontrado

---

---

## Histórico
`GET` /api/sono/{userId}/historico

| Campo | Tipo | Descrição
|:-------:|:------:|--
| registros | ArrayList<Registro> | é a lista que contém os registros efetuados dentro da duração do objetivo.

**Exemplo de corpo do response**
```js
{
    "content": [
        {
            "id": 1,
            "data": "2023-04-03",
            "tempo": "07:00:00"
        },
        {
            "id": 2,
            "data": "2023-05-23",
            "tempo": "08:00:00"
        }
    ],
    "number": 0,
    "totalElements": 2,
    "totalPages": 1,
    "first": true,
    "last": true,
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/sono/1/historico"
        },
        "relatorio": {
            "href": "http://localhost:8080/api/sono/1/relatorio"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Historico recuperado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado
| 404 | Historico não encontrado

---

---

## Relatório
`GET` /api/sono/{userId}/relatorio

| Campo | Tipo | Descrição
|-------|------|--
| inicio | LocalDate | é a data inicial do relatório
| fim | LocalDate | é a data final do relatório
| tempoTotal | Duration | é o tempo total de sono que o usuário teve durante o período de tempo
| objetivo | String | é o objetivo inicial que o usuário tinha definido

**Exemplo de corpo do response**
```js
{
  "inicio": "2023-05-22",
          "fim": "2023-06-06",
          "tempoTotal": "16H",
          "objetivo": 120,
          "_links": {
    "self": {
      "href": "http://localhost:8080/api/sono/1/relatorio"
    },
    "historico": {
      "href": "http://localhost:8080/api/sono/1/historico"
    }
  }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Relatorio recuperado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---
