# Asaas Tech Lab Hackathon - Java

Seja bem-vindo(a)! Neste hackathon, o seu objetivo é tentar implementar soluções para 3 desafios.

Você recebeu um material impresso com a sua chave de API do Asaas e o link deste repositório. É muito importante que você leia todo este README para ficar atento às regras.

## Primeiros passos

Cada equipe ou participante deverá realizar um fork deste repositório, de forma privada e dando acesso admin ao time de avaliadores, nos seguintes usuários do GitHub

```
DouglasGiovanella
GlauberF
jpdev01
larisseabitencourt
marlon407
Thuran
willevini
```

## Pré-requisitos

Para subir a aplicação, basta rodar o comando ``docker-compose up --build tech-lab-app`` na raiz do projeto. Ao subir, a aplicação estará disponível na porta 8080.

Você também pode rodar o projeto localmente através dos atalhos da sua IDE, como por exemplo, o atalho `Run` e `Debug` do IntelliJ IDEA.

Para desenvolver localmente, você precisa ter instalado na seu dispositivo:

- Java 17.0.12
- Maven
- Docker e Docker-Compose

### Banco de dados

O projeto utiliza o SQLite, criado após a primeira execução do projeto, você pode acessa-lo por meio do seu editor preferido, com os seguintes dados:

**URL**: ``jdbc:sqlite:memory:myDb?cache=shared``

**username**: ``hackaton``

**password**: ``hackaton``

Caso você esteja utilizando a aplicação via Docker, o arquivo estará dentro do container, para acessa-lo você pode copia-lo com o comando abaixo na raiz do projeto:

```bash
docker cp <container-id>:'/app/memory:myDb?cache=shared' ./
```

Para descobrir o ``container-id``, execute ``docker ps`` para listar os containers que estão executando e seus respectivos Ids.

Sugestão de editores:

- Database connections do Intellij
- Sqlite Studio: https://sqlitestudio.pl/

### Testes

Para executar os testes dos desafios, execute o comando ``git submodule update --init`` para obter o script de testes que serão usados como parte da avaliação da sua solução.

Na pasta raiz do projeto, execute:

- ``docker-compose up --build tech-lab-app``: para subir a aplicação
- ``docker-compose run --rm k6``: Executar os testes

### Autenticação API
Para se autenticar na API, é necessário realizar o envio do Authorization no header da requisição com o token JWT do usuário que deseja se autenticar. Caso contrário, a API retornará status code 401.

- Usuário: user1@example.com
- `Authorization`: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyMUBleGFtcGxlLmNvbSJ9.5DK2w70WyEtf4HzBPXqsHfO8xoY30CG8rO0QQ5BQGZg`


- Usuário: user2@example.com
- `Authorization`: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyMkBleGFtcGxlLmNvbSJ9.20lKVN-pzmT8teJwCjH07Ebm2r-tkoY5POxkioy7Ie0`


- Usuário: user3@example.com
- `Authorization`: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyM0BleGFtcGxlLmNvbSJ9.pv5Y7HIqjloZtlMfVKwTMXv1IWQ7JA0Cl6iMLVmzvDQ`


- Usuário: user4@example.com
- `Authorization`: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyNEBleGFtcGxlLmNvbSJ9.XWTnHFbUZtTT5azD7ByadrLs42dM4vbsrQFvVZcq6m4`


- Usuário: user5@example.com
- `Authorization`: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyNUBleGFtcGxlLmNvbSJ9.G7UAkWKqtHOUxvW7zvqpMA9kjYk0uNZuEVMGkKHpBK8`


Exemplo de requisição:
```bash
curl --request GET \
  --url http://localhost:8080/payments \
  --header 'Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjb250YTFAZ21haWwuY29tIn0.aPbhx4QONeubDyPUoYHh9zlGU6LgyucX0TMIJjBjVO4' \
  --cookie JSESSIONID=FEFEFBE88B9D77DC2DD1A35592005F24
