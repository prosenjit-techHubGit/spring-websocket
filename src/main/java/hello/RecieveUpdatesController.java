package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class RecieveUpdatesController {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private SensorDataService sensorDataService;
	

	@PostMapping("/receiveSensorData")
	public void addItems(@RequestBody List<SensorDataModel> items) {

		sensorDataService.saveSenorData(items);

		template.convertAndSend("/topic/notification", buidJsonString(sensorDataService.getAllData()));

	}

	public static String buidJsonString(List<SensorDataModel> sensorData) {

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
