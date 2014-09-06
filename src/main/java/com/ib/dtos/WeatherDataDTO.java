package com.ib.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import com.ib.misc.DateSerializer;
import com.ib.models.Location;
import com.ib.models.LocationWeatherCondition;
import com.ib.models.LocationWeatherData;

/**
 * Weather dto is the representation of the json response exposed by the app
 *  
 * @author ishmael
 *
 *<pre>
 * {
 * 	"date": "14-08-2014 13:30:45",
 * 	"city": {
 * 		"name": "Dubai",
 * 		"country": "AE",
 * 		"sunrise": "04:30",
 * 		"sunset": "18:50",
 * 		"coord": {
 * 			"lon": 55.3,
 * 			"lat": 25.26
 * 		}
 * 	},
 * 	"weather": [
 * 		{
 * 			"main": "Clear",
 * 			"description": "Sky is clear"
 * 		}
 * 	],
 * 	"main": {
 * 		"temp": 19.19,
 * 		"temp_min": 12.15,
 * 		"temp_max": 40.15
 * 	}
 * }
 * </pre>
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataDTO {

	@JsonProperty("date")
	@JsonSerialize(using = DateSerializer.class)
	private Date date;

	@JsonProperty("city")
	private LocationDTO city;

	@JsonProperty("main")
	private MainDTO main;

	@JsonProperty("weather")
	private List<WeatherDTO> weather;

	public WeatherDataDTO(Location location) {
		if (location != null) {

			date = DateTime.now().toDate();

			city = new LocationDTO();
			city.setName(location.getName());
			city.setCountry(location.getCountry());

			CoordinateDTO coordinateDTO = new CoordinateDTO();
			coordinateDTO.setLatitude(location.getLatitude());
			coordinateDTO.setLongitude(location.getLongitude());
			city.setCoordinate(coordinateDTO);

			if (CollectionUtils.isNotEmpty(location.getTodaysData())) {
				// returns only today's record
				for (LocationWeatherData data : location.getTodaysData()) {

					// TODO - clean use any other api to find timezone by lat
					// long country code to convert to location's local time
					city.setSunriseTime(data.getSunriseTime());
					city.setSunsetTime(data.getSunsetTime());

					main = new MainDTO();
					main.setTemperature(data.getTempurature());
					main.setMiniumumTemperature(data.getMinimumTempurature());
					main.setMaximumTemperature(data.getMaximumTempurature());
					break;
				}

			}

			if (CollectionUtils.isNotEmpty(location.getTodaysConditions())) {
				// returns only today's record
				weather = new ArrayList<WeatherDTO>();
				for (LocationWeatherCondition condition : location.getTodaysConditions()) {
					if (condition.getWeatherCondition() != null) {
						WeatherDTO dto = new WeatherDTO();
						dto.setMain(condition.getWeatherCondition().getMain());
						dto.setDescription(condition.getWeatherCondition().getDescription());
						weather.add(dto);
					}
				}

			}

		}
	}

}
