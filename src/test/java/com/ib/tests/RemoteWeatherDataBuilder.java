package com.ib.tests;

import org.joda.time.DateTime;

import com.ib.dtos.RemoteWeatherData;

public class RemoteWeatherDataBuilder {

	private RemoteWeatherData remoteWeatherData;
	
	public static RemoteWeatherDataBuilder aBasicRemoteWeatherData()
	{
		RemoteWeatherDataBuilder builder = new RemoteWeatherDataBuilder();
		
		RemoteWeatherData remoteWeatherData = new RemoteWeatherData();
		remoteWeatherData.setCode(200);
		remoteWeatherData.setDate(DateTime.now().toDate());
		remoteWeatherData.setName("name");
		
		builder.remoteWeatherData = remoteWeatherData;
		return builder;
	}
	
	public RemoteWeatherData build()
	{
		return remoteWeatherData;
	}
}
