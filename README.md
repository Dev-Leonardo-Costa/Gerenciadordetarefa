
# Gerenciador de Tarefas <br>
Este é um gerenciador de tarefas simples desenvolvido usando Java Spring e PostgreSQL como banco de dados.

Índice
Instalação
Configuração do Banco de Dados
Como Usar
API Endpoints
Instalação
Certifique-se de ter o Java e PostgreSQL instalados.

<br> Clone o repositório e execute o seguinte comando para instalar as dependências:

# Clone o repositório
```bash
git clone git@github.com:Dev-Leonardo-Costa/Gerenciadordetarefa.git
```
# Entre no diretório do projeto
cd gerenciadordetarefas

# Instale as dependências
Este projeto usa o PostgreSQL como banco de dados. Certifique-se de ter um servidor PostgreSQL em execução e atualize as configurações no arquivo application.properties com as informações do seu banco de dados.

properties
Copy code
# src/main/resources/application.properties
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco<br>
spring.datasource.username=seu-usuario<br>
spring.datasource.password=sua-senha<br>
```
Como Usar

Execute o aplicativo usando uma IDE de preferência

# Execute o aplicativo
mvn spring-boot:run
O aplicativo estará disponível em http://localhost:8080. Você pode acessar a interface do Swagger para testar os endpoints da API em http://localhost:8080/swagger-ui/index.html.

# Endpoints fornecidos pela API
## Pessoa
### [ PUT ] /pessoas/{pessoaId}  altera dados de uma pessoa       
### [ DELETE ] /pessoas/{pessoaId} remove uma pessoa pelo id
### [ GET ] /pessoas busca todas as pessoas
### [ POST ] /pessoas registra uma nova pessoa
### [ GET ] /pessoas/gastos busca todas as pessoas por nome e periodo  

## Tarefa
### [PUT] finaliza uma tarefa que esteja associada com uma pessoa 
### [PUT] aloca a tarefa para uma pessoa 
### [POST] adiciona uma nova tarefa
### [GET] busca as 3 tarefas que estejam sem pessoa alocada com prazo antigo

## Departamento
### [GET] busca departamentos com quantidade de pessoas e tarefas

## Teste a API Remotamente

Explore e teste os endpoints da API usando a interface Swagger hospedada no [Render](https://app-geranciador-de-tarefa.onrender.com/swagger-ui/index.html).

**[Swagger Online](https://app-geranciador-de-tarefa.onrender.com/swagger-ui/index.html)**


