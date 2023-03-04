# Dream Control

Uma API para o sistema de controlar a saúde do sono.

## FIAP - Activity ProductDesign

### Atividade Aula 3

---

## Endpoints
- Usuário
    - [Cadastrar](#cadastrar)
    - [Atualizar Cadastro](#atualizar-cadastro)
    - [Login](#login)
- Sono
    - [Objetivo](#objetivo)
    - [Registrar](#registrar)
    - [Histórico](#histórico)
    - [Relatório](#relatório)

---

## Cadastrar
`POST` /api/usuario/cadastrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | sim | é o nome do usuário
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

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 403 | Erro na requisição
| 422 | Erro ao processar a requisição

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
| 202 | Erro na execução do processamento
| 204 | Usuario não encontrado
| 403 | Erro na requisição

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
| id | string | GUID gerado no cadastro que identifica o usuário no sistema

```js
{
	"id": "1be7d074-a639-43ed-8cb3-d051252bc919"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario validado com sucesso
| 202 | Erro no processamento da requisição
| 401 | Usuário ou Senha incorreto

---

---

## Objetivo
`POST` /api/usuario/{id}/objetivo

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| duracao | int | sim | é o prazo em dias que o usuário deseja alcançar o objetivo, deve ser maior que zero
| objetivo | int | sim | é o objetivo em horas que o usuário pretende alcançar no tempo definido, deve ser maior que zero e menor que: duracao * 16


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
| 201 | Usuario cadastrado com sucesso
| 403 | Erro na requisição
| 422 | Erro ao processar a requisição

---

`GET` /api/usuario/{id}/objetivo

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
| duracao | int | é o prazo em dias que o usuário deseja alcançar o objetivo, deve ser maior que zero

```js
{
	"duracao": 15
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Objetivo recuperado com sucesso
| 204 | Objetivo não encontrado
| 403 | Erro na requisição

---

---

## Registrar
`POST` /api/usuario/{id}/registrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| data | date | sim | é a data que o usuário quer fazer o registro
| time | datetime | sim | é o período de sono do usuário na data em questão

**Exemplo de corpo do request**
```js
{
	"data": "2023-03-02",
	"time": "22:00:00"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 403 | Erro na requisição
| 422 | Erro ao processar a requisição

---

---

## Histórico
`GET` /api/usuario/{id}/historico

| Campo | Tipo | Descrição
|:-------:|:------:|--
| data | date | é a data que o usuário fez o registro
| tempo | time | é o período de sono do usuário na data mencionada

**Exemplo de corpo do response**
```js
{
    "registros": [
        {
            "data": "2022-02-01",
            "tempo": "14H30M00S"
        },
        {
            "data": "2022-02-02",
            "tempo": "11H15M00S"
        },
        {
            "data": "2022-02-03",
            "tempo": "15H20M00S"
        }    
    ]
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Historico recuperado com sucesso
| 204 | Historico não encontrado
| 403 | Erro na requisição

---

---

## Relatório
`GET` /api/usuario/{id}/relatorio

| Campo | Tipo | Obrigatório | Descrição
|-------|------|:-------------:|--
| inicio | date | sim | é a data inicial do relatório
| fim | date | sim | é a data final do relatório
| tempoTotal | datetime | sim | é o tempo total de sono que o usuário teve durante o período de tempo
| objetivo | datetime | sim | é o objetivo inicial que o usuário tinha definido

**Exemplo de corpo do response**
```js
{
    "inicio": "2022-02-01",
    "fim": "2022-02-16",
    "tempoTotal": "115H15M30S",
    "objetivo": "120H"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Relatorio recuperado com sucesso
| 204 | Erro na validação dos dados da requisição
| 403 | Erro na requisição

---
