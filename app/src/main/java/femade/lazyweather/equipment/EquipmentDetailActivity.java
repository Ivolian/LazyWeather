package femade.lazyweather.equipment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.view.IconicsImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import femade.lazyweather.R;
import me.relex.circleindicator.CircleIndicator;


public class EquipmentDetailActivity extends AppCompatActivity {


    // ================================== onCreate ==================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_detail);
        ButterKnife.bind(this);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        initViews();
    }


    private int[] images = {R.drawable.sb1, R.drawable.sb2, R.drawable.sb3};

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.indicator)
    CircleIndicator indicator;

    @Bind(R.id.scrollView)
    CustomScrollView scrollView;

    boolean toolbarIsShow =false;

    @Bind(R.id.titleBar)
    PercentLinearLayout titleBar;

    @Bind(R.id.back)
    IconicsImageView back;

    @Bind(R.id.title)
    TextView title;

@Bind(R.id.line)
View line;

    private void initViews() {

        titleBar.setBackgroundColor( Color.argb(0,255,255,255));

        scrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY, int direction) {
                double height = viewPager.getHeight() - titleBar.getHeight() - scrollY;
                if (height > 55){
                    back.setColor(getResources().getColor(R.color.md_white));
                    title.setTextColor(getResources().getColor(R.color.md_white));
                    line.setVisibility(View.INVISIBLE);
                }else {
                    back.setColor(getResources().getColor(R.color.colorPrimary));
                    title.setTextColor(getResources().getColor(R.color.md_black));
                    line.setVisibility(View.VISIBLE);
                }

                if (height>0 &&height < 255){

                    titleBar.setBackgroundColor(  Color.argb((int)(255-height),255,255,255));
                }else if (height<0){
                    titleBar.setBackgroundColor( Color.argb((255),255,255,255));
                }else if (height>200){
                    titleBar.setBackgroundColor( Color.argb(0,255,255,255));

                }


            }
        });

        viewPager.post(new Runnable() {
            @Override
            public void run() {
                List<View> viewList = new ArrayList<>();
                for (int image : images) {
//                    View view = new View(EquipmentDetailActivity.this);
//                    view.setBackgroundColor(getResources().getColor(image));

                    final ImageView imageView = new ImageView(EquipmentDetailActivity.this);
//                    Bitmap bitmap = decodeSampledBitmapFromResource(getResources(),image,1080,500);
//            imageView.setImageBitmap(bitmap);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    int height = viewPager.getHeight();
                    int width = viewPager.getWidth();

                    ImageSize targetSize = new ImageSize(width, height); // result Bitmap will be fit to this size
                    imageLoader.loadImage("drawable://" + image, targetSize, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            imageView.setImageBitmap(loadedImage);
                        }
                    });

                    viewList.add(imageView);
                }

                EquipmentPhotoPagerAdapter adapter = new EquipmentPhotoPagerAdapter(viewList);
                viewPager.setAdapter(adapter);
                indicator.setViewPager(viewPager);
                viewPager.setCurrentItem(2);
            }
        });


    }


}
