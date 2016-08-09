package com.example.config;

import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.socket.sockjs.SockJsException;
import org.springframework.web.socket.sockjs.transport.SockJsServiceConfig;
import org.springframework.web.socket.sockjs.transport.SockJsSession;
import org.springframework.web.socket.sockjs.transport.TransportHandler;
import org.springframework.web.socket.sockjs.transport.TransportType;

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
		
		HttpSessionHandshakeInterceptor httpSessionHandshakeInterceptor = new HttpSessionHandshakeInterceptor();
		httpSessionHandshakeInterceptor.setCreateSession(true);
		httpSessionHandshakeInterceptor.setCopyAllAttributes(true);
		
		
		registry.addHandler(myHandler(), "websocket")
		.setAllowedOrigins("*")
		.addInterceptors(httpSessionHandshakeInterceptor)
		.withSockJS()
//		.setTransportHandlers(new HtmlTransportHandler())
		.setSessionCookieNeeded(true)
		.setClientLibraryUrl("http://172.10.12.137:8081/sockjs-1.1.1.js");
	}

}
