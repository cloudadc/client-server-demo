package io.cloudadc.backend.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyTextWebSocketHandler extends TextWebSocketHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTextWebSocketHandler.class);
    	
	static Map<String,WebSocketSession> activeMap = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("Connection Established: " + session.toString());
		activeMap.put(session.getId(),session);
		sendMessageForAll(session.getId() + " join in !");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("Connection Closed: " + session.toString() + ", " + status);
		close(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info(session.getId() + " received: [" + message.getPayload() + "]");
		Msg msg = new Msg(session.getId(), message.getPayload(), new Date());
		sendMessageForAll(msg.toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		close(session);
		LOGGER.error("Error occurred.", exception.getMessage());
	}
	
	public void sendMessageForAll(String message) {
        activeMap.forEach((sessionId, session) -> sendMessage(session, message));
    }
	
	 private void sendMessage(WebSocketSession session, String message) {
		 try {
			 TextMessage toClient = new TextMessage(message);
			 session.sendMessage(toClient);
		 } catch (IOException e) {
			 LOGGER.error("Error occurred.", e);
		 }
	 }
	 
	 private void close(WebSocketSession session) {
	        try {
	            activeMap.remove(session.getId());
	            session.close();
	        } catch (IOException e) {
	        	LOGGER.error("Error occurred.", e);
	        }
	    }

}
