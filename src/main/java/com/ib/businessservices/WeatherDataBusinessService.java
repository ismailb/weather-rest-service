package com.ib.businessservices;

import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ib.dtos.RemoteWeatherData;
import com.ib.dtos.WeatherDataDTO;
import com.ib.exception.ApplicationException;
import com.ib.services.RemoteWeatherDataService;

@Component
public class WeatherDataBusinessService {

	@Autowired
	private RemoteWeatherDataService remoteWeatherDataService;
	
	public WeatherDataDTO getCurrentWeather(String location) {
		if (isBlank(location))
		{
			throw new ApplicationException("Location %s not found", location);
		}
		// TODO: check from db
		
		RemoteWeatherData remoteWeatherData = remoteWeatherDataService.getCurrentWeatherData(location);
		return null;
	}

}
