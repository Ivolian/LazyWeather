package femade.lazyweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import femade.lazyweather.weekWeather.DataProvider;
import femade.lazyweather.weekWeather.WeekWeatherAdapter;
import femade.lazyweather.weekWeather.model.WeekWeatherData;

public class MainActivity extends AppCompatActivity {


    // ========================= onCreate =========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initRvWeekWeather();
    }


    // ========================= 一周天气 =========================

    @Bind(R.id.rvWeekWeather)
    RecyclerView recyclerView;

    private void initRvWeekWeather() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<WeekWeatherData> dataList = DataProvider.getDataList();
        WeekWeatherAdapter adapter = new WeekWeatherAdapter(dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.hasFixedSize();
    }


}