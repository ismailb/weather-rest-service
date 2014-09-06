package com.ib.dtos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.ib.misc.UnixTimestampDeserializer;

@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SystemDTO {
	
	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	private Date sunrise;

	@JsonProperty("sunset")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	private Date sunset;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getSunrise() {
		return sunrise;
	}

	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}

	public Date getSunset() {
		return sunset;
	}

	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}
}
