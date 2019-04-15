package hello;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SENSOR_DATA")
public class SensorDataModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JsonIgnoreProperties
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@JsonIgnoreProperties
	private Timestamp timestamp;
	private float temperature;

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getSoilMoisture() {
		return soilMoisture;
	}

	public void setSoilMoisture(float soilMoisture) {
		this.soilMoisture = soilMoisture;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getSunlight() {
		return sunlight;
	}

	public void setSunlight(float sunlight) {
		this.sunlight = sunlight;
	}

	private float soilMoisture;
	private float humidity;
	private float sunlight;

	@Override
	public String toString() {
		return "{\"temperature\":\"" + temperature + "   \", \"soilMoisture\":\"" + soilMoisture
				+ "4000\", \"humidity\":\"" + humidity + "45\"}";

	}

}
