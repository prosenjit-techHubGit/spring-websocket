package hello;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyMessageHandler extends TextWebSocketHandler {

	@Autowired
	private SensorDataService sensorDataService;
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// The WebSocket has been closed
	}

	@Override
	@CrossOrigin
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// The WebSocket has been opened
		// I might save this session object so that I can send messages to it outside of
		// this method

		// Let's send the first message
		session.sendMessage(new TextMessage(buidJsonString(sensorDataService.getAllData())));
	}

	@Override
	@CrossOrigin
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		// A message has been received
		System.out.println("Message received: " + textMessage.getPayload());
	}

	public  static  String buidJsonString(List<SensorDataModel> sensorData) {

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			return objectMapper.writeValueAsString(sensorData);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
