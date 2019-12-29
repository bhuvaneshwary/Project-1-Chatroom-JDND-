package edu.udacity.java.nano.chat;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static HashMap<String, String> users = new HashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        onlineSessions.forEach((id, session) -> {
            try{
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String user) {
        //TODO: add on open connection.
        // this is how you can get the usernamep
        onlineSessions.put(session.getId(), session);
        // Getting username through path param.
        users.put(session.getId(), user);
        sendMessageToAll(MessageModel.strToJson("ENTERED THE CHAT", user, onlineSessions.size(), "ENTER"));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        MessageModel message = JSON.parseObject(jsonStr, MessageModel.class);
        sendMessageToAll(MessageModel.strToJson(message.getMsg(), message.getUsername(), onlineSessions.size(), "SPEAK"));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());
        sendMessageToAll(MessageModel.strToJson("LEAVE THE CHAT", "", onlineSessions.size(), "LEAVE"));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
