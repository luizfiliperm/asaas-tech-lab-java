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

Para rodar a aplicação, siga os passos abaixo:

1. Clone o repositório
2. Abra o projeto em sua IDE de preferência
3. Execute o comando abaixo para gerar o artefato: 
```shell
mvn clean package
```
4. Execute o comando abaixo para subir o projeto:
```shell
docker-compose up
```

Você também pode rodar o projeto através dos atalhos da sua IDE, como por exemplo, o atalho `Run` e `Debug` do IntelliJ IDEA.

## Desafios

**Desafio 1: Adicionar rate-limit, burst e quota na API**
- O desafio consiste em receber uma alta carga de chamados na API e adicionar os limites
- A aplicação deve realizar bloqueios baseado nas regras definidas
- É interessante criar um endpoint para resetar os limites
- Rate-limit
  - máximo de 100 requisições por minuto nos endpoints de Lista cobranças e Recuperar uma única cobrança
- Burst (semáforo)
  - máximo de 10 requisições paralelas no endpoint de Criar cobrança
- Quota (semáforo)
  - verificar limites diários configurados para cada usuário para limitar o número de cobranças criadas por dia

**Desafio 2: Fazer uma rotina de transferências automáticas no Asaas**
- O desafio é criar um Job que irá realizar transferências automáticas diariamente às 8h e 12h
- A aplicação deve realizar a integração com o Asaas, receber pagamentos de cobranças e realizar a transferência para outra conta via Pix
- Verificar saldo e realizar transferências apenas se tiver saldo positivo

**Desafio 3: Aplicar idempotência**
- Fazer com que o controller de cobranças seja idempotente

## O que será avaliado?
- Desempenho da aplicação
  - O código teve um bom desempenho?
  - As respostas foram rápidas?
  - O teste de desempenho trouxe quais resultados?
- Código funcional
  - É possível rodar o código de uma forma fácil?
  - Não é necessário instalar nenhuma ferramenta extra?
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
