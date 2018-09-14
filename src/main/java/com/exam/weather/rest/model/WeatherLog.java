package com.exam.weather.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WEATHER_LOG")
public class WeatherLog extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7528233936717308625L;

	public WeatherLog() {
		super();
	}
	
	@Column(name = "RESPONSE_ID")
	private String responseId;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "ACTUAL_WEATHER")
	private String actualWeather;
	
	@Column(name = "TEMPERATURE")
	private String temperature;
	
	@Column(name = "DTIME_INSERTED")
	private Date dtimeInserted;

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getActualWeather() {
		return actualWeather;
	}

	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Date getDtimeInserted() {
		return dtimeInserted;
	}

	public void setDtimeInserted(Date dtimeInserted) {
		this.dtimeInserted = dtimeInserted;
	}
	
}
