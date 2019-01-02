package xin.lrvik.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import xin.lrvik.easybanner.EasyViewPager;
import xin.lrvik.easybanner.Transformer;
import xin.lrvik.easybanner.adapter.recyclerview.BaseViewHolder;
import xin.lrvik.easybanner.adapter.viewpager.BaseEasyViewPagerAdapter;
import xin.lrvik.easybanner.adapter.viewpager.EasyImageAdapter;
import xin.lrvik.easybanner.adapter.viewpager.EasyTypeItemAdapter;
import xin.lrvik.easybanner.dto.TypeItem;
import xin.lrvik.easybanner.indicator.EasyDotIndicator;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EasyViewPager viewPager;
    private EasyViewPager viewPager2;
    private EasyDotIndicator dot;
    private EasyDotIndicator dot2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.evp);
        viewPager2 = findViewById(R.id.evp2);

        dot = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);

        ArrayList<String> imgUrls = new ArrayList<>();
        imgUrls.add("https://i0.hdslb.com/bfs/archive/ef72072c178bff478d305e0fad4a1aecca5c30e2.jpg");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/a324228e27f820a3995aca65e48e7d795d496cbd.png");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/47763f9544513d934b0edace50ec2541f3590f7c.jpg");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/c759a6098b816852fae0371791855ac94b5ad646.jpg");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/17e9361930ef585353422e86a8192381da05b5a3.jpg");

        viewPager.setBannerAnimation(Transformer.Accordion)
                .setIndicator(dot)
                .setAdapter(new EasyImageAdapter<String>(ImageView.ScaleType.CENTER_CROP) {

                    @Override
                    protected void convert(ImageView view, String data) {
                        Glide.with(MainActivity.this)
                                .load(data)
                                .into(view);
                    }
                }).setOnItemClickListner(new BaseEasyViewPagerAdapter.OnItemClickListner<String>() {
            @Override
            public void onItemClickListner(View v, String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }).setOnPageChangeListener(new EasyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Toast.makeText(MainActivity.this, "当前页为"+position, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "更新文本提示 " + position);
            }
        }).setData(imgUrls);


        ArrayList<TypeItem> typeItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            typeItems.add(new TypeItem("https://fuss10.elemecdn.com/7/d8/a867c870b22bc74c87c348b75528djpeg.jpeg?imageMogr/format/webp/thumbnail/!90x90r/gravity/Center/crop/90x90/", "美食" + i));
            typeItems.add(new TypeItem("https://fuss10.elemecdn.com/e/89/185f7259ebda19e16123884a60ef2jpeg.jpeg?imageMogr/format/webp/thumbnail/!90x90r/gravity/Center/crop/90x90/", "晚餐" + i));
            typeItems.add(new TypeItem("https://fuss10.elemecdn.com/c/7e/76a23eb90dada42528bc41499d6f8jpeg.jpeg?imageMogr/format/webp/thumbnail/!90x90r/gravity/Center/crop/90x90/", "商店遍历" + i));
        }

        viewPager2.setIndicator(dot2)
                .setAdapter(new EasyTypeItemAdapter(10, 5) {

                    @Override
                    protected void convert(ImageView imageView, TextView textView, TypeItem data) {
                        Glide.with(MainActivity.this)
                                .load(data.getImgUrl())
                                .into(imageView);
                        textView.setText(data.getTitle());
                    }

                }).setOnItemClickListner(new BaseEasyViewPagerAdapter.OnItemClickListner<TypeItem>() {
            @Override
            public void onItemClickListner(View v, TypeItem typeItem) {
                Toast.makeText(MainActivity.this, typeItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }).setData(typeItems);

    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.stopAutoPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.startAutoPlay();
    }
}
