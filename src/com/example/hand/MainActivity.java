package com.example.hand;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ImageView img;
	private GestureDetector mGestureDetector;
	private GestureOverlayView mGestureOverlayView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView) findViewById(R.id.img);
//		mGestureDetector = new GestureDetector(new myGestureListener());
//		img.setOnTouchListener(new OnTouchListener() {
//			
//			@Override//可以捕获触摸屏幕发生的Event事件
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				
//				mGestureDetector.onTouchEvent(event);
//				return true;
//			}
//		});
		
		mGestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
//		1、找到刚才的与设定的手势文件
//		2、加载那个手势文件中的所有手势
//		3、匹配 识别
//		从资源中将手势库文件加载进来。
		final GestureLibrary library = GestureLibraries.fromRawResource(MainActivity.this, R.raw.gestures);
		library.load();
		mGestureOverlayView.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			
			@Override
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				// 读出手势库中的内容   识别手势
				ArrayList<Prediction> mygesture = library.recognize(gesture);
				Prediction prediction =mygesture.get(0);
				 if (prediction.score >= 7.0) 
				{
					if (prediction.name.equals("exit")) {
						finish();
					}
					if (prediction.name.endsWith("next")) {
						Toast.makeText(MainActivity.this, "播放下一首", Toast.LENGTH_SHORT).show();

					}
					if (prediction.name.endsWith("pervious")) {
						Toast.makeText(MainActivity.this, "播放上一首", Toast.LENGTH_SHORT).show();

					}
				}
				 else 
				{
					Toast.makeText(MainActivity.this, "没有该手势！", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	class myGestureListener extends SimpleOnGestureListener
	{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if (e1.getX() - e2.getX() > 50) {
				Toast.makeText(MainActivity.this, "从右往左边滑动", Toast.LENGTH_LONG).show();
			}
			else if (e1.getX() - e2.getX() <= 50) {
				
			}{
				Toast.makeText(MainActivity.this, "从左往右边滑动", Toast.LENGTH_LONG).show();
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}
