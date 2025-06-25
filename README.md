# Literalura 📚

![Java](https://img.shields.io/badge/Java-21-blue) ![Spring%20Boot](https://img.shields.io/badge/Spring_Boot-2.7.5-green)

---

## 📝 Sobre

Literalura é um catálogo de livros desenvolvido com **Java** e **Spring Boot**, que consome a API pública **Gutendex** para buscar livros gratuitos e armazena as informações em um banco de dados PostgreSQL. O projeto tem como objetivo exercitar conceitos de desenvolvimento web, consumo de APIs REST, persistência de dados com JPA/Hibernate, e uso do Spring Boot.

---

## 🚀 Funcionalidades principais

- Buscar livros por título, autor ou tema na API Gutendex  
- Salvar livros selecionados no banco de dados PostgreSQL  
- Listar livros cadastrados no sistema  
- Evitar duplicação de registros   
- Interface via terminal

---

## 🛠 Tecnologias utilizadas

| Tecnologia       | Versão        | Propósito                           |
| ---------------- | ------------- | ---------------------------------- |
| Java             | 21            | Linguagem de programação principal |
| Spring Boot      | 2.7.5         | Framework principal para backend   |
| Spring Data JPA  | -             | ORM para persistência no banco      |
| PostgreSQL       | 15+           | Banco de dados relacional           |
| Maven            | 3.8+          | Gerenciamento de dependências       |
| Gutendex API     | -             | API pública de livros               |

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java JDK 17+ instalado  
- Maven instalado  
- PostgreSQL instalado e rodando  
- Git instalado  

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/literalura.git
cd literalura
