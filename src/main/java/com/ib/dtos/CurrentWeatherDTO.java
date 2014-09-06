package com.ib.dtos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class CurrentWeatherDTO {

	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("city")
	private LocationDTO location;

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	
	
}
