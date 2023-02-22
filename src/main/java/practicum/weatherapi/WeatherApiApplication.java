package practicum.weatherapi;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practicum.weatherapi.retriever.WeatherDataRetriever;


@SpringBootApplication
public class WeatherApiApplication {

    @Autowired
    private WeatherDataRetriever weatherDataRetriever;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        weatherDataRetriever.retrieveWeatherData();
    }
}
