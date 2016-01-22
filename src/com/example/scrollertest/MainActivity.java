package com.example.scrollertest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ViewPager pager;
	private PagerAdapter adapter;
	private List<Bitmap> bitmaps;
	private int index;
	private static final int MAX = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pager = (ViewPager) findViewById(R.id.pager);
		bitmaps = new ArrayList<Bitmap>();
		bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.a3));
		bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.a4));
		bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.a5));
		bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.a6));
		adapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.layout, null);
				ImageView imageView = (ImageView) view.findViewById(R.id.img1);
				imageView.setImageBitmap(bitmaps.get(position % 4));
				container.addView(view);
				return view;
			}

			@Override
			public int getCount() {
				return MAX;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}
		};
		index = 0;
		pager.setAdapter(adapter);
		pager.setCurrentItem(index);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				index = arg0;

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				index++;
				if (index == 100) {
					index = 0;
				}
				runOnUiThread(new Runnable() {
					public void run() {
						pager.setCurrentItem(index);
					}
				});
			}
		}, 2000, 2000);
	}
}
