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

### Cadastrar
`POST` /api/usuario/cadastrar

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|--
| nome | string | sim | é o nome do usuário
| email | string | sim | é o email do usuário
| senha | string | sim | é a senha do usuário, deve ter no mínimo 8 caracteres


**Exemplo de corpo do request**
```
{
	"nome": "Pedro Augusto",
	"email": "pedro.silva@gmail.com",
	"senha": "MinhSenha456"
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

### Atualizar Cadastro
`PUT` /api/usuario/{id}

**Exemplo de corpo do request**
```
{
	"nome": "Thiago Matos",
	"email": "thm@outroemail.com",
	"senha": "novaSenha456"
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

### Login
`POST` /api/usuario/login

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|--
| email | string | sim | é o email que o usuário realizou o cadastro
| senha | string | sim | é a senha que o usuário realizou o cadastro

**Exemplo de corpo do request**
```
{
	"email": "thm@outroemail.com",
	"senha": "novaSenha456"
}
```

**Exemplo de corpo do response**
```
{
	"id": "string"
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

### Objetivo
`POST` /api/usuario/{id}/objetivo

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|--
| duracao | int | sim | é um integer que representa o objetivo do usuário em relação ao sono, deve ser maior que zero


**Exemplo de corpo do request**
```
{
	"duracao": 60
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 403 | Erro na requisição
| 422 | Erro ao processar a requisição


`GET` /api/usuario/{id}/objetivo

**Exemplo de corpo do response**
```
{
	"duracao": 60
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

### Registrar
`POST` /api/usuario/{id}/registrar

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|--
| data | date | sim | é a data que o usuário quer fazer o registro
| time | datetime | sim | é o período de sono do usuário na data em questão

**Exemplo de corpo do request**
```
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

### Histórico
`GET` /api/usuario/{id}/historico

**Exemplo de corpo do response**
```
{
    "registros": [
        {
            "data": "2022-02-01",
            "time": "2022-02-01T14:30:00Z"
        },
        {
            "data": "2022-02-02",
            "time": "2022-02-02T11:15:00Z"
        },
        {
            "data": "2022-02-03",
            "time": "2022-02-03T18:20:00Z"
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

### Relatório
`GET` /api/usuario/{id}/relatorio

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|--
| inicio | date | sim | é a data inicial do relatório
| fim | date | sim | é a data final do relatório
| tempoTotal | datetime | sim | é o tempo total de sono que o usuário teve durante o período de tempo
| objetivo | datetime | sim | é o objetivo inicial que o usuário tinha definido

**Exemplo de corpo do response**
```
{
    "inicio": "2022-02-01",
    "fim": "2022-02-07",
    "tempoTotal": "PT14H15M30S",
    "objetivo": "PT20H"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Relatorio recuperado com sucesso
| 204 | Erro na validação dos dados da requisição
| 403 | Erro na requisição

---
