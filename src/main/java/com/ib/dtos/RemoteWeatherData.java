package com.ib.dtos; 

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.ib.misc.UnixTimestampDeserializer;

/**
 * RemoteWeatherData maps to the data given by remote weather data service
 *  
 * @author ishmael
 *
 * <pre>
 * {
 * 	"coord": {
 * 		"lon": 55.3,
 * 		"lat": 25.26
 * 	},
 * 	"sys": {
 * 		"type": 1,
 * 		"id": 7100,
 * 		"message": 0.0631,
 * 		"country": "AE",
 * 		"sunrise": 1409882464,
 * 		"sunset": 1409927621
 * 	},
 * 	"weather": [{
 * 		"id": 800,
 * 		"main": "Clear",
 * 		"description": "Sky is Clear",
 * 		"icon": "01d"
 * 	}],
 * 	"base": "cmc stations",
 * 	"main": {
 * 		"temp": 39.52,
 * 		"pressure": 998,
 * 		"humidity": 22,
 * 		"temp_min": 39,
 * 		"temp_max": 40
 * 	},
 * 	"wind": {
 * 		"speed": 5.7,
 * 		"deg": 310
 * 	},
 * 	"clouds": {
 * 		"all": 0
 * 	},
 * 	"dt": 1409914800,
 * 	"id": 292223,
 * 	"name": "Dubai",
 * 	"cod": 200
 * }
 * </pre>
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RemoteWeatherData {
	
	@JsonProperty("dt")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	private Date date;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("cod")
	private int code;
	
	@JsonProperty("coord")
	private CoordinateDTO coordinates;
	
	@JsonProperty("sys")
	private SystemDTO system;
	
	@JsonProperty("main")
	private MainDTO main;
	
	@JsonProperty("weather")
	private List<WeatherDTO> weather;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public CoordinateDTO getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(CoordinateDTO coordinates) {
		this.coordinates = coordinates;
	}

	public SystemDTO getSystem() {
		return system;
	}

	public void setSystem(SystemDTO systemDTO) {
		this.system = systemDTO;
	}

	public MainDTO getMain() {
		return main;
	}

	public void setMain(MainDTO mainDTO) {
		this.main = mainDTO;
	}

	public List<WeatherDTO> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherDTO> weatherDTO) {
		this.weather = weatherDTO;
	}
}
