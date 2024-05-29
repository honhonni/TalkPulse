package cn.edu.ncu.talkpulse.friends.service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ServerEndpoint("/ws/{uid}")
public class WebSocketServer {
    // 存放会话对象
    private static Map<Integer, Session> sessionMap = new HashMap<>();

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") Integer uid) {
        log.info("WebSocket：客户端 {} 建立连接",uid);
        sessionMap.put(uid, session);
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("uid") Integer uid) {
        log.info("WebSocket：收到来自客户端 {} 的信息:{}",uid,message);
    }


    /**
     * 连接关闭调用的方法
     *
     * @param uid
     */
    @OnClose
    public void onClose(@PathParam("uid") Integer uid) {
        log.info("WebSocket：客户端 {} 连接断开",uid);
        sessionMap.remove(uid);
    }


    /**
     * 群发
     *
     * @param message
     */
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                session.getBasicRemote().sendText(message);
                log.info("WebSocket：向所有客户端发送消息：{}",message);
                sessionMap.forEach((key,value)-> System.out.println("uid="+key));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 根据uid向客户端推送消息
     * @param uid
     * @param message
     */
    public void sendToUser(Integer uid,String message){
        Session session = sessionMap.get(uid);
        if(session!=null){
            try {
                session.getBasicRemote().sendText(message);
                log.info("WebSocket：向用户{} 发送消息：{}",uid,message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            log.info("WebSocket：用户{} 未登录",uid);
        }
    }

    /**
     * 向用户组发消息
     * @param ids
     * @param message
     */
    public void sendToGroup(List<Integer> ids,String message){
        log.info("---------------------------------------");
        ids.forEach(uid->{
            Session session = sessionMap.get(uid);
            if(session!=null){
                try {
                    session.getBasicRemote().sendText(message);
                    log.info("WebSocket：向用户{} 发送群聊消息通知：{}",uid,message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                log.info("WebSocket：用户{} 未登录",uid);
            }
        });
        log.info("---------------------------------------");
    }
}
