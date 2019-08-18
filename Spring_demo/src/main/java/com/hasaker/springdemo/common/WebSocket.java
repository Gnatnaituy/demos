package com.hasaker.springdemo.common;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author hasaker
 * @create_date 2019-08-16 10:13
 * @description
 */

@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(username, session);
        System.out.println(username + "[websocket] has new connection, total: " + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("[websocket] disconnected, total: " + webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("[websocket] received new message: " + message);
    }

    public void sendAllMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            System.out.println("[websocket] broadcast: " + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(String username, String message) {
        System.out.println("[websocket] send one message: " + message);
        Session session = sessionPool.get(username);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof WebSocket)) {
            return false;
        }

        WebSocket webSocket = (WebSocket) o;

        return session.equals(webSocket.session);
    }
}
