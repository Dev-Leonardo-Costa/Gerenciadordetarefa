# Gerenciador de tarefas

O Gerenciador de Tarefas é uma aplicação dedicada a 
simplificar o processo de organização e acompanhamento de tarefas em um ambiente de equipe. 
Desenvolvido para oferecer uma experiência eficiente e intuitiva, 
o sistema permite a adição, atribuição e conclusão de tarefas de forma centralizada.

## Endpoints

A seguir estão os principais endpoints da API:

### Adicionar uma pessoa

- **Método**: POST
- **Endpoint**: `/pessoas`
- **Descrição**: Adiciona uma nova pessoa.

### Alterar uma pessoa

- **Método**: PUT
- **Endpoint**: `/pessoas/{id}`
- **Descrição**: Altera os dados de uma pessoa específica.

### Remover uma pessoa

- **Método**: DELETE
- **Endpoint**: `/pessoas/{id}`
- **Descrição**: Remove uma pessoa específica.

### Adicionar uma tarefa

- **Método**: POST
- **Endpoint**: `/tarefas`
- **Descrição**: Adiciona uma nova tarefa.

### Alocar uma pessoa em uma tarefa do mesmo departamento

- **Método**: PUT
- **Endpoint**: `/tarefas/alocar/{id}`
- **Descrição**: Aloca uma pessoa em uma tarefa com base no departamento.

### Finalizar uma tarefa

- **Método**: PUT
- **Endpoint**: `/tarefas/finalizar/{id}`
- **Descrição**: Finaliza uma tarefa específica.

### Listar pessoas com nome, departamento e total de horas gastas

- **Método**: GET
- **Endpoint**: `/pessoas`
- **Descrição**: Lista pessoas com informações detalhadas.

### Buscar pessoas por nome e período, retorna média de horas gastas por tarefa

- **Método**: GET
- **Endpoint**: `/pessoas/gastos`
- **Descrição**: Busca pessoas por nome e período, retornando a média de horas gastas por tarefa.

### Listar 3 tarefas sem pessoa alocada com prazos mais antigos

- **Método**: GET
- **Endpoint**: `/tarefas/pendentes`
- **Descrição**: Lista as 3 tarefas sem pessoa alocada, ordenadas pelos prazos mais antigos.

### Listar departamento e quantidade de pessoas e tarefas

- **Método**: GET
- **Endpoint**: `/departamentos`
- **Descrição**: Lista os departamentos com a quantidade de pessoas e tarefas associadas.
