package com.group1.core.interceptor;

import com.group1.core.entity.merchant.Merchant;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;


public class SpringWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {
    public static final String ATTRIBUTES_USERID = "session_userId";
    public static final String ATTRIBUTES_USER = "session_user";
    public static final String MERCHANT = "merchant";


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Before Handshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String id;
                Merchant merchant = (Merchant) session.getAttribute(MERCHANT);
                if (merchant==null) {
                    id="default-system";
                }else{
                    id = merchant.getId();
                }
                attributes.put(ATTRIBUTES_USERID,id);
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        // TODO Auto-generated method stub
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
