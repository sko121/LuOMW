package com.example.omw.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RectView extends View{
	private Paint mPaint;
	
	public RectView(Context context){
		super(context);
		mPaint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 设置画布的颜色
		canvas.drawColor(Color.GRAY);
		
		/* 设置取消锯齿效果 */
		mPaint.setAntiAlias(true);
		
		// 设置剪切区域
		canvas.clipRect(50, 50, 800, 500);
		
		// 笔的颜色
		mPaint.setColor(Color.WHITE);
		
		// 画矩形
		canvas.drawRect(60, 60, 800, 500, mPaint);
	}
}
