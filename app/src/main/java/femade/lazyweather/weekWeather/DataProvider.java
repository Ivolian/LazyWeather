package femade.lazyweather.weekWeather;

import java.util.ArrayList;
import java.util.List;

import femade.lazyweather.R;
import femade.lazyweather.weekWeather.model.WeekWeatherData;


public class DataProvider {

    public static List<WeekWeatherData> getDataList() {

        List<WeekWeatherData> dataList = new ArrayList<>();

        WeekWeatherData weekWeatherData = new WeekWeatherData();
        weekWeatherData.setWeekdayOrWeekend("昨天");
        weekWeatherData.setMonthAndDay("04/18");
        weekWeatherData.setWeather("阴");
        weekWeatherData.setTemperature("15~21" + "\u00B0");
        weekWeatherData.setColor1(R.color.md_grey_600);
        weekWeatherData.setColor2(R.color.color1);
        dataList.add(weekWeatherData);

        weekWeatherData = new WeekWeatherData();
        weekWeatherData.setWeekdayOrWeekend("今天");
        weekWeatherData.setMonthAndDay("04/19");
        weekWeatherData.setWeather("中雨");
        weekWeatherData.setTemperature("15~21" + "\u00B0");
        weekWeatherData.setColor1(R.color.md_white);
        weekWeatherData.setColor2(R.color.color2);
        dataList.add(weekWeatherData);

        weekWeatherData = new WeekWeatherData();
        weekWeatherData.setWeekdayOrWeekend("明日");
        weekWeatherData.setMonthAndDay("04/20");
        weekWeatherData.setWeather("多云");
        weekWeatherData.setTemperature("16~21" + "\u00B0");
        weekWeatherData.setColor1(R.color.md_white);
        weekWeatherData.setColor2(R.color.color3);
        dataList.add(weekWeatherData);

        weekWeatherData = new WeekWeatherData();
        weekWeatherData.setWeekdayOrWeekend("周四");
        weekWeatherData.setMonthAndDay("04/21");
        weekWeatherData.setWeather("中雨");
        weekWeatherData.setTemperature("15~21" + "\u00B0");
        weekWeatherData.setColor1(R.color.md_white);
        weekWeatherData.setColor2(R.color.color2);
        dataList.add(weekWeatherData);

        weekWeatherData = new WeekWeatherData();
        weekWeatherData.setWeekdayOrWeekend("周五");
        weekWeatherData.setMonthAndDay("04/22");
        weekWeatherData.setWeather("阴");
        weekWeatherData.setTemperature("15~24" + "\u00B0");
        weekWeatherData.setColor1(R.color.md_white);
        weekWeatherData.setColor2(R.color.color1);
        dataList.add(weekWeatherData);

        weekWeatherData = new WeekWeatherData();
        weekWeatherData.setWeekdayOrWeekend("周六");
        weekWeatherData.setMonthAndDay("04/23");
        weekWeatherData.setWeather("阵雨");
        weekWeatherData.setTemperature("15~20" + "\u00B0");
        weekWeatherData.setColor1(R.color.md_white);
        weekWeatherData.setColor2(R.color.color4);
        dataList.add(weekWeatherData);

        return dataList;
    }

}
