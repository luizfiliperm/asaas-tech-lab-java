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

Para subir a aplicação, basta rodar o comando ``docker-compose up --build tech-lab-java`` na raiz do projeto. Ao subir, a aplicação estará disponível na porta 8080.

Para executar os testes dos desafios, execute o comando ``git submodule update --init`` para obter os dados o script de teste e então ``docker-compose run k6`` para executar sempre que necessário. Lembre-se que o container com o projeto spring acima deve estar em execução.

Você também pode rodar o projeto localmente através dos atalhos da sua IDE, como por exemplo, o atalho `Run` e `Debug` do IntelliJ IDEA.

Para desenvolver localmente, você precisa ter instalado na seu dispositivo:

- Java 17.0.12
- Maven
- Docker e Docker-Compose

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
  - verificar limites diários configurados para cada usuário afim de limitar o quantidade de acessos ao endpoint de criação de cobrança

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
