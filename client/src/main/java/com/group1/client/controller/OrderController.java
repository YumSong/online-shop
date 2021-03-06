package com.group1.client.controller;

import com.group1.client.dto.OrderDto;
import com.group1.client.service.OrderService;
import com.group1.core.entity.client.Client;
import com.group1.core.entity.order.Order;
import com.group1.core.entity.order.OrderItem;
import com.group1.core.handler.SpringWebSocketHandler;
import com.group1.core.utils.*;
import com.group1.core.utils.base.model.Page;
import com.group1.core.utils.base.model.Pageable;
import com.group1.core.utils.jerseyPoolingClientFactory.JerseyPoolingClientFactoryImpl;
import org.apache.http.HttpResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.group1.core.interceptor.SpringWebSocketHandlerInterceptor.ATTRIBUTES_USER;
import static com.group1.core.interceptor.SpringWebSocketHandlerInterceptor.ATTRIBUTES_USERID;

@RestController
@RequestMapping("/client/order")
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource
    private JerseyPoolingClientFactoryImpl jerseyPoolingClientFactoryBean;

    @Bean   // 这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {
        return new SpringWebSocketHandler();
    }

    // 接收商家的http請求，并且將數據通過websocket返回到前臺
    @PostMapping("/websocket")
    @ResponseBody
    public ResultBody send(@RequestBody Message message) {
        ResultBody resultBody = new ResultBody();
        String str = JsonUtil.objectToJson(message);
        Order order =  JsonUtil.mapToObject((Map) message.getMap().get("order"),Order.class);
        orderService.update(order.getId(),order.getStatus());
        infoHandler().sendMessageToUser(message.getReceiverId(), new TextMessage(str));
        resultBody.addData("result", "success send");
        return resultBody;
    }

    @PostMapping
    @ResponseBody
    public ResultBody add(@RequestBody @Valid OrderDto orderDto, HttpSession session, Errors errors) {
        ResultBody resultBody = new ResultBody();
        if (!errors.hasErrors()) {
            try {
                Client user = (Client) session.getAttribute(ATTRIBUTES_USER);
                if (user == null) {
                    resultBody.addError("user", "請重新登陸");
                    return resultBody;
                }
                Order result = orderService.save(orderDto, user);
                Message message = new Message(result.getClient().getId(), result.getShopId());

                Map<String, Object> map = new HashMap<>();
                map.put("status", result.getStatus());
                map.put("order", result);
                message.setMap(map);

                javax.ws.rs.client.Client client = jerseyPoolingClientFactoryBean.getObject();
                WebTarget webTarget = client.target(PropertiesUtils.getProperty("ws_url"));
                Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
                String str = JsonUtil.objectToJson(message);
                Response response = invocationBuilder.post(Entity.entity(str, MediaType.APPLICATION_JSON_TYPE));
                System.out.println(response.getStatus());
                System.out.println(response);
            } catch (Exception e) {
                e.printStackTrace();
//                resultBody.addError("error", "client cannot get by factory");
            }

        } else {
            resultBody.addErrors(errors.getAllErrors());
        }
        return resultBody;
    }

    @DeleteMapping("/{orderId}")
    @ResponseBody
    public ResultBody delete(@PathVariable String orderId) {
        ResultBody resultBody = new ResultBody();
        Integer result = orderService.delete(orderId);
        if (result == 1) {
            resultBody.addData("result", result);
        } else {
            resultBody.addError("errors", "fail to delete");
        }
        return resultBody;
    }

    @PutMapping("/{orderId}/{status}")
    @ResponseBody
    public ResultBody update(@PathVariable String orderId, @PathVariable Integer status) {
        ResultBody resultBody = new ResultBody();
        Order order = orderService.update(orderId, status);
        if (order != null) {
            resultBody.addData("order", order);
        } else {
            resultBody.addError("errors", "fail to update");
        }
        return resultBody;
    }

    @GetMapping("/{page}")
    @ResponseBody
    public ResultBody findAll(@MatrixVariable(name = "offset", pathVar = "page") Integer offset,
                              @MatrixVariable(name = "size", pathVar = "page") Integer size, HttpSession httpSession) {
        String clientId = (String) httpSession.getAttribute(ATTRIBUTES_USERID);
        Pageable pageable = new Pageable(offset + 1, size);
        ResultBody resultBody = new ResultBody();
        Page<OrderDto> page = orderService.findAllById(clientId, pageable);
        if (page == null)
            resultBody.addError("errors", "會話超時，請重新登陸");
        else
            resultBody.addData("orderList", page);
        return resultBody;
    }

    @GetMapping("/detail/{orderId}")
    @ResponseBody
    public ResultBody findDetailByOrderId(@PathVariable String orderId) {
        ResultBody resultBody = new ResultBody();
        List<OrderItem> orderItems = orderService.findOrderDetailByOrderId(orderId);
        resultBody.addData("orderItems", orderItems);
        return resultBody;
    }
}
