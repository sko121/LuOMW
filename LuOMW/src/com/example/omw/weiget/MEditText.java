package com.example.omw.weiget;

import com.example.omw.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class MEditText extends EditText{
	
	//����һ֧��
	private Paint paint;
	//EditText lineBound
	private Rect rect;

	public MEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}
	
	public MEditText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public MEditText(Context context) {
		this(context, null);
	}

	public void init(Context context, AttributeSet attrs){
		//�����ʵĶ���
		paint = new Paint();
		//lineBound
		rect = new Rect();
		
		int defaultColor = Color.BLACK;
		// ��ȡ��������Լ��ϣ�ע�⣬obtainStyledAttributes ��һ�������Ǵ��ݹ��������Լ���
		// �ڶ����������� attrs.xml ��<declare-styleable name="NoteEidt"> �����ݣ������ٻ�ȡ
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteEdit);
		if(typedArray != null){
			defaultColor = typedArray.getColor(R.styleable.NoteEdit_noteLineColor, defaultColor);
		}
		paint.setColor(defaultColor);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// ��ȡ�ı��������߶�
		int height = getHeight();
		// ��ȡһ�еĸ߶�
		int lineHeight = getLineHeight();
		// �������Ҫ��������
		int num = height / lineHeight;
		// ��ȡ lineBound
		getLineBounds(0, rect);
		
		int currentY = lineHeight;
		// ���ı���ÿһ��baseLine
		for(int i = 0; i < num; i++){
			currentY = lineHeight * i;
			canvas.drawLine(rect.left, currentY, rect.right, currentY, paint);
		}
		super.onDraw(canvas);
	}
}
