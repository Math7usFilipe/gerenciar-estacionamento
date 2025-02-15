
# Estacionamento API

Este projeto é uma API REST para gerenciar um estacionamento de carros e motos. Ele permite registrar entradas e saídas de veículos, gerar relatórios e realizar outras operações administrativas para o gerenciamento de um estacionamento.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.3.8**: Framework para desenvolvimento rápido de aplicações Java.
- **Spring Data JPA**: Integração com banco de dados utilizando JPA.
- **Spring Security**: Para autenticação e controle de acesso.
- **H2 Database**: Banco de dados em memória (utilizado para desenvolvimento).
- **MySQL**: Banco de dados para produção (configurável via Docker).
- **Lombok**: Biblioteca para redução de boilerplate no código.
- **JUnit 5**: Framework de testes unitários.
- **Mockito**: Biblioteca para testes unitários e mocks.
- **Hibernate**: Framework ORM (Object-Relational Mapping) para mapeamento de entidades Java para tabelas no banco de dados.

## Como Rodar o Projeto

1. **Clone o repositório:**

   ```bash
   git clone git@github.com:Math7usFilipe/gerenciar-estacionamento.git
   cd estacionamento
   ```

2. **Configuração do Banco de Dados:**

   A aplicação pode ser executada com um banco de dados em memória (H2) ou com MySQL (via Docker).

    - Para usar o **H2 em memória**, basta rodar a aplicação. O banco de dados será criado automaticamente.
    - Para usar o **MySQL com Docker**, siga os passos abaixo:

        - **Iniciar o container MySQL com Docker:**

          Crie um arquivo `docker-compose.yml`:

          ```yaml
          version: '3'
          services:
            mysql:
              image: mysql
              ports:
                - "3306:3306"
              environment:
                - MYSQL_USER=springuser
                - MYSQL_PASSWORD=ThePassword
                - MYSQL_DATABASE=db_example
                - MYSQL_ROOT_PASSWORD=root
              volumes:
                - "./conf.d:/etc/mysql/conf.d:ro"
          ```

        - Execute o seguinte comando para iniciar o MySQL:

          ```bash
          docker-compose up -d
          ```

3. **Configurações do Banco de Dados:**

   Caso esteja utilizando o MySQL, configure o arquivo `application.properties` com as seguintes informações:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_example
   spring.datasource.username=springuser
   spring.datasource.password=ThePassword
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

4. **Rodar o Projeto:**

   Utilize o Maven para compilar e rodar a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   A aplicação será iniciada na porta `8080` por padrão.

## Como Testar a API

### 1. **Registrar Entrada de Veículo**

- **Método**: `POST`
- **Endpoint**: `/api/movimentacoes/entrada`
- **Exemplo de Requisição**:
  ```bash
  curl -X POST http://localhost:8080/api/movimentacoes/entrada      -H "Content-Type: application/json"      -d '{
    "veiculo": {
      "id": 1,
      "placa": "ABC-1234",
      "marca": "Ford",
      "modelo": "Fiesta",
      "cor": "Preto",
      "tipo": "CARRO"
    },
    "estabelecimento": {
      "id": 1,
      "nome": "Estacionamento Central"
    }
  }'
  ```

### 2. **Registrar Saída de Veículo**

- **Método**: `POST`
- **Endpoint**: `/api/movimentacoes/{id}/saida`
- **Exemplo de Requisição**:
  ```bash
  curl -X POST http://localhost:8080/api/movimentacoes/1/saida
  ```

### 3. **Gerar Relatório de Entrada e Saída de um Veículo**

- **Método**: `GET`
- **Endpoint**: `/api/movimentacoes/relatorio/{veiculoId}`
- **Exemplo de Requisição**:
  ```bash
  curl http://localhost:8080/api/movimentacoes/relatorio/1
  ```

### 4. **Gerar Sumário de Movimentações por Hora**

- **Método**: `GET`
- **Endpoint**: `/api/movimentacoes/sumario`
- **Exemplo de Requisição**:
  ```bash
  curl "http://localhost:8080/api/movimentacoes/sumario?inicio=2025-02-15T10:00:00&fim=2025-02-15T11:00:00"
  ```

## Configurações Necessárias

1. **Banco de Dados**: Utilize o H2 (em memória) ou configure o MySQL para produção conforme explicado acima.
2. **Ambiente de Desenvolvimento**: Certifique-se de que o JDK 17 esteja instalado.

    - **Instalação do JDK 17** (se necessário):
        - [Baixar JDK 17](https://jdk.java.net/17/).
        - Verifique a instalação executando:
          ```bash
          java -version
          ```

3. **Ferramentas de Desenvolvimento**:
    - **IDE recomendada**: IntelliJ IDEA, Eclipse ou VSCode.
    - **Ferramenta de Build**: Maven (ou `./mvnw` caso não tenha o Maven instalado localmente).

## Como Executar os Testes

Os testes podem ser executados utilizando o Maven. Para rodar todos os testes do projeto:

```bash
./mvnw test
```

## Contribuições

Se você deseja contribuir para este projeto, por favor, siga os passos abaixo:

1. Faça um **fork** deste repositório.
2. Crie uma nova **branch** para suas alterações.
3. Envie um **pull request** explicando suas alterações.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