```

### Rotas pré configuradas

Dentro do arquivo ``application.properties``, adicione a chave de API de sua equipe no campo ``asaas.api.key`` para possibilitar a integração com a API Asaas.

Recursos já existentes podem ser consultados via Swagger na rota: http://localhost:8080/swagger-ui/index.html

## Desafios

**Desafio 1: Adicionar rate-limit, burst e quota na API**

O desafio consiste em receber uma alta carga de chamados na API e adicionar os limites. A aplicação deve realizar bloqueios baseado nas regras definidas:

- Rate-limit
  - máximo de 100 requisições por minuto **por IP** nos endpoints de Listar cobranças e Recuperar uma única cobrança
  - o Ip deverá ser enviado via header na request como `remote-ip` para que seja possível simular diferentes IPs
- Burst
  - máximo de 10 requisições paralelas **por IP** no endpoint de Criar cobrança
  - o Ip deverá ser enviado via header na request como `remote-ip` para que seja possível simular diferentes IPs
- Quota
  - verificar limites diário de 100 requisições para cada usuário, afim de limitar o quantidade de acessos ao endpoint de criação de cobrança

**Desafio 2: Fazer uma rotina de transferências automáticas no Asaas**
- O desafio é criar um Job que irá realizar transferências automáticas diariamente às 8h e 12h
- A aplicação deve integrar-se com o Asaas e realizar a transferência para uma das chave PIX abaixo:
  - `c4c52a44-070e-454a-8417-3cc312986a68`
  - `524d069f-7b61-425b-a211-6cb3ad4ba1b5`
  - `c2a81e71-a138-4e34-985e-a5abea4cd0f5`
  - `942c4a99-770b-40ac-9068-f152a0adc532`
  - `95f80f23-bf81-4d9c-9090-a16caa18f17e`
- Verificar o saldo e realizar transferências apenas se tiver saldo positivo

**Desafio 3: Aplicar idempotência**
- Fazer com que a rotina de criação de cobranças seja idempotente através de uma chave de idempotência informada no header da requisição utilizando a sintaxe `Idempotency-Key`: `value`

#### Documentação da API Asaas: https://docs.asaas.com

## O que será avaliado?

Você está trabalhando em uma aplicação em um ambiente produtivo, altamente escalável e que realiza múltiplos deploys diários. Diante disso, é necessário desenvolver uma solução definitiva, sem recorrer a paliativos ou medidas temporárias, garantindo a estabilidade e a eficiência contínua do sistema.

- Desempenho da aplicação
  - O código teve um bom desempenho?
  - As respostas foram rápidas?
  - O teste de desempenho trouxe quais resultados?
- Boas práticas
  - O código usou boas práticas de desenvolvimento de software?
- Organização
  - O código está bem organizado?
  - É fácil de ler e entender?
- Ferramental
  - Foram usadas as melhores ferramentas disponíveis?
- Tempo de entrega
  - O grupo respeitou o tempo de entrega?
 
## Premiações

- Melhor solução
  - O grupo/pessoa que entregou uma ou mais categorias e recebeu a maior nota de todos os avaliadores
  
- Entrega mais rápida
  - O grupo/pessoa que entregou todas as soluções funcionando em menor tempo
  
- Extra
  - O grupo/pessoa que entregou algo a mais, que chamou a atenção de todos os avaliadores

## Processo de submissão dos desafios

[Este é o formulário de envio das submissões.](https://docs.google.com/forms/d/e/1FAIpQLSfefkzby7VuA910I0KtIHiGGjVrj1ePDGkYwJZlitTuKnVOuQ/viewform?usp=sf_link)

- O encerramento da participação se dá pelo envio de um formulário com o link do repositório criado, o formulário precisará das informações:
  - Nome da equipe/pessoa
  - Link do repositório
  - Decisões tomadas
- Após o envio não podem mais ser feitas alterações no código e qualquer alteração será desconsiderada, podendo causar a desclassificação dos participantes
