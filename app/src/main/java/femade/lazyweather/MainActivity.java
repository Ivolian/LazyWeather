package femade.lazyweather;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initRvWeekWeather();
        initSlidingPanel();

        String url =  "http://tu.webps.cn/tb/img/4/TB1qYlmHVXXXXaUXpXXXXXXXXXX_%21%210-item_pic.jpg";
        Glide.with(this).load(url).into(ivCloth);


        GradientDrawable gradientDrawable =new GradientDrawable();
        gradientDrawable.setColor(ContextCompat.getColor(this,R.color.md_pink_300));
        gradientDrawable.setAlpha(170);
        gradientDrawable.setCornerRadius(170);
        temp.setBackground(gradientDrawable);
    }

    @Bind(R.id.ivCloth)
    ImageView ivCloth;

    @Bind(R.id.temp)
    TextView temp;

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

//    @Bind(R.id.slidingPanel)
//    MySlidingPaneLayout slidingPaneLayout;

    private void initSlidingPanel(){
    }


}
