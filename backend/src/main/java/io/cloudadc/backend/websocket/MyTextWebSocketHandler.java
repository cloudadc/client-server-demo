package io.cloudadc.backend.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyTextWebSocketHandler extends TextWebSocketHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTextWebSocketHandler.class);
    
	private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("Connection Established: " + session.toString());
		sessions.add(session);
		super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("Connection Closed: " + session.toString());
		sessions.remove(session);
		super.afterConnectionClosed(session, status);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info(session.getId() + " received: [" + message.getPayload() + "]");
		sessions.forEach(webSocketSession -> {
			try {
				Msg msg = new Msg(session.getId(), message.getPayload(), new Date());
				TextMessage toClient = new TextMessage(msg.payload());
				webSocketSession.sendMessage(toClient);
			} catch (IOException e) {
				LOGGER.error("Error occurred.", e);
			}
		});
	}

}
