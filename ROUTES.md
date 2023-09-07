# Bem vindo a Documentação de Rota do projeto!

  

O objetivo desta documentação é entregar todos os parâmetros necessários para

manipular os *endpoints* disponíveis na aplicação.

  

## Dados da aplicação:

  

-  **Framework:** Spring Boot

-  **Servlet:** Apache Tomcat

-  **Banco de Dados:** MySQL 8.0.3

  

## Dados de Autenticação:

  

-  **Security Manager:** Spring Security

-  **Tipo de Token:** JWT HMAC256

-  **Tipo de Senha:** Hash $2a$ (BCrypt)

  

# Rotas:

>Esta aplicação possui um "Live Deploy", cujo pode ser acessada [AQUI](https://deploy-production-314a.up.railway.app/)!

### Dos padrões:
- Todas as rotas possuem o prefíxo <code>/api/</code>.
- Todas as rotas utilizam JSON.

## Rota /auth

|Método| Endpoint | Objetivo
|--|--|--|
| POST | /api/auth/login | Gera um token de autenticação
|POST| /api/auth/register|Cadastra um novo usuário na aplicação
| GET | /api/auth/profile | Retorna os dados do usuário autenticado


### Implementação POST /auth/login
**Requisição:**
>Não é necessário enviar Bearer Token nesta rota!
```json
	{
		"login":"nomedeusuario",
		"password":"senhadeusuario"
	}	
```
**Resposta:** STATUS 200 (OK)
```json
	{
		"token": "--TOKEN RETORNADO--"
	}
```
**Possíveis erros:**
|Status| Mensagem | Causa
|--|--|--
| 401 | Wrong user or password | Credenciais inválidas



### Implementação POST /auth/register

**Requisição:**
> Não é necessário enviar Bearer Token nesta rota!
```json
	{
		"name":"Felipe Holanda",
		"login":"felipehal",
		"password":"Holanda123",
		"registration":"000.000.001-00",
		"role":"ADMIN"
	}
```
**Resposta:** 201 (Created)
```json
	{
		"id": "47fc1b44-a9ae-4c58-b8a1-f3a10346e8c5",
		"name": "Nome Usuario",
		"login": "nomeusuario",
		"registration": "000.000.000-00",
		"createdAt": "2023-09-05T01:41:39.896+00:00",
		"updatedAt": "2023-09-05T01:41:39.933+00:00"
	}
```
**Possíveis erros:**
| Status | Mensagem | Causa |
|--|--|--|
| 400 | ~Personalizada para cada caso~ | Requisição feita com dados incompletos/errados. |
| 409 | (Login/Registration) already exists | Já existe um usuário com o mesmo username ou documento cadastrado.

### Implementação do GET /auth/profile
**Requisição:**
><b>Obrigatório</b> enviar Bearer {TOKEN} na Header <b>Authorization
```json
//Não é necessário enviar corpo na requisição.
```
**Retorno:** 200 (OK)
```json
{
	"id": "b14b0911-30ad-4ffe-b3eb-29acc8aa08cf",
	"name": "Exemplo Usuario",
	"login": "exemplologin",
	"registration": "000.000.000-00",
	"created_at": "2023-09-04T03:13:55.000+00:00",
	"updated_at": "2023-09-04T03:13:55.000+00:00",
	"enabled": true,
	"username": "exemplologin",
	"authorities": [
		{"authority": "ROLE_USER"},
		{"authority": "ROLE_ADMIN"}
	],
	"admin": true,
	"accountNonExpired": true,
	"accountNonLocked": true,
	"credentialsNonExpired": true
}
```
**Possíveis erros:**
|Status| Mensagem | Causa |
|--|--|--|
| 401 |  | Não foi enviado o Token
| 403 |  | Token enviado é Inválido

## Rota /products

|Método| Endpoint | Objetivo
|--|--|--|
| GET | /api/products | Lista todos os produtos
|POST| /api/products|Cadastra um novo produto
| GET | /api/products/{id} | Retorna os dados de um produto específico
| PATCH | /api/products/{id} | Edita um produto
| PUT | /api/products/{id} | Adiciona estoque em um produto
| DELETE| /api/products/{id} | Deleta um produto da aplicação

### ATENÇÃO! 
**Todos os verbos nesta rota requerem um Bearer token válido enviado na Header "Authorization"**

### Implementação GET /products
**Requisição:** Nenhum dado é necessário.
**Resposta:** STATUS 200 (OK)
```json
	{
		[
			{
				"id": "428e73d7-c227-4c2b-a184-6880ec3effcb",
				"productName": "Josuke's stand",
				"price": 23.5,
				"amountDisposable": 323,
				"vendorName": "JoJo Shining Diamonds Arch",
				"vendorRegistration": "00.000.000/0000-00"
			}
		]
	}
```
**Possíveis erros:**
|Status| Mensagem | Causa
|--|--|--
| 401 |  | Não foi enviado um Bearer Token na Header Authorization

### Implementação GET /products/{id}
**Requisição:** Enviar o *ID* do produto alvo substituindo o exemplo a cima "{id}"
**Resposta:** STATUS 200 (OK)
```json
	{
			{
				"id": "428e73d7-c227-4c2b-a184-6880ec3effcb",
				"productName": "Josuke's stand",
				"price": 23.5,
				"amountDisposable": 323,
				"vendorName": "JoJo Shining Diamonds Arch",
				"vendorRegistration": "00.000.000/0000-00"
			}
	}
```
**Possíveis erros:**
|Status| Mensagem | Causa
|--|--|--
| 401 |  | Não foi enviado um Bearer Token na Header Authorization
| 404 | Not Found | Não foi encontrado nenhum produto com o ID especificado.

### Implementação POST /products
**Requisição:** 
> Enviar um Bearer Token válido na Header "Authorization"
```json
{
	"id": "428e73d7-c227-4c2b-a184-6880ec3effcb",
	"productName": "Shining diamond",
	"price": 23.5,
	"amountDisposable": 200,
	"vendorName": "Josuke Higashikata",
	"vendorRegistration": "00.000.000/0000-00"
}
```
**Resposta:** STATUS 201 (Created)
```json
	{
			{
				"id": "428e73d7-c227-4c2b-a184-6880ec3effcb",
				"productName": "Josuke's stand",
				"price": 23.5,
				"amountDisposable": 323,
				"vendorName": "JoJo Shining Diamonds Arch",
				"vendorRegistration": "00.000.000/0000-00"
			}
	}
```
**Possíveis erros:**
|Status| Mensagem | Causa
|--|--|--
| 401 |  | Não foi enviado um Bearer Token na Header Authorization

### Implementação PATCH /products/{id}
**Requisição:** Enviar o *ID* do produto alvo substituindo o exemplo a cima "{id}"
> Enviar um Bearer Token válido na Header "Authorization"
> Não é obrigatório enviar todos os campos!
```json
{
	"productName": "Shining diamond",
	"price": 23.5,
	"vendorName": "Josuke Higashikata",
}
```
**Resposta:** STATUS 200 (OK)
```json
	{
			{
				"productName": "Shining diamond",
				"price": 23.5,
				"vendorName": "Josuke Higashikata",
				"vendorRegistration": "00.000.000/0000-00"
			}
	}
```
**Possíveis erros:**
|Status| Mensagem | Causa
|--|--|--
| 401 |  | Não foi enviado um Bearer Token na Header Authorization
| 404 | Not Found | Não foi encontrado nenhum produto com o ID especificado.

### Implementação DELETE /products/{id}
**Requisição:** Enviar o *ID* do produto alvo substituindo o exemplo a cima "{id}"
> Enviar um Bearer Token válido na Header "Authorization"
```json
{
	//Não é necessário enviar corpo.
}
```
**Resposta:** STATUS 204 (No content)
```json
{
	//"No content" não permite o retorno de body.
}
```
**Possíveis erros:**
|Status| Mensagem | Causa
|--|--|--
| 401 |  | Não foi enviado um Bearer Token na Header Authorization
| 404 | Not Found | Não foi encontrado nenhum produto com o ID especificado.




