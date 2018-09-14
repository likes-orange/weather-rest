package com.exam.weather.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exam.weather.rest.constant.WeatherRestConstants;
import com.exam.weather.rest.model.WeatherLog;
import com.exam.weather.rest.repository.WeatherLogRepository;
import com.exam.weather.rest.service.WeatherService;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {

	@Value("${app.openweather.app-id}")
	private String openweatherAppId;
	
	@Autowired
	WeatherLogRepository weatherLogRepository;
	
	@Override
	public ResponseEntity<String> getCurrentWeatherInfoList(List<String> cityIds) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity(
				WeatherRestConstants.OPENWEATHER_API_BASE_URL + 
				"/group?units=metric&appid=" + openweatherAppId +
				"&id=" + StringUtils.join(cityIds, ","), String.class);
		
		return response;
	}

	/**
	 * Incomplete Logic
	 */
	@Override
	public Iterable<WeatherLog> saveWeatherLog() {
		List<WeatherLog> listWeatherLog = new ArrayList<WeatherLog>();
		return weatherLogRepository.save(listWeatherLog);
	}
	
}
