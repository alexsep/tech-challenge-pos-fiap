# Tech Challenge Fase 2

Este projeto é uma aplicação backend construída seguindo os princípios da arquitetura limpa. Esta aplicação utilizar o MongoDB como banco de dados.


## Executando a aplicação
Existem 3 formas de executar a aplicação e que estão descritas nos próximos passos.

### Executar a partir do projeto no github
- Tenha o Docker instalado em sua máquina.
- Clone o repositório para sua máquina local.
- Execute o comando: docker compose up -d para subir a aplicação com o banco de dados.
- As APIs da aplicação ficam acessíveis no endereço http://localhost:8080

### Executar a partir do projeto no github para debug
- Tenha o Docker instalado em sua máquina.
- Clone o repositório para sua máquina local.
- Altere o arquivo docker compose para que ele suba apenas o banco de dados.
- Execute o comando: docker compose up -d para subir o banco de dados.
- Execute a classe `TechChallengeApplication` em sua IDE
- As APIs da aplicação ficam acessíveis no endereço http://localhost:8080

### Executar a aplicação e o banco de dados no kubernetes

Para essa forma vamos utilizar a imagem do estado atual do repositório que foi publicada no dockerhub, o nome da imagem é xxxxxxx.

**É necessário ter o kubernetes com o kubectl configurado em sua máquina (durante os testes foi utilizado o Docker Desktop com o Kubernetes ativo).** 



Primeiro é necessário subir a estrutura do banco de dados, essa estrutura fica em um namespace específico.
- Acesse o diretório `kubernetes/database`
- Execute o comando: criar namespace TODO
- Execute o comando: aplicar deployment
- Execute o comando: aplicar service
   

Após isso podemos subir a aplicação:
- Acesse o diretório `kubernetes/application`
- Execute o comando: criar namespace TODO
- Execute o comando: aplicar deployment
- Execute o comando: aplicar service
- Execute o comando: aplicar hpa

***Caso queira utilizar outro serviço de banco de dados basta alterar o `Deployment.yaml` com a configuração da string de conexão do banco na variável de ambiente `MONGODB_CONNECTION_STRING`. Nesse caso não é necessário subir a estrutura do banco de dados que foi feita nos passos anteriores***



## APIs

As APIs da aplicação ficam acessíveis no endereço http://localhost:8080

APIs Disponíveis
As APIs disponíveis são:
1. Criação, Edição e Busca do Cliente
2. Criação, Edição, Remoção e Busca de Produto
3. Busca de Produtos por Categoria
4. Criação de pedidos sem identificação do cliente, pedido com o cliente e Busca de Pedidos

Para utilizar as APIs importe o arquivo: https://github.com/pietroow/tech-challenge-pos-tech/blob/main/Tech-challenge.postman_collection.json no postman
