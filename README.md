# Cadastro de Clientes - Sistema Web

![Java](https://img.shields.io/badge/Java-8-blue)
![Spring MVC](https://img.shields.io/badge/Spring-MVC-green)
![JSF](https://img.shields.io/badge/JSF-PrimeFaces-purple)
![Banco de Dados](https://img.shields.io/badge/SQL%20Server-2022-lightgrey)

Este repositório contém dois projetos separados que compõem o sistema de cadastro de clientes:

* [`cadastro-clientes-model`](./cadastro-clientes-model): Projeto backend (Java + Spring MVC), responsável pela lógica de negócio e persistência dos dados.
* [`cadastro-cliente-web`](./cadastro-cliente-web): Projeto frontend (JSF + PrimeFaces), responsável pela interação do usuário com o sistema via interface web.

## 📚 Sumário

* [Visão Geral](#visão-geral)
* [1. cadastro-clientes-model (Back-End)](#1-cadastro-clientes-model-backend-rest-api)
* [2. cadastro-cliente-web (Front-End)](#2-cadastro-cliente-web-frontend-jsf--primefaces)
* [Diagrama de Arquitetura](#diagrama-de-arquitetura)
* [Exemplos de Consumo da API REST](#exemplos-de-consumo-da-api-rest)
* [Instalação Rápida](#instalação-rápida)
* [Observações Finais](#observações-finais)
* [Autor](#autor)

## Visão Geral

Sistema completo para cadastro de clientes e seus endereços, com suporte a upload de imagens, autenticação de funcionários e busca de endereços via WebService (CEP).

---

## 1. cadastro-clientes-model (Backend REST API)

### Tecnologias Utilizadas

* Java 8
* Spring MVC (sem Spring Boot)
* Hibernate / JPA
* SQL Server, com suporte a stored procedures)
* ModelMapper

### Executar

1. Configure seu banco de dados e ajuste o `applicationContext.xml` conforme suas credenciais e URL.
2. Execute o projeto como uma aplicação web usando Tomcat ou outro contêiner compatível.

### Principais Recursos

* Cadastro e edição de clientes
* Cadastro de endereços vinculados aos clientes
* Cadastro e login de funcionários
* Upload de imagem do cliente
* Consulta automática de endereço via CEP

### Estrutura

* `model`: Entidades JPA (Cliente, Endereco, Funcionario)
* `dao`: Camada de persistência com uso de stored procedures
* `service`: Camada de serviço que conecta DAO com o frontend
* `dto`: Objetos de transferência entre camadas
* `utils`: Utilitários para mensagens, upload de arquivos e busca por CEP

### Padrões e Arquitetura

* MVC tradicional com separação clara entre camadas
* DTO + ModelMapper para desacoplamento
* Uso de DAO para encapsular acesso ao banco
* Utilização de stored procedures (SQL Server)

---

## 2. cadastro-cliente-web (Frontend JSF + PrimeFaces)

### Tecnologias Utilizadas

* JavaServer Faces (JSF)
* PrimeFaces
* HTML
* css
* Javascript
* XHTML
* Managed Beans (escopos de sessão, request)

### Executar

1. Importe o projeto em seu IDE
2. Configure seu servidor (ex: Tomcat)
3. Acesse: `http://localhost:8080/cadastro-cliente-web`

### Recursos da Interface

* Formulário de cadastro de clientes com upload de imagem
* Formulário de cadastro de endereços (CEP automático)
* Tela de login para funcionários
* Listagem de clientes e endereços com opção de edição e exclusão

### Integração

* Se comunica com o backend via classes `@Service` e `DAO`
* Imagens são salvas no diretório `/resources/imagens/`
* Busca de endereço baseada em WebService `cep.republicavirtual.com.br`

---

## Layout web
![Image](https://github.com/user-attachments/assets/bca089ca-333a-4195-a4c0-dc0fecb1f77f)
![Image](https://github.com/user-attachments/assets/ff65d115-8d6f-41f2-a564-15b982952654)


## Video


## Diagrama de Arquitetura

```text
                     +-----------------------------+
                     |        Navegador Web        |
                     |  (Usuário via JSF/PrimeFaces)|
                     +-------------+---------------+
                                   |
                                   v
                     +-------------+--------------+
                     |    cadastro-cliente-web    |
                     |     (JSF + REST Client)    |
                     +-------------+--------------+
                                   |
                 REST API (HTTP - JSON)
                                   |
                     +-------------+--------------+
                     |  cadastro-clientes-model   |
                     |  (Spring MVC + JPA + SP)   |
                     +-------------+--------------+
                                   |
                           Banco de Dados (SQL Server)
                     [Stored Procedures | Tabelas ]
```

---

## Exemplos de Consumo da API REST

### Buscar todos os clientes

**GET** `/cliente/cadastro`

```bash
curl -X GET http://localhost:8082/cadastro-clientes-model/cliente/cadastro
```

### Cadastrar um novo cliente

**POST** `/cliente/cadastro`

**Request Body (JSON):**

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "enderecos": [
    {
      "logradouro": "Rua das Flores",
      "numero": "123",
      "cep": "25900000"
    }
  ]
}
```

**Curl Example:**

```bash
curl -X POST http://localhost:8082/cadastro-clientes-model/cliente/cadastro \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","email":"joao@email.com","enderecos":[{"logradouro":"Rua das Flores","numero":"123","cep":"25900000"}]}'
```

### Atualizar um cliente

**PUT** `/cliente/cadastro`

```bash
curl -X PUT http://localhost:8082/cadastro-clientes-model/cliente/cadastro \
  -H "Content-Type: application/json" \
  -d '{...}'
```

### Excluir um cliente

**DELETE** `/cliente/cadastro/{id}`

```bash
curl -X DELETE http://localhost:8082/cadastro-clientes-model/cliente/cadastro/1
```

### Upload de Logotipo (em desenvolvimento)

Endpoint esperado: `POST /cliente/logotipo`

Utilizar `multipart/form-data` com campos:

* `idCliente`
* `imagem` (arquivo)

---

## Instalação Rápida

1. **Banco de dados:** Crie o schema e execute os scripts SQL se necessário
2. **Backend:** Ajuste `applicationContext.xml` e execute o projeto
3. **Frontend:** Deploy no mesmo Tomcat do backend ou separadamente se preferir
4. **Acesse:** `http://localhost:8080/cadastro-cliente-web`

---

## Observações Finais

* Código documentado e modular
* Uso de boas práticas como separação de responsabilidades
* Imagens são carregadas dinamicamente via PrimeFaces
* Dependências gerenciadas via Maven (certifique-se de incluir `pom.xml` nos projetos)

---

## Autor

**Diego Medeiros Jesus**
Desenvolvedor Java Fullstack
Magé, RJ

🔗 [LinkedIn](https://linkedin.com/in/diegomedeirosjesus)
✉️ [diegocielle@gmail.com](mailto:diegocielle@gmail.com)

Agradeço a oportunidade de participar do processo seletivo. Estou à disposição para esclarecer quaisquer dúvidas e demonstrar o sistema em execução.

---
