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

	@OneToMany(fetch=FetchType.LAZY, mappedBy="location", cascade = CascadeType.ALL)
	private Set<LocationWeatherCondition> conditions;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="location", cascade = CascadeType.ALL)
	private Set<LocationWeatherData> data;
	
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

	public Set<LocationWeatherCondition> getConditions() {
		if (CollectionUtils.isEmpty(conditions))
		{
			conditions = new HashSet<LocationWeatherCondition>();
		}
		return conditions;
	}

	public void setConditions(Set<LocationWeatherCondition> conditions) {
		this.conditions = conditions;
	}

	public Set<LocationWeatherData> getData() {
		if (CollectionUtils.isEmpty(data))
		{
			data = new HashSet<LocationWeatherData>();
		}
		return data;
	}

	public void setData(Set<LocationWeatherData> data) {
		this.data = data;
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
