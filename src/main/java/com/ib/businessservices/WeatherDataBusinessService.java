package com.ib.businessservices;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.dozer.DozerBeanMapper;
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

@Component
public class WeatherDataBusinessService {

	@Autowired
	private RemoteWeatherDataService remoteWeatherDataService;
	
	@Autowired
	private WeatherDataDao weatherDataDao;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public WeatherDataDTO getCurrentWeather(String location) {
		
		if (isBlank(location))
		{
			throw new ApplicationException("Location %s not found", location);
		}
		
		// TODO: check froma db
		
		RemoteWeatherData remoteWeatherData = remoteWeatherDataService.getCurrentWeatherData(location);
		
		if (remoteWeatherData == null)
		{
			throw new ApplicationException("Location not found");
		}
		
		processWeatherData(remoteWeatherData);
		
		return null;
	}

	private void processWeatherData(RemoteWeatherData remoteWeatherData) {

		if (remoteWeatherData != null)
		{
			String name = remoteWeatherData.getName();
			
			Location location = weatherDataDao.findLocationByName(name);
			
			if (location == null)
			{
//				location = mapper.map(remoteWeatherData, Location.class);
//				System.out.println(location);
				location = new Location();
				location.setName(name);
				location.setCountry(remoteWeatherData.getSystem().getCountry());
				location.setLatitude(remoteWeatherData.getCoordinates().getLatitude());
				location.setLongitude(remoteWeatherData.getCoordinates().getLongitude());
			}
			if (isNotEmpty(remoteWeatherData.getWeather()))
			{
				for(WeatherDTO weatherDto : remoteWeatherData.getWeather())
				{
					WeatherCondition wc = weatherDataDao.find(WeatherCondition.class, weatherDto.getId());
					if (wc == null)
					{
						wc = new WeatherCondition();
						wc.setPrimaryId(weatherDto.getId());
						wc.setMain(weatherDto.getMain());
						wc.setDescription(weatherDto.getDescription());
						//wc = weatherDataDao.save(wc);
					} 
					
					LocationWeatherCondition lwc = new LocationWeatherCondition();
					lwc.setLocation(location);
					lwc.setWeatherCondition(wc);
					lwc.setDate(remoteWeatherData.getDate());
					
					location.getConditions().add(lwc);
				}
			}
			
			if (remoteWeatherData.getMain() != null && remoteWeatherData.getSystem() != null)
			{
				LocationWeatherData lwd = new LocationWeatherData();
				lwd.setLocation(location);
				lwd.setDate(remoteWeatherData.getDate());
				lwd.setTempurature(remoteWeatherData.getMain().getTemperature());
				lwd.setMaximumTempurature(remoteWeatherData.getMain().getMaximumTemperature());
				lwd.setMinimumTempurature(remoteWeatherData.getMain().getMiniumumTemperature());
				lwd.setSunriseTime(remoteWeatherData.getSystem().getSunrise());
				lwd.setSunsetTime(remoteWeatherData.getSystem().getSunset());
				
				location.getData().add(lwd);
			}
			weatherDataDao.save(location);
		}
		
	}

}
