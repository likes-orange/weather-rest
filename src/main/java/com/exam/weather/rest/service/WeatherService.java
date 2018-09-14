package com.exam.weather.rest.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exam.weather.rest.model.WeatherLog;

public interface WeatherService {

	ResponseEntity<String> getCurrentWeatherInfoList(List<String> cityIds);
	
	Iterable<WeatherLog> saveWeatherLog();

}
