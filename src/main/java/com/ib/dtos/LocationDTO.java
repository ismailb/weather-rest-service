package com.ib.dtos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.ib.misc.TimeSerializer;

@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {

	@JsonProperty("name")
	private String name;

	@JsonProperty("country")
	private String country;

	@JsonProperty("coord")
	private CoordinateDTO coordinate;

	@JsonProperty("sunrise")
	@JsonSerialize(using = TimeSerializer.class)
	private Date sunriseTime;

	@JsonProperty("sunset")
	@JsonSerialize(using = TimeSerializer.class)
	private Date sunsetTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CoordinateDTO getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(CoordinateDTO coordinate) {
		this.coordinate = coordinate;
	}

	public Date getSunriseTime() {
		return sunriseTime;
	}

	public void setSunriseTime(Date sunriseTime) {
		this.sunriseTime = sunriseTime;
	}

	public Date getSunsetTime() {
		return sunsetTime;
	}

	public void setSunsetTime(Date sunsetTime) {
		this.sunsetTime = sunsetTime;
	}
	
	
}
