package femade.lazyweather.weekWeather.model;

import java.io.Serializable;

public class WeekWeatherData implements Serializable {

    private String weekdayOrWeekend;

    private String monthAndDay;

    private String weather;

    private String temperature;

    private int color1;

    private int color2;

    //

    public String getWeekdayOrWeekend() {
        return weekdayOrWeekend;
    }

    public void setWeekdayOrWeekend(String weekdayOrWeekend) {
        this.weekdayOrWeekend = weekdayOrWeekend;
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

    //

    public int getColor1() {
        return color1;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public int getColor2() {
        return color2;
    }

    public void setColor2(int color2) {
        this.color2 = color2;
    }

}
