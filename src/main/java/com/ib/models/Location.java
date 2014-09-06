package com.ib.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "LOCATION")
public class Location extends BaseEntity{

	@Id
	@Column(name = "LOCATION_ID")
	private String primaryId;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@OneToMany(fetch=FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "location")
	@Where(clause = "DATE(INFO_DATE) = CURDATE()")
	private Set<LocationWeatherCondition> todaysConditions;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "location")
	@Where(clause = "DATE(INFO_DATE) = CURDATE()")
	private Set<LocationWeatherData> todaysData;
	
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Set<LocationWeatherCondition> getTodaysConditions() {
		if (CollectionUtils.isEmpty(todaysConditions))
		{
			todaysConditions = new HashSet<LocationWeatherCondition>();
		}
		return todaysConditions;
	}

	public void setTodaysConditions(Set<LocationWeatherCondition> conditions) {
		this.todaysConditions = conditions;
	}

	public Set<LocationWeatherData> getTodaysData() {
		if (CollectionUtils.isEmpty(todaysData))
		{
			todaysData = new HashSet<LocationWeatherData>();
		}
		return todaysData;
	}

	public void setTodaysData(Set<LocationWeatherData> data) {
		this.todaysData = data;
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
