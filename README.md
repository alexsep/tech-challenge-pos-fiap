# Tech Challenge Fase 2

Este projeto é uma aplicação backend construída seguindo os princípios da arquitetura limpa. Esta aplicação utilizar o MongoDB como banco de dados.
O design do código foi estruturado da seguinte forma:

![](docs/diagramas/Estrutura-codigo.png?raw=true)


A infraestrutura da aplicação foi feita de forma que fosse possível executá-la em um ambiente Kubernetes, a imagem abaixo detalha como esta estrutura está definida:

![](docs/diagramas/infraestrutura.png?raw=true)

Foram criados dois namespaces para separar os contextos, no caso da aplicação foi feita uma configuração com autoscale automático e para o banco de dados apenas subimos uma instância. Os manifestos com as definições dessa infraestrutura podem ser encontrados na pasta `kubernetes` que está na raiz do repositório.


## Executando a aplicação
Existem 3 formas de executar a aplicação que estão descritas nos próximos passos.

### Executar a partir do projeto no github
- Tenha o Docker instalado em sua máquina.
- Clone o repositório para sua máquina local.
- Execute o comando: `docker compose up -d` para subir a aplicação com o banco de dados.
- As APIs da aplicação ficam acessíveis no endereço http://localhost:8080

### Executar a partir do projeto no github para debug
- Tenha o Docker instalado em sua máquina.
- Clone o repositório para sua máquina local.
- Altere o arquivo docker compose para que ele suba apenas o banco de dados.
- Execute o comando:` docker compose up -d` para subir o banco de dados.
- Execute a classe `TechChallengeApplication` em sua IDE
- As APIs da aplicação ficam acessíveis no endereço http://localhost:8080

### Executar a aplicação e o banco de dados no kubernetes

Para essa forma vamos utilizar a imagem do estado atual do repositório que foi publicada no dockerhub, o nome da imagem é `alexxsep/postech:1.1.0`.

**É necessário ter o kubernetes com o kubectl configurado em sua máquina (durante os testes foi utilizado o Docker Desktop com o Kubernetes ativo).** 

Primeiro é necessário subir a estrutura do banco de dados, essa estrutura fica em um namespace específico.
- Acesse o diretório `kubernetes/database`
- Execute o comando: `kubectl create namespace restaurante-db`
- Execute o comando: `kubectl apply -f Deployment.yaml`
- Execute o comando: `kubectl apply -f Service.yaml`
   

Após isso podemos subir a aplicação:
- Acesse o diretório `kubernetes/application`
- Execute o comando: `kubectl create namespace restaurante-app`
- Execute o comando: `kubectl apply -f Deployment.yaml`
- Execute o comando: `kubectl apply -f Service.yaml`
- Execute o comando: `kubectl apply -f hpa.yaml`

***Caso queira utilizar outro serviço de banco de dados basta alterar o `Deployment.yaml` com a configuração da string de conexão do banco na variável de ambiente `MONGODB_CONNECTION_STRING`. Nesse caso não é necessário subir a estrutura do banco de dados que foi feita nos passos anteriores***





## APIs

As APIs da aplicação ficam acessíveis no endereço `http://localhost:8080`, abaixo é possível ver a descrição das APIs e qual é a ordem de execução recomendada.

### Operações disponíveis
As APIs disponíveis são:
1. Criação, Edição e Busca do Cliente
2. Criação, Edição, Remoção e Busca de Produto
3. Busca de Produtos por Categoria
4. Criação de pedidos sem identificação do cliente, pedido com o cliente, busca de Pedidos e atualização de status
5. Realização e confirmação de pagamentos 
    
O pagamento do pedido apenas simula uma integração com meio de pagamento para gerar um QRCode. Com base nisso, as APIS de pagamento funcionam da seguinte forma:
- POST `v1/pedidos/:pedidoId/pagamentos` apenas gera um QRCode simulando uma integração com um meio de pagamento. Essa API não muda nenhum status no pedido.
- PUT `v1/pedidos/notifications` recebe o que seria a resposta de um webhook do meio de pagamento. O pedido é atualizado com base no request body recebido por essa API.


### Ordem de execução das APIs
***Para realizar pedidos é necessário cadastrar pelo menos 1 produto***

Com isso uma possível ordem para execução das APIs é:
1. Criar produtos
2. Criar pedidos sem identifição 
3. Realizar pagamentos e atualizar status dos pedidos

Outra ordem para execução das APIs é:
1. Criar produtos
2. Criar clientes
3. Criar pedidos com e sem identifição 
4. Realizar pagamentos e atualizar status dos pedidos


### Utilizando as APIs 
- Para fazer via postman importe o arquivo que está na pasta: `docs/postman-collections/Tech challenge.postman_collection.json`
- Para pegar a documentação swagger baixe o arquivo yaml que está na pasta: `docs/swagger/api-docs.yaml`
- Com a aplicação rodando acesse: http://localhost:8080/swagger-ui/index.html
