# EasyBanner

### 包含功能

- 分离轮播图和指示器为两个控件，位置可写在布局，最后组合以下即可
- 增加轮播图动画，和指示器动画
- 链式调用，快速配置参数


### 使用方法

1. 配置maven仓库地址

        maven { url "https://jitpack.io" }

2. 导入库（版本号参见[版本号](https://github.com/huanhan/EasyBanner/releases)）

		implementation 'com.github.huanhan:EasyBanner:1.0.2'  

3. 使用EasyViewPager

		<xin.lrvik.easybanner.EasyViewPager
	        android:id="@+id/evp"
	        android:layout_width="match_parent"
	        android:layout_height="200dp"
	        app:delay_time="2000"
	        app:auto_play="true"
	        app:is_loop="true"
	        android:text="Hello World!" />

4. 设置轮播图适配器以及数据

	1. 设置普通图片轮播，用于banner,闪频页

	![](https://i.imgur.com/HufoRCA.png)

		viewPager = findViewById(R.id.evp);
		ArrayList<String> imgUrls = new ArrayList<>();

        ....
		
		//EasyImageAdapter构造为轮播图的ImageView缩放类型，参照ImageView.ScaleType，默认为fitXY
        viewPager.setBannerAnimation(Transformer.Accordion)
                .setAdapter(new EasyImageAdapter<String>(ImageView.ScaleType.CENTER_CROP) {

                    @Override
                    protected void convert(ImageView view, String data) {
						//将数据渲染到ImageView,可以用Glide...
                        ....
                    }
                }).setData(imgUrls);	

	2. 一页Viewpager要有展示多个子项，设置EasyTypeItemAdapter可以将所有数据自动划分成多页

	![](https://i.imgur.com/Fvnwtea.png)

			ArrayList<TypeItem> typeItems = new ArrayList<>();
	        //第二个viewpager
	        for (int i = 0; i < 20; i++) {
	            typeItems.add(new TypeItem("https://fuss10....", "美食" + i));
				....
	        }
	
			//设置1页有10个元素，10个元素按照5列排序
	        viewPager2.setAdapter(new EasyTypeItemAdapter(10, 5) {
	
	                @Override
                    protected void convert(ImageView imageView, TextView textView, TypeItem data) {
                        Glide.with(MainActivity.this)
                                .load(data.getImgUrl())
                                .into(imageView);
                        textView.setText(data.getTitle());
                    }
	
	                }).setData(typeItems);

5. 开始轮播

	只有auto_play和is_loop同时为true时才可以轮播

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

6. 增加点击监听

		viewPager.setOnItemClickListner(new BaseEasyViewPagerAdapter.OnItemClickListner<String>() {
	            @Override
	            public void onItemClickListner(View v, String s) {
	                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
	            }
	        });

7. 增加轮播监听
		
		viewPager.setOnPageChangeListener(new EasyViewPager.OnPageChangeListener() {
	            @Override
	            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	                //Toast.makeText(MainActivity.this, "当前页为"+position, Toast.LENGTH_SHORT).show();
	                Log.d(TAG, "更新文本提示 " + position);
	            }
	        });

8. 设置动画

	具体动画参考Transformer类

		viewPager.setBannerAnimation(Transformer.Accordion);

9. 使用指示器

		<xin.lrvik.easybanner.indicator.DotIndicator
	        android:id="@+id/dot"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content" />

10. 联立轮播图和指示器

		viewPager2 = findViewById(R.id.evp2);
	    dot = findViewById(R.id.dot);
		viewPager2.setIndicator(dot);

11. 自定义适配器

	默认图片适配器为例
	
	1.
	
		public abstract class EasyTypeItemAdapter2 extends BaseTypeItemAdapter<TypeItem> {
	
	    public EasyTypeItemAdapter(int itemNum, int cols) {
			//R.layout.item_type 你的布局
	        super(itemNum, cols, R.layout.item_type);
	    }
	
		}

	2.渲染的时候使用自己的布局id即可

		viewPager2.setAdapter(new EasyTypeItemAdapter2(10, 5) {
	
	                    @Override
	                    protected void convert(BaseViewHolder holder, TypeItem data) {
							
							//获取view的id是你自己写的布局里的id
	                        ImageView iv = holder.getView(R.id.iv);
	                        Glide.with(MainActivity.this)
	                                .load(data.getImgUrl())
	                                .into(iv);
	                        TextView tv = holder.getView(R.id.tv);
	                        tv.setText(data.getTitle());
	                    }
	
	                }).setData(typeItems);
	
### 常用属性

#### EasyViewPager

|属性|对应方法|描述
|---|---|---|
|delay_time|setDelayTime(int delayTime)|设置轮播延迟|
|auto_play|setAutoPlay(boolean autoPlay)|设置轮播自动播放|
|is_loop|setAutoPlay(boolean autoPlay)|ViewPager是否无限循环|

#### EasyDotIndicator

|属性|对应方法|描述
|---|---|---|
|indicator_height|setIndicatorHeight(int indicatorHeight)|设置默认情况的单个指示器高度|
|indicator_width|setIndicatorWidth(int indicatorWidth)|设置默认情况的单个指示器宽度|
|indicator_margin|setIndicatorMargin(int indicatorMargin)|设置单个指示器之间的间隔|
|sel_indicator_height|setSelIndicatorHeight(int selIndicatorHeight)|设置选中情况的单个指示器高度|
|sel_indicator_width|setSelIndicatorWidth(int selIndicatorWidth)|设置选中情况的单个指示器宽度|
|def_color|setDefColor(int defColor)|设置单个指示器默认情况的颜色|
|sel_color|setSelColor(int selColor)|设置单个指示器选中情况的颜色|
|def_is_full|setDefIsFull(boolean defIsFull)|设置单个指示器默认情况是绘制实心还是空心|
|sel_is_full|setSelIsFull(boolean selIsFull)|设置单个指示器选中情况是绘制实心还是空心|
|stroke_width|setStrokeWidth(int strokeWidth)|设置单个指示器绘制空心的时候边框厚度|
|def_radius|setDef_radius(int def_radius)|设置单个指示器默认情况的圆角弧度|
|sel_radius|setSel_radius(int sel_radius)|设置单个指示器选中情况的圆角弧度|
|shape|setDelayTime(int delayTime)|形状（目前只有方形和圆形）|


----------
### 注意事项

#### 调用方法顺序

设置适配器方法必须在设置监听以及设置数据之前调用，一般最后调用设置数据方法

设置适配器方法必须在设置监听以及设置数据之前调用，一般最后调用设置数据方法

设置适配器方法必须在设置监听以及设置数据之前调用，一般最后调用设置数据方法

总而言之：**杂七杂八配置->适配器->监听->设置数据**


### 联系邮箱 <huanhanfu@126.com>

欢迎交流
