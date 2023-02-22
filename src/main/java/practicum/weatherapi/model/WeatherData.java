package practicum.weatherapi.model;

import org.jetbrains.annotations.NotNull;

/**
 * A model class that represents weather data for a specific location at a specific time.
 */
public class WeatherData {
    @NotNull
    private String datetime;

    @NotNull
    private Double tempmax;

    @NotNull
    private Double tempmin;

    @NotNull
    private Double precip;

    // Getters and setters

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }


    @Override
    public String toString() {
        return String.format("%s - MaxTemp: %.1f°C, MinTemp: %.1f°C, Precipitation: %.1f mm",
                datetime, tempmax, tempmin, precip );
    }
}
