package femade.lazyweather.weekWeather;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.android.percent.support.PercentLinearLayout;

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

        @Bind(R.id.tvWeekdayOrWeekend)
        TextView tvWeekDayOrWeekend;

        @Bind(R.id.tvMonthAndDay)
        TextView tvMonthAndDay;

        @Bind(R.id.square)
        PercentLinearLayout pflSquare;

        @Bind(R.id.tvWeather)
        TextView tvWeather;

        @Bind(R.id.tvTemperature)
        TextView tvTemperature;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }


    // ========================= onBindViewHolder =========================

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        WeekWeatherData weekWeatherData = dataList.get(position);
        viewHolder.tvMonthAndDay.setText(weekWeatherData.getMonthAndDay());
        viewHolder.tvWeekDayOrWeekend.setText(weekWeatherData.getWeekdayOrWeekend());
        Context context = viewHolder.pflSquare.getContext();
        int color1 = ContextCompat.getColor(context, weekWeatherData.getColor1());
        viewHolder.tvMonthAndDay.setTextColor(color1);
        viewHolder.tvWeekDayOrWeekend.setTextColor(color1);

        //
        viewHolder.tvWeather.setText(weekWeatherData.getWeather());
        viewHolder.tvTemperature.setText(weekWeatherData.getTemperature());
        int color2 = ContextCompat.getColor(context, weekWeatherData.getColor2());
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color2);
        gradientDrawable.setCornerRadius(10);
        viewHolder.pflSquare.setBackground(gradientDrawable);

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


}