package com.group1.core.handler;

import com.group1.core.utils.JsonUtil;
import com.group1.core.utils.Message;
import com.group1.core.utils.PropertiesUtils;
import com.group1.core.utils.jerseyPoolingClientFactory.JerseyPoolingClientFactoryImpl;
import com.group1.core.utils.jerseyPoolingClientFactory.JerseyPoolingClientFactroy;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.group1.core.interceptor.SpringWebSocketHandlerInterceptor.ATTRIBUTES_USERID;

public class SpringWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> users;//这个会出现性能问题，最好用Map来存储，key用userid

    @Resource
    private JerseyPoolingClientFactroy jerseyPoolingClientFactoryBean;

    static {
        users = new ConcurrentHashMap<>();
    }
    @Resource
    private JerseyPoolingClientFactroy jerseyPoolingClientFactoryBean;

    public SpringWebSocketHandler() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("connect to the websocket success......当前数量:" + users.size());
        String userId = (String) session.getAttributes().get(ATTRIBUTES_USERID);
        users.put(userId, session);
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        //TextMessage returnMessage = new TextMessage("你将收到的离线");
        //session.sendMessage(returnMessage);
    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String userId = (String) session.getAttributes().get(ATTRIBUTES_USERID);
        System.out.println("用户" + userId + "已退出！");
        users.remove(userId);
        System.out.println("剩余在线用户" + users.size());
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println(message.getPayload());
//        super.handleTextMessage(session, message);
        javax.ws.rs.client.Client client = jerseyPoolingClientFactoryBean.getObject();
        WebTarget webTarget = client.target(PropertiesUtils.getProperty("ws_url"));
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        String str = message.getPayload();
        //Message msg = JsonUtil.jsonToObject(str,Message.class);
        Response response = invocationBuilder.post(Entity.entity(str,MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.getStatus());
        System.out.println(response);
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String userId = (String) session.getAttributes().get(ATTRIBUTES_USERID);
        users.remove(userId);
        if (session.isOpen()) {
            session.close();
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {
        WebSocketSession socketSession = users.get(userId);
        if (socketSession != null) {
            try {
                if (socketSession.isOpen()) {
                    socketSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
