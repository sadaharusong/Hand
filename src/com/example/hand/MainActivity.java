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
//			@Override//���Բ�������Ļ������Event�¼�
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				
//				mGestureDetector.onTouchEvent(event);
//				return true;
//			}
//		});
		
		mGestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
//		1���ҵ��ղŵ����趨�������ļ�
//		2�������Ǹ������ļ��е���������
//		3��ƥ�� ʶ��
//		����Դ�н����ƿ��ļ����ؽ�����
		final GestureLibrary library = GestureLibraries.fromRawResource(MainActivity.this, R.raw.gestures);
		library.load();
		mGestureOverlayView.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			
			@Override
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				// �������ƿ��е�����   ʶ������
				ArrayList<Prediction> mygesture = library.recognize(gesture);
				Prediction prediction =mygesture.get(0);
				 if (prediction.score >= 7.0) 
				{
					if (prediction.name.equals("exit")) {
						finish();
					}
					if (prediction.name.endsWith("next")) {
						Toast.makeText(MainActivity.this, "������һ��", Toast.LENGTH_SHORT).show();

					}
					if (prediction.name.endsWith("pervious")) {
						Toast.makeText(MainActivity.this, "������һ��", Toast.LENGTH_SHORT).show();

					}
				}
				 else 
				{
					Toast.makeText(MainActivity.this, "û�и����ƣ�", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(MainActivity.this, "��������߻���", Toast.LENGTH_LONG).show();
			}
			else if (e1.getX() - e2.getX() <= 50) {
				
			}{
				Toast.makeText(MainActivity.this, "�������ұ߻���", Toast.LENGTH_LONG).show();
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}
