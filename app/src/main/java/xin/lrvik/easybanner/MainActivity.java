package xin.lrvik.easybanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import xin.lrvik.easybanner.adapter.recyclerview.BaseViewHolder;
import xin.lrvik.easybanner.adapter.viewpager.EasyImageAdapter;
import xin.lrvik.easybanner.adapter.viewpager.EasyTypeItemAdapter;
import xin.lrvik.easybanner.dto.TypeItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EasyViewPager viewPager;
    private EasyViewPager viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.evp);
        viewPager2 = findViewById(R.id.evp2);

        //第一个viewpager
        ArrayList<String> imgUrls = new ArrayList<>();
        imgUrls.add("https://i0.hdslb.com/bfs/archive/ef72072c178bff478d305e0fad4a1aecca5c30e2.jpg");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/a324228e27f820a3995aca65e48e7d795d496cbd.png");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/47763f9544513d934b0edace50ec2541f3590f7c.jpg");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/c759a6098b816852fae0371791855ac94b5ad646.jpg");
        imgUrls.add("https://i0.hdslb.com/bfs/archive/17e9361930ef585353422e86a8192381da05b5a3.jpg");

        viewPager.setAutoPlay(true)
                .setDelayTime(2000)
                .setLoop(true)
                .setAdapter(new EasyImageAdapter<String>(ImageView.ScaleType.CENTER_CROP) {
                    @Override
                    protected void onItemClick(View view, String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void convert(ImageView view, String data) {
                        Glide.with(MainActivity.this)
                                .load(data)
                                .into(view);
                    }
                }).setData(imgUrls);


        ArrayList<TypeItem> typeItems = new ArrayList<>();
        //第二个viewpager
        for (int i = 0; i < 6; i++) {
            typeItems.add(new TypeItem("https://fuss10.elemecdn.com/7/d8/a867c870b22bc74c87c348b75528djpeg.jpeg?imageMogr/format/webp/thumbnail/!90x90r/gravity/Center/crop/90x90/", "美食" + i));
            typeItems.add(new TypeItem("https://fuss10.elemecdn.com/e/89/185f7259ebda19e16123884a60ef2jpeg.jpeg?imageMogr/format/webp/thumbnail/!90x90r/gravity/Center/crop/90x90/", "晚餐" + i));
            typeItems.add(new TypeItem("https://fuss10.elemecdn.com/c/7e/76a23eb90dada42528bc41499d6f8jpeg.jpeg?imageMogr/format/webp/thumbnail/!90x90r/gravity/Center/crop/90x90/", "商店遍历" + i));
        }

        viewPager2.setAutoPlay(false)
                .setLoop(false)
                .setAdapter(new EasyTypeItemAdapter(10, 5) {

                    @Override
                    protected void onItemClick(View view, TypeItem typeItem) {

                    }

                    @Override
                    protected void convert(BaseViewHolder holder, TypeItem data) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(MainActivity.this)
                                .load(data.getImgUrl())
                                .into(iv);
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText(data.getTitle());
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
