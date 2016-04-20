package femade.lazyweather.equipment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class ColorButton extends TextView {

    public ColorButton(Context context) {
        super(context);
    }

    //

    public ColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 最终绘图的单位是像素
        int cornerRadius = dp2px(context, 2);

        final int textColor = getCurrentTextColor();
        GradientDrawable unpressed = new GradientDrawable();
        unpressed.setCornerRadius(cornerRadius);
        unpressed.setColor(Color.WHITE);
        unpressed.setStroke(2, textColor);

        GradientDrawable pressed = new GradientDrawable();
        pressed.setCornerRadius(cornerRadius);
        pressed.setColor(getCurrentTextColor());

        StateListDrawable newBackground = new StateListDrawable();
        newBackground.addState(new int[]{-android.R.attr.state_pressed}, unpressed);
        newBackground.addState(new int[]{android.R.attr.state_pressed}, pressed);
        setBackground(newBackground);

        //
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{{-android.R.attr.state_pressed}, {android.R.attr.state_pressed}},
                new int[]{textColor, Color.WHITE}
        );
        setTextColor(colorStateList);
        setClickable(true);
    }

    //

    protected int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
