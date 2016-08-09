package com.example.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.sockjs.SockJsException;
import org.springframework.web.socket.sockjs.SockJsTransportFailureException;
import org.springframework.web.socket.sockjs.transport.handler.HtmlFileTransportHandler;
import org.springframework.web.socket.sockjs.transport.session.AbstractHttpSockJsSession;

public class HtmlTransportHandler extends HtmlFileTransportHandler {


	@Override
	public void handleRequestInternal(ServerHttpRequest request, ServerHttpResponse response,
			AbstractHttpSockJsSession sockJsSession) throws SockJsException {
		
		System.err.println("TRANSPORT.... ");
		
		response.getHeaders().set("Set-Cookie", "JSESSIONID=alejrlqjwelq234; path=/");

		super.handleRequestInternal(request, response, sockJsSession);
		
		HttpHeaders headers = response.getHeaders();
		System.err.println(headers.toString());
	}
	
}
