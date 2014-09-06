package com.ib.services;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ib.dtos.RemoteWeatherData;

@Service
public class RemoteWeatherDataService {

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(RemoteWeatherDataService.class);

	@Value("${remote.weather.data.url}")
	private String remoteWeatherDataUrl;

	public RemoteWeatherData getCurrentWeatherData(String location) {
		
		RemoteWeatherData remoteWeatherData = null;
		
		try {
			remoteWeatherData = restTemplate.getForObject(remoteWeatherDataUrl, RemoteWeatherData.class, location);
		} catch (Exception e) {
			logger.error("Exception occured when getting weather data from remote", e);
		}

		return (remoteWeatherData != null && Status.OK.getStatusCode() == remoteWeatherData.getCode()) ? remoteWeatherData : null;

	}
}
