# Sistema de Pedidos de Restaurante 🍽️

Olá! Este é um projeto de API REST simples, construído com Spring Boot e Java, para simular o sistema de pedidos de um restaurante.

O objetivo aqui é gerenciar Clientes, Pratos e os Pedidos feitos. Uma característica interessante deste projeto é que ele usa o Hibernate "puro" (através do `EntityManagerFactory`) para conversar com o banco, em vez de abstrações mais altas como o Spring Data JPA.

## O que foi usado? (Tecnologias)

* **Java 21:** Escrito em uma versão moderna do Java.
* **Spring Boot:** Para subir a API e gerenciar as requisições web (HTTP) de forma rápida.
* **Hibernate:** O coração da persistência, usado diretamente para salvar e buscar dados no banco.
* **H2 Database:** Um banco de dados super leve que roda em memória. Perfeito para testes e desenvolvimento, pois não precisa de instalação.
* **Maven Wrapper:** Permite que qualquer pessoa rode o projeto com o Maven certo, sem precisar instalar nada (só precisa ter o Java!).
* **Lombok:** Para reduzir o "boilerplate" (código repetitivo) nas nossas classes de modelo e DTOs.

## Como rodar o projeto

Você não precisa instalar o Maven, basta ter o **Java 21 (JDK)** instalado.

1.  Abra seu terminal ou prompt de comando.
2.  Navegue até a pasta onde você clonou este repositório.
3.  Execute o comando mágico do Maven Wrapper:

    *No Linux ou Mac:*
    ```bash
    ./mvnw spring-boot:run
    ```

    *No Windows:*
    ```bash
    mvnw.cmd spring-boot:run
    ```
Pronto! A API começará a rodar em `http://localhost:8080`.

## Espiando o Banco de Dados (H2)

Como estamos usando um banco em memória, você pode olhar o que está acontecendo em tempo real pelo console do H2.

1.  Com a aplicação rodando, abra seu navegador em:
    `http://localhost:8080/h2-console`

2.  Use estas informações para conectar:
    * **JDBC URL:** `jdbc:h2:mem:testdb` (já deve estar preenchido)
    * **Username:** `sa`
    * **Password:** (deixe em branco)

**Importante:** A configuração `create-drop` significa que toda vez que você reiniciar a aplicação, o banco de dados é zerado. Todos os dados são perdidos!

## O que a API faz? (Endpoints)

Temos 3 grandes áreas que você pode controlar:

### 1. Clientes (`/clientes`)
* `POST /clientes`: Cadastra um novo cliente.
* `GET /clientes`: Vê a lista de todos os clientes.
* `GET /clientes/{id}`: Busca um cliente específico pelo ID.
* `PUT /clientes/{id}`: Atualiza os dados de um cliente.
* `DELETE /clientes/{id}`: Remove um cliente.

### 2. Pratos (`/pratos`)
* `POST /pratos`: Adiciona um novo prato ao cardápio.
* `GET /pratos`: Lista todos os pratos disponíveis.
* `GET /pratos/{id}`: Vê os detalhes de um prato.
* `PUT /pratos/{id}`: Atualiza o nome, descrição ou preço de um prato.
* `DELETE /pratos/{id}`: Remove um prato do cardápio.

### 3. Pedidos (`/pedidos`)
* `POST /pedidos`: Faz um novo pedido. Você precisa enviar o `clienteId` e uma lista de `pratoIds`.
* `GET /pedidos`: Lista todos os pedidos já feitos.
* `GET /pedidos/{id}`: Busca um pedido específico (trazendo os dados do cliente e os pratos).
