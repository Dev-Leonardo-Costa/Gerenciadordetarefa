
Gerenciador de Tarefas - Java Spring
Este é um gerenciador de tarefas simples desenvolvido usando Java Spring e PostgreSQL como banco de dados.

Índice
Instalação
Configuração do Banco de Dados
Como Usar
API Endpoints
Contribuindo
Licença
Instalação
Certifique-se de ter o Java e o Maven instalados em seu sistema. Clone o repositório e execute o seguinte comando para instalar as dependências:

bash
Copy code
# Clone o repositório
git clone https://github.com/seu-usuario/gerenciador-de-tarefas.git

# Entre no diretório do projeto
cd gerenciador-de-tarefas

# Instale as dependências
mvn install
Configuração do Banco de Dados
Este projeto usa o PostgreSQL como banco de dados. Certifique-se de ter um servidor PostgreSQL em execução e atualize as configurações no arquivo application.properties com as informações do seu banco de dados.

properties
Copy code
# src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
Como Usar
Execute o aplicativo usando o seguinte comando:

bash
Copy code
# Execute o aplicativo
mvn spring-boot:run
O aplicativo estará disponível em http://localhost:8080. Você pode acessar a interface do Swagger para testar os endpoints da API em http://localhost:8080/swagger-ui.html.

API Endpoints
Listar Tarefas: GET /tarefas
Obter Tarefa por ID: GET /tarefas/{id}
Criar Tarefa: POST /tarefas
Atualizar Tarefa: PUT /tarefas/{id}
Excluir Tarefa: DELETE /tarefas/{id}
Exemplos de uso podem ser encontrados na interface do Swagger.

Contribuindo
Sinta-se à vontade para contribuir! Abra um issue para discutir novas funcionalidades ou correções de bugs. Se quiser contribuir com código, siga as instruções no guia de Contribuição.
