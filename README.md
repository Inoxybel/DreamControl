# Dream Control
## FIAP - Activity ProductDesign

### Atividade Aula 3

---

### Cadastrar
`POST` /api/usuario/cadastrar

**Exemplo de corpo do request**
```
{
	"nome": "string",
	"email": "string",
	"senha": "string"
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
	"nome": "string",
	"email": "string",
	"senha": "string"
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

**Exemplo de corpo do request**
```
{
	"email": "string",
	"senha": "string"
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

**Exemplo de corpo do request**
```
{
	"duracao": "int"
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
	"duracao": "int"
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

**Exemplo de corpo do request**
```
{
	"data": "date",
	"time": "datetime"
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
			"data": "date",
			"time": "datetime"
		},
		{
			"data": "date",
			"time": "datetime"
		},
		{
			"data": "date",
			"time": "datetime"
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

### Relatorio
`GET` /api/usuario/{id}/relatorio

**Exemplo de corpo do response**
```
{
	"inicio": "date",
	"fim": "date",
	"tempoTotal": "datetime",
	"objetivo": "datetime",
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Relatorio recuperado com sucesso
| 204 | Erro na validação dos dados da requisição
| 403 | Erro na requisição

---
