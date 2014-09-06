package com.ib.businessservices;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ib.dao.WeatherDataDao;
import com.ib.dtos.RemoteWeatherData;
import com.ib.dtos.WeatherDTO;
import com.ib.dtos.WeatherDataDTO;
import com.ib.exception.ApplicationException;
import com.ib.models.Location;
import com.ib.models.LocationWeatherCondition;
import com.ib.models.LocationWeatherData;
import com.ib.models.WeatherCondition;
import com.ib.services.RemoteWeatherDataService;

/**
 * Weather business service component handles all the business methods for processing, persisiting and retriving weather data
 * 
 * @author ishmael
 *
 */
@Component
public class WeatherDataBusinessService {

	@Autowired
	private RemoteWeatherDataService remoteWeatherDataService;

	@Autowired
	private WeatherDataDao weatherDataDao;


	/**
	 * Gets todays weather information for a location
	 * 
	 * @param locationName
	 * @return today's weatherDto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public WeatherDataDTO getCurrentWeather(String locationName) {

		if (isBlank(locationName)) {
			throw new ApplicationException("Location %s not found", locationName);
		}

		Location location = weatherDataDao.findLocationByName(locationName);

		if (location == null || CollectionUtils.isEmpty(location.getTodaysConditions()) || CollectionUtils.isEmpty(location.getTodaysData())) {
			
			RemoteWeatherData remoteWeatherData = remoteWeatherDataService.getCurrentWeatherData(locationName);

			if (remoteWeatherData == null) {
				throw new ApplicationException("Error: city not found");
			}

			location = processWeatherData(remoteWeatherData);
		}

		return new WeatherDataDTO(location);
	}

	/**
	 * Process the weather data retrieved from the remote weather service into the database
	 * 
	 * @param remoteWeatherData
	 * @return location
	 */
	private Location processWeatherData(RemoteWeatherData remoteWeatherData) {

		Location location = null;

		if (remoteWeatherData != null) {
			String name = remoteWeatherData.getName();

			location = weatherDataDao.findLocationByName(name);

			if (location == null) {

				// TODO: change to dozer bean mapper
				// location = mapper.map(remoteWeatherData, Location.class);

				location = new Location();
				location.setName(name);
				location.setCountry(remoteWeatherData.getSystem().getCountry());
				location.setLatitude(remoteWeatherData.getCoordinates().getLatitude());
				location.setLongitude(remoteWeatherData.getCoordinates().getLongitude());
			}
			if (isNotEmpty(remoteWeatherData.getWeather())) {
				for (WeatherDTO weatherDto : remoteWeatherData.getWeather()) {
					WeatherCondition wc = weatherDataDao.findWeatherConditionByCode(weatherDto.getId());
					if (wc == null) {
						wc = new WeatherCondition();
						wc.setCode(weatherDto.getId());
						wc.setMain(weatherDto.getMain());
						wc.setDescription(weatherDto.getDescription());
					}

					LocationWeatherCondition lwc = new LocationWeatherCondition();
					lwc.setLocation(location);
					lwc.setWeatherCondition(wc);
					lwc.setDate(remoteWeatherData.getDate());

					location.getTodaysConditions().add(lwc);
				}
			}

			if (remoteWeatherData.getMain() != null && remoteWeatherData.getSystem() != null) {
				LocationWeatherData lwd = new LocationWeatherData();
				lwd.setLocation(location);
				lwd.setDate(remoteWeatherData.getDate());
				lwd.setTempurature(remoteWeatherData.getMain().getTemperature());
				lwd.setMaximumTempurature(remoteWeatherData.getMain().getMaximumTemperature());
				lwd.setMinimumTempurature(remoteWeatherData.getMain().getMiniumumTemperature());

				// TODO : fix json - custom jackson deserializer issue for
				// nested object
				DateTime sunriseTime = new DateTime(Long.valueOf(remoteWeatherData.getSystem().getSunrise() + "000"));
				lwd.setSunriseTime(sunriseTime.toDate());
				DateTime sunsetTime = new DateTime(Long.valueOf(remoteWeatherData.getSystem().getSunset() + "000"));
				lwd.setSunsetTime(sunsetTime.toDate());

				location.getTodaysData().add(lwd);
			}
			location = weatherDataDao.save(location);
		}
		return location;
	}

}
