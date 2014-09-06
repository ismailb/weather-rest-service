package com.ib.dtos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.ib.misc.UnixTimestampDeserializer;

@JsonAutoDetect(getterVisibility = Visibility.ANY, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SystemDTO {
	
	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	//@JsonDeserialize(using = UnixTimestampDeserializer.class)
	private String sunrise;

	@JsonProperty("sunset")
	//@JsonDeserialize(using = UnixTimestampDeserializer.class)
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
