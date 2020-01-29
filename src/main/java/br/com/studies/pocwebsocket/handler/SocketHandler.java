package br.com.studies.pocwebsocket.handler;

import br.com.studies.pocwebsocket.integration.KafkaServiceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class SocketHandler extends TextWebSocketHandler {

    WebSocketSession session;

    @Autowired
    private KafkaServiceRestClient kafkaServiceRestClient;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {
        System.out.println(message);
        System.out.println("Digite a resposta: ");
        String msg = new BufferedReader(new InputStreamReader(System.in)).readLine();
        kafkaServiceRestClient.sendToKafkaService(msg);
        this.session = session;
    }

    public void returnMessageToClient(String message) throws IOException {
        this.session.sendMessage(new TextMessage(message));
    }



}
