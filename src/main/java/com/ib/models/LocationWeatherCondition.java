package com.ib.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LOCATION_WEATHER_CONDITION")
public class LocationWeatherCondition extends BaseEntity{

	@Id
	@Column(name = "LOCATION_WEATHER_CONDITION_ID")
	private String primaryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID")
	private Location location;

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_DATE")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "WEATHER_CONDITION_ID", referencedColumnName = "WEATHER_CONDITION_ID")
	private WeatherCondition weatherCondition;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public WeatherCondition getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(WeatherCondition weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	@Override
	public String getPrimaryId() {
		return primaryId;
	}

	@Override
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}

}
