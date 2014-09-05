package com.ib.dtos; 

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author ishmael
 *
 * Format
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
public class RemoteWeatherData {
	
	@JsonProperty("dt")
	private String date;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("cod")
	private Status code;
	
	@JsonProperty("coord")
	private CoordinateDTO coordinates;
	
	@JsonProperty("sys")
	private SystemDTO systemDTO;
	
	@JsonProperty("main")
	private MainDTO mainDTO;
	
	@JsonProperty("weather")
	private List<WeatherDTO> weatherDTO;
	
	public class CoordinateDTO
	{
		@JsonProperty("lat")
		public double latitude;
		
		@JsonProperty("lon")
		public double longitude;
	}
	
	public class SystemDTO
	{
		@JsonProperty("country")
		public String country;
		
		@JsonProperty("sunrise")
		public String sunrise;
		
		@JsonProperty("sunset")
		public String sunset;
	}
	
	public class MainDTO
	{
		@JsonProperty("temp")
		public String temperature;
		
		@JsonProperty("temp_min")
		public String miniumumTemperature;
		
		@JsonProperty("temp_max")
		public String maximumTemperature;
	}
	
	public class WeatherDTO
	{
		@JsonProperty("id")
		public String id;
		
		@JsonProperty("main")
		public String main;
		
		@JsonProperty("description")
		public String description;
	}
}
