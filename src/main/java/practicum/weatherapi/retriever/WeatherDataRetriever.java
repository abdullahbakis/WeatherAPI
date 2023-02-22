package practicum.weatherapi.retriever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practicum.weatherapi.model.WeatherData;
import practicum.weatherapi.service.WeatherService;

import java.util.List;
import java.util.Scanner;

/**
 * Retrieves weather data using the WeatherService.
 */
@Component
public class WeatherDataRetriever {

    @Autowired
    private WeatherService weatherService;

    /**
     * Retrieves weather data for a given city from the weather service and displays it.
     */
    public void retrieveWeatherData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the city name: ");
        String cityName = scanner.nextLine();

        System.out.println("Daily weather data for " + cityName + ":");
        WeatherData dailyData = weatherService.getDailyWeatherData(cityName);
        System.out.println(dailyData);

        System.out.println("Weekly weather data for " + cityName + ":");
        List<WeatherData> weeklyData = weatherService.getWeeklyWeatherData(cityName);
        for (WeatherData data : weeklyData) {
            System.out.println(data);
        }

        System.out.println("Monthly weather data for " + cityName + ":");
        List<WeatherData> monthlyData = weatherService.getMonthlyWeatherData(cityName);
        for (WeatherData data : monthlyData) {
            System.out.println(data);
        }
    }
}