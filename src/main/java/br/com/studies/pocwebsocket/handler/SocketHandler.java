package br.com.studies.pocwebsocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class SocketHandler extends TextWebSocketHandler {

//    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {
        System.out.println(message);
        System.out.println("Digite a resposta: ");
        String a = new BufferedReader(new InputStreamReader(System.in)).readLine();
        session.sendMessage(new TextMessage(a));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
//        sessions.add(session);
    }


}
