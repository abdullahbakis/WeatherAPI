package practicum.weatherapi.exception;

/**
 * An exception class to handle exceptions that occur while retrieving weather data from the weather API.
 */
public class WeatherServiceException extends RuntimeException {
    public WeatherServiceException(String message) {
        super(message);
    }

}
