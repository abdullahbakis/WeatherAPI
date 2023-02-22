package practicum.weatherapi.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import practicum.weatherapi.exception.WeatherServiceException;
import practicum.weatherapi.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the WeatherService interface that uses the Visual Crossing Weather API
 * to retrieve weather data.
 */
@Service
public class VisualCrossingWeatherService implements WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.unitGroup}")
    private String unitGroup;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieves the daily weather data for the specified location.
     *
     * @param location the name of the location to retrieve weather data for
     * @return the daily weather data for the specified location
     * @throws WeatherServiceException if there is an error retrieving the weather data
     */
    @Override
    public WeatherData getDailyWeatherData(String location) {
        validateLocation(location);

        String url = apiUrl + "/" + location + "?unitGroup=" + unitGroup + "&key=" + apiKey;
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new WeatherServiceException("Failed to retrieve weather data from the weather API.");
        }

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray days = jsonResponse.getJSONArray("days");
        JSONObject today = days.getJSONObject(0);
        WeatherData weatherData = new WeatherData();
        weatherData.setDatetime(today.getString("datetime"));
        weatherData.setTempmax(today.getDouble("tempmax"));
        weatherData.setTempmin(today.getDouble("tempmin"));
        weatherData.setPrecip(today.getDouble("precip"));
        return weatherData;
    }

    /**
     * Retrieves the weekly weather data for the specified location.
     *
     * @param location the name of the location to retrieve weather data for
     * @return the weekly weather data for the specified location
     * @throws WeatherServiceException if there is an error retrieving the weather data
     */
    @Override
    public List<WeatherData> getWeeklyWeatherData(String location) {
        validateLocation(location);

        String url = apiUrl + "/" + location + "?unitGroup=" + unitGroup + "&key=" + apiKey + "&aggregateHours=24&dayStartTime=0:00:00&dayEndTime=23:59:59&period=7day";
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new WeatherServiceException("Failed to retrieve weather data from the weather API.");
        }

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray days = jsonResponse.getJSONArray("days");
        List<WeatherData> weatherDataList = new ArrayList<>();
        int limit = Math.min(7, days.length());
        for (int i = 0; i < limit; i++) {
            JSONObject day = days.getJSONObject(i);
            WeatherData weatherData = new WeatherData();
            weatherData.setDatetime(day.getString("datetime"));
            weatherData.setTempmax(day.getDouble("tempmax"));
            weatherData.setTempmin(day.getDouble("tempmin"));
            weatherData.setPrecip(day.getDouble("precip"));
            weatherDataList.add(weatherData);
        }
        return weatherDataList;
    }

    /**
     * Retrieves the monthly weather data for the specified location using the Visual Crossing Weather API.
     *
     * @param location the name of the location to retrieve the weather data for
     * @return a list of WeatherData objects containing the weather data for each day of the past month
     * @throws WeatherServiceException if the weather data could not be retrieved from the API
     * @throws IllegalArgumentException if the location parameter is null or empty
     */
    @Override
    public List<WeatherData> getMonthlyWeatherData(String location) {
        validateLocation(location);

        String url = apiUrl + "/" + location + "?unitGroup=" + unitGroup + "&key=" + apiKey + "&aggregateHours=24&dayStartTime=0:00:00&dayEndTime=23:59:59&period=30day";
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new WeatherServiceException("Failed to retrieve weather data from the weather API.");
        }

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray days = jsonResponse.getJSONArray("days");
        List<WeatherData> weatherDataList = new ArrayList<>();
        int limit = Math.min(30, days.length());
        for (int i = 0; i < limit; i++) {
            JSONObject day = days.getJSONObject(i);
            WeatherData weatherData = new WeatherData();
            weatherData.setDatetime(day.getString("datetime"));
            weatherData.setTempmax(day.getDouble("tempmax"));
            weatherData.setTempmin(day.getDouble("tempmin"));
            weatherData.setPrecip(day.getDouble("precip"));
            weatherDataList.add(weatherData);
        }
        return weatherDataList;
    }

    /**
     * Validates the location parameter to ensure that it is not null or empty.
     *
     * @param location the location parameter to validate
     * @throws IllegalArgumentException if the location parameter is null or empty
     */
    private void validateLocation(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty.");
        }
    }

}
