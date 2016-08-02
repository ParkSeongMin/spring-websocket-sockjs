package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.EchoWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Bean
	public WebSocketHandler myHandler() {
		return new EchoWebSocketHandler();
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "websocket")
		.setAllowedOrigins("*")
		.withSockJS()
//		.setClientLibraryUrl("http://172.10.12.150:8081/sockjs-1.1.1.js");
		.setClientLibraryUrl("http://172.10.12.137:8081/sockjs-1.1.1.js");
	}

}
