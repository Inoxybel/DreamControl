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
| 400 | Erro na validação dos dados da requisição

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
| 201 | Usuario atualizado com sucesso
| 400 | Erro na validação dos dados da requisição

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
| 400 | Erro na validação dos dados da requisição

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
| 201 | Objetivo persistido com sucesso
| 400 | Erro na validação dos dados da requisição


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
| 400 | Erro na validação dos dados da requisição

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
| 201 | Registro persistido com sucesso
| 400 | Erro na validação dos dados da requisição

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
| 400 | Erro na validação dos dados da requisição

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
| 400 | Erro na validação dos dados da requisição

---