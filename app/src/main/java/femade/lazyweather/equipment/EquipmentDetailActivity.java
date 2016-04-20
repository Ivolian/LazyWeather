package femade.lazyweather.equipment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.view.IconicsImageView;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import femade.lazyweather.R;
import me.relex.circleindicator.CircleIndicator;


public class EquipmentDetailActivity extends AppCompatActivity {


    // ================================== onCreate ==================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_detail);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initViewpager();
        setScrollLister();
        initAnimation();
    }


    // ================================== viewpager ==================================

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.indicator)
    CircleIndicator indicator;

    String[] imageUrls = {
            "http://img3.007swz.com/cpimg/ershouyinshuashebei/RF3dD9CHB_1313573778.jpg",
            "http://sup.user.img8.51sole.com/images3/20120606/1103362_2012661957250.jpg",
            "http://docs.ebdoor.com/Image/ProductImage/0/639/6397386_1.jpg"
    };

    private void initViewpager() {
        List<View> viewList = new ArrayList<>();
        for (String url : imageUrls) {
            ImageView imageView = new ImageView(this);
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .into(imageView);
            viewList.add(imageView);
        }
        EquipmentPhotoAdapter adapter = new EquipmentPhotoAdapter(viewList);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }


    // ================================== scrollView & titleBar ==================================

    @Bind(R.id.scrollView)
    CustomScrollView scrollView;

    @Bind(R.id.titleBar)
    PercentLinearLayout titleBar;

    @Bind(R.id.cancel)
    IconicsImageView cancel;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.operate)
    View operate;

    @Bind(R.id.titleBarLine)
    View titleBarLine;

    private void setScrollLister() {
        // 预计中的 Scroll 和 ViewPager 的滑动冲突，并没有出现。
        scrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY, int direction) {
                // “设备编码” 距离标题栏的距离
                int diff = viewPager.getHeight() - titleBar.getHeight() - scrollY;

                // 标题栏背景渐变
                if (diff > 255) {
                    titleBar.setBackgroundColor(Color.argb(0, 255, 255, 255));
                } else if (diff >= 0) {
                    titleBar.setBackgroundColor(Color.argb(255 - diff, 255, 255, 255));
                } else {
                    titleBar.setBackgroundColor(Color.argb(255, 255, 255, 255));
                }

                // 变色点的偏移，学菜鸟裹裹
                Context context = EquipmentDetailActivity.this;
                if (diff > 30) {
                    cancel.setColor(ContextCompat.getColor(context, R.color.md_white));
                    title.setTextColor(ContextCompat.getColor(context, R.color.md_white));
                    operate.setVisibility(View.INVISIBLE);
                    titleBarLine.setVisibility(View.INVISIBLE);
                } else {
                    cancel.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    title.setTextColor(ContextCompat.getColor(context, R.color.md_black));
                    operate.setVisibility(View.VISIBLE);
                    titleBarLine.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    // ================================== 想法来自 drop menu ==================================

    @Bind(R.id.mask)
    View mask;

    @Bind(R.id.panel)
    View panel;

    boolean isPanelShow = false;
    boolean animating = false;

    private void showPanel() {
        if (animating || isPanelShow) {
            return;
        }
        mask.setVisibility(View.VISIBLE);
        mask.startAnimation(alphaOccurAnimation);
        panel.startAnimation(occurAnimation);
        animating = true;

    }

    private void hidePanel() {
        if (animating || !isPanelShow) {
            return;
        }
        mask.startAnimation(alphaDismissAnimation);
        panel.startAnimation(dismissAnimation);
        animating = true;

    }

    @OnClick(R.id.operate)
    public void operateOnClick() {
        if (animating) {
            return;
        }
        if (isPanelShow) {
            hidePanel();
        } else {
            showPanel();
        }
    }

    @OnClick(R.id.mask)
    public void maskOnClick() {
        hidePanel();
    }

    // 所有动画的 duration 都是 300 ms
    private Animation dismissAnimation;
    private Animation occurAnimation;
    private Animation alphaDismissAnimation;
    private Animation alphaOccurAnimation;

    private void initAnimation() {
        // for panel
        occurAnimation = AnimationUtils.loadAnimation(this, R.anim.top_in);
        dismissAnimation = AnimationUtils.loadAnimation(this, R.anim.top_out);

        // for mask
        alphaDismissAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_to_zero);
        alphaDismissAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                isPanelShow = false;
                animating = false;
                mask.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        alphaOccurAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_to_one);
        alphaOccurAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                isPanelShow = true;
                animating = false;
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
