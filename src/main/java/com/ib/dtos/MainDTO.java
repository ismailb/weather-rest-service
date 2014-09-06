package com.ib.dtos;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MainDTO
{
	@JsonProperty("temp")
	private double temperature;
	
	@JsonProperty("temp_min")
	private double miniumumTemperature;
	
	@JsonProperty("temp_max")
	private double maximumTemperature;

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getMiniumumTemperature() {
		return miniumumTemperature;
	}

	public void setMiniumumTemperature(double miniumumTemperature) {
		this.miniumumTemperature = miniumumTemperature;
	}

	public double getMaximumTemperature() {
		return maximumTemperature;
	}

	public void setMaximumTemperature(double maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}
}
