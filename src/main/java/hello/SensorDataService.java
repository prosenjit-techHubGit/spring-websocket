package hello;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class SensorDataService {

	@Autowired
	private SensorDataRepository sensorDataRepository;

	public List<SensorDataModel> getAllData() {

		List<SensorDataModel> sensorData = null;

		sensorData = sensorDataRepository.findAll();
		for (SensorDataModel sensorDataModel : sensorData) {
			System.out.println("Sensor Data" + sensorDataModel.toString());

		}

		return sensorData;

	}

	public List<Long> saveSenorData(List<SensorDataModel> items) {

		List<SensorDataModel> itemEntities = null;
		List<Long> newItemIds = null;

		try {
			itemEntities = sensorDataRepository.saveAll(items);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (itemEntities != null && itemEntities.size() != 0) {

			newItemIds = itemEntities.stream().map(e -> e.getId()).collect(Collectors.toList());
		}

		return newItemIds;

	}

}
