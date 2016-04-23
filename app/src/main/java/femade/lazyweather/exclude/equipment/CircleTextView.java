package femade.lazyweather.exclude.equipment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/22.
 */
public class CircleTextView extends TextView {

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100);
        gradientDrawable.setColor(((ColorDrawable)getBackground()).getColor());
        setBackground(gradientDrawable);
    }

}
