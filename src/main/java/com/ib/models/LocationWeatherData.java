package com.ib.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LocationWeatherData contains the weather data for a {@link Location}
 * 
 * @author ishmael
 *
 */
@Entity
@Table(name = "LOCATION_WEATHER_DATA")
public class LocationWeatherData extends BaseEntity {

	@Id
	@Column(name = "LOCATION_WEATHER_DATA_ID")	
	private String primaryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID")
	private Location location;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INFO_DATE")
	private Date date;

	@Column(name = "TEMP")
	private double tempurature;

	@Column(name = "MIN_TEMP")
	private double minimumTempurature;

	@Column(name = "MAX_TEMP")
	private double maximumTempurature;

	@Column(name = "SUNRISE_TIME")
	private Date sunriseTime;

	@Column(name = "SUNSET_TIME")
	private Date sunsetTime;

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

	public double getTempurature() {
		return tempurature;
	}

	public void setTempurature(double tempurature) {
		this.tempurature = tempurature;
	}

	public double getMinimumTempurature() {
		return minimumTempurature;
	}

	public void setMinimumTempurature(double minimumTempurature) {
		this.minimumTempurature = minimumTempurature;
	}

	public double getMaximumTempurature() {
		return maximumTempurature;
	}

	public void setMaximumTempurature(double maximumTempurature) {
		this.maximumTempurature = maximumTempurature;
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

	public String getPrimaryId() {
		return primaryId;
	}

	@Override
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	

}
