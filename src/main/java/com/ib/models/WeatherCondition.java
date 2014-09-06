package com.ib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WeatherCondition contains weather condition codes and description
 * 
 * @author ishmael
 *
 */
@Entity
@Table(name = "WEATHER_CONDITION")
public class WeatherCondition extends BaseEntity {

	@Id
	@Column(name = "WEATHER_CONDITION_ID")
	private String primaryId;
	
	@Column(name = "MAIN")
	private String main;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CODE")
	private String code;
	
	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
