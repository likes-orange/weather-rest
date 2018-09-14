package com.exam.weather.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.weather.rest.WeatherRestApplication;
import com.exam.weather.rest.constant.WeatherRestConstants;
import com.exam.weather.rest.model.WeatherLog;
import com.exam.weather.rest.response.WeatherInfoResponse;
import com.exam.weather.rest.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get the current weather for LONDON, PRAGUE, SAN FRANCISCO.")
	public List<WeatherInfoResponse> getWeatherInfo() throws Exception {
		
		// Hard-coded cities
		List<String> requestCityIds = new ArrayList<String>();
		requestCityIds.add(WeatherRestConstants.LONDON_ID);
		requestCityIds.add(WeatherRestConstants.PRAGUE_ID);
		requestCityIds.add(WeatherRestConstants.SAN_FRANCISCO_ID);
		
		ResponseEntity<String> response = weatherService
				.getCurrentWeatherInfoList(requestCityIds);
		
		return extractResullt(response);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/log")
	@ApiOperation(value = "Logs the 5 unique response from GET/weather (Incomplete).")
	public String saveWeatherInfoLog() {
		Iterable<WeatherLog> weatherLogs = weatherService.saveWeatherLog();
		return "INCOMPLETE";
	}
	
	private List<WeatherInfoResponse> extractResullt(ResponseEntity<String> response) throws JsonProcessingException, IOException {
		List<WeatherInfoResponse> listWeatherInfoResponse = new ArrayList<WeatherInfoResponse>();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode list = root.path("list");
		
	    for (JsonNode objNode : list) {
	    	WeatherInfoResponse weatherInfoResponse = new WeatherInfoResponse();
	    	weatherInfoResponse.setLocation(
	    			StringUtils.join(objNode.path("name").asText(), ", ", 
	    			objNode.path("sys").path("country").asText()));
	    	weatherInfoResponse.setWeather(objNode.path("weather").get(0).path("description").asText());
	    	weatherInfoResponse.setTemperature(
	    			StringUtils.join(objNode.path("main").path("temp").asText(), "°С"));
	    	listWeatherInfoResponse.add(weatherInfoResponse);
	    }
		
		return listWeatherInfoResponse;
	}

}
