package com.ib.dtos;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * System dto to capture remote sys info
 *  
 * @author ishmael
 *
 */
@JsonAutoDetect(getterVisibility = Visibility.ANY, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SystemDTO {
	
	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	private String sunrise;

	@JsonProperty("sunset")
	private String sunset;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
}
