package com.yuhao.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.AbstractWebSocketMessage;
import org.springframework.web.socket.config.annotation.AbstractWebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Configuration
// @EnableWebSocketMessageBroker
/**
 * 1.@EnableWebSocketMessageBroker开启使用stomp协议来传输基于代理（message broke）的消息
 * 控制器支持@MessageMapping就像使用@RequestMapping一样
 *2.注册stomp的endpoint，并映射指定URL
 *3.注册一个stomp协议的节点，并指定使用SockJS协议
 *4.配置消息代理（Message Broker）
 *5.广播式应配置一个/topic消息代理
 * */
public class WebSocketConfig {
    //不仅限于聊天/消息传递应用程序。它们适用于需要实时更新和即时信息交换的任何应用程序
}
