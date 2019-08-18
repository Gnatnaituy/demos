package com.hasaker.springdemo.controller;

import com.hasaker.springdemo.common.WebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther hasaker
 * @create_date 2019-08-16 14:09
 * @description
 */
@RestController
@RequestMapping("/websocket")
public class WebSocketController {
    private WebSocket webSocket;

    public WebSocketController(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @GetMapping("/sendAllWebSockets")
    public String sendAll() {
        String text = "你们好! 这是websocket群体发送!";
        webSocket.sendAllMessage(text);

        return text;
    }

    @GetMapping("/sendWebSocket/{username}")
    public String sendWebSocket(@PathVariable("username") String username) {
        String message = username + ", 你好, 这是websocket单人发送!";
        webSocket.sendOneMessage(username, message);

        return message;
    }
}
