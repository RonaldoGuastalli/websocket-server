package br.com.studies.pocwebsocket.handler;

import br.com.studies.pocwebsocket.integration.KafkaServiceRestClient;
import br.com.studies.pocwebsocket.model.KafkaMessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private KafkaServiceRestClient kafkaServiceRestClient;

    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

        System.out.println(message);

        String hostname = getHostname(message.getPayload());

        if (sessions.get(hostname) != null) {
            sessions.put(hostname, session);
        }
        if (sessions.get(session.getId()) != null) {
            sessions.put(session.getId(), session);
        }

        kafkaServiceRestClient.sendToKafkaService(message.getPayload());
    }

    public void returnMessageToClient(String message) throws Exception {
        String hostname = getHostname(message);
        WebSocketSession webSocketSession = sessions.get(hostname);
        webSocketSession.sendMessage(new TextMessage("Sessao logado no hostname: " + hostname + " é " + webSocketSession.getId()));
        webSocketSession.sendMessage(new TextMessage(message));
    }

    private String getHostname(String value) throws Exception {
        return new ObjectMapper().readValue(value, KafkaMessageModel.class).getHostname();
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }

}
