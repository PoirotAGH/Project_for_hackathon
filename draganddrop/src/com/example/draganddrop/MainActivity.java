package com.example.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.textApple).setOnLongClickListener(longListen);
		findViewById(R.id.textBanana).setOnLongClickListener(longListen);
		findViewById(R.id.textFruit).setOnLongClickListener(longListen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

OnLongClickListener longListen = new OnLongClickListener() {
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		
		DragShadow dragShadow = new DragShadow(v);
		
		ClipData data = ClipData.newPlainText("", "");
		
		v.startDrag(data, dragShadow, v, 0);
		return false;
	}
};

private class DragShadow extends View.DragShadowBuilder
{

	ColorDrawable greyBox;
	
	
	public DragShadow(View view) {
		
		super(view);
		// TODO Auto-generated constructor stub
		greyBox = new ColorDrawable(Color.LTGRAY);
	}

	@Override
	public void onDrawShadow(Canvas canvas) {
		// TODO Auto-generated method stub
		greyBox.draw(canvas);
	}
	
	@Override
	public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
		// TODO Auto-generated method stub
		View v = getView();
		
		int height = (int)v.getHeight()/2;
		int width = (int)v.getWidth()/2;
		
		greyBox.setBounds(0, 0, width, height);
		
		shadowSize.set(width, height);
		
		shadowTouchPoint.set((int)width/2, (int)height/2);
	
	
	}
	
	OnDragListener dragListener = new OnDragListener() {
		
		@Override
		public boolean onDrag(View v, DragEvent event) {
			// TODO Auto-generated method stub
			
			int dragEvent = event.getAction();
			
			switch(dragEvent)
			{
			case DragEvent.ACTION_DRAG_ENTERED:
				Log.i("Drag event", "Entered");
				break;
				
			case DragEvent.ACTION_DRAG_EXITED:
				Log.i("Drag event", "Exited");
				break;
				
			case DragEvent.ACTION_DROP:
				TextView target = (TextView) v;
				TextView dragged = (TextView) event.getLocalState();
						target.setText(dragged.getText());
				break;
			}
			
			return true;
		}
	};
}
	
}
