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
		// ���û�������ɫ
		canvas.drawColor(Color.GRAY);
		
		/* ����ȡ�����Ч�� */
		mPaint.setAntiAlias(true);
		
		// ���ü�������
		canvas.clipRect(50, 50, 800, 500);
		
		// �ʵ���ɫ
		mPaint.setColor(Color.WHITE);
		
		// ������
		canvas.drawRect(60, 60, 800, 500, mPaint);
	}
}
