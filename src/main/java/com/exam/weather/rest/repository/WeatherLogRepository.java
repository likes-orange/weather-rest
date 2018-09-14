package com.exam.weather.rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.exam.weather.rest.model.WeatherLog;

public interface WeatherLogRepository extends CrudRepository<WeatherLog, Long>{

}
