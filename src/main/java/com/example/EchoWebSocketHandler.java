package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class EchoWebSocketHandler extends TextWebSocketHandler {

	 //유저 집합 리스트
    static List<WebSocketSession> sessionUsers = Collections.synchronizedList(new ArrayList<WebSocketSession>());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionUsers.add(session);
	}
	
	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		
		session.getHandshakeHeaders().set("aljdksflkajsdf", "asdlfkjalkdsjf");
		
		
		String id = session.getId();
		System.err.println("session="+id);
		
		
		String payloadMessage = (String) message.getPayload();
		try {
			
			System.out.println("payload message : " + payloadMessage);
			 //username이 있으면 전체에게 메시지를 보낸다.
	        Iterator<WebSocketSession> iterator = sessionUsers.iterator();
	        while(iterator.hasNext()){
	            iterator.next().sendMessage(new TextMessage(payloadMessage));
	        }

//			System.out.println("payload message : " + payloadMessage);
//			session.sendMessage(new TextMessage(payloadMessage));

		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (JsonMappingException jme) {
			jme.printStackTrace();
		}
	}
}
