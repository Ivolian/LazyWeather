package femade.lazyweather.weekWeather;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.android.percent.support.PercentFrameLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import femade.lazyweather.R;
import femade.lazyweather.weekWeather.model.WeekWeatherData;


public class WeekWeatherAdapter extends RecyclerView.Adapter<WeekWeatherAdapter.ViewHolder> {


    // ========================= dataList =========================

    private List<WeekWeatherData> dataList;

    public WeekWeatherAdapter(List<WeekWeatherData> dataList) {
        this.dataList = dataList;
    }


    // ========================= holder =========================

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.bg)
        PercentFrameLayout pflBg;

        public ViewHolder(View v) {
            super(v);
            // yesterday's question, my fault.
            ButterKnife.bind(this, v);
        }

    }


    // ========================= onBindViewHolder =========================

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Context context = viewHolder.pflBg.getContext();
        int bgColor = 0;
        switch (position) {
            case 0:
            case 1:
            case 2:
                bgColor = ContextCompat.getColor(context, R.color.md_light_blue_300);
                break;
            case 3:
                bgColor = ContextCompat.getColor(context, R.color.md_green_200);

                break;
            case 4:
                bgColor = ContextCompat.getColor(context, R.color.md_pink_300);

                break;
            case 5:
                bgColor = ContextCompat.getColor(context, R.color.md_orange_300);
                break;
        }

        // corner
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(bgColor);
        gradientDrawable.setCornerRadius(10);
        viewHolder.pflBg.setBackground(gradientDrawable);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_week_weather, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    private int colorBurn(int RGBValues) {
        //int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 + 0.1));
        green = (int) Math.floor(green * (1 + 0.1));
        blue = (int) Math.floor(blue * (1 + 0.1));
        return Color.rgb(red, green, blue);
    }
}