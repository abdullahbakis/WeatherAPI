package practicum.weatherapi.service;

import practicum.weatherapi.model.WeatherData;

import java.util.List;

public interface WeatherService {
    // Interface for weather services
    WeatherData getDailyWeatherData(String location); // Method to retrieve daily weather data for a location
    List<WeatherData> getWeeklyWeatherData(String location); // Method to retrieve weekly weather data for a location
    List<WeatherData> getMonthlyWeatherData(String location); // Method to retrieve monthly weather data for a location
}
