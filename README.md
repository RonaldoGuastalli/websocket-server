# websocket-server

- Este projeto faz uso do websocket entre um cliente (https://github.com/RonaldoGuastalli/websocket-client) trocando mensagens de uma assinatura produto.
- Um serviço (https://github.com/RonaldoGuastalli/kafka-service) é utilizado para recebimento de um objeto e após um processo de validação este objeto é envido para um tópico utilizando (Producer), Kafka como tecnologia.
- O retorno para o client é geredado por este projeto (Consumer) após o "consumo" de uma mensagem que esta em uma partição em um tópico do Kafka.
