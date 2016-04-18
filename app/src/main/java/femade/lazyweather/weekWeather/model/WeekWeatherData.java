package femade.lazyweather.weekWeather.model;

import java.io.Serializable;

public class WeekWeatherData implements Serializable {

    private String weekdayAndWeekend;

    private String monthAndDay;

    private String weather;

    private String temperature;

    //

    public String getWeekdayAndWeekend() {
        return weekdayAndWeekend;
    }

    public void setWeekdayAndWeekend(String weekdayAndWeekend) {
        this.weekdayAndWeekend = weekdayAndWeekend;
    }

    public String getMonthAndDay() {
        return monthAndDay;
    }

    public void setMonthAndDay(String monthAndDay) {
        this.monthAndDay = monthAndDay;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}
