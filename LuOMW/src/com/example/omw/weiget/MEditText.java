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
	
	//创建一支笔
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
		//产生笔的对象
		paint = new Paint();
		//lineBound
		rect = new Rect();
		
		int defaultColor = Color.BLACK;
		// 获取定义的属性集合，注意，obtainStyledAttributes 第一个参数是传递过来的属性集合
		// 第二个参数是在 attrs.xml 的<declare-styleable name="NoteEidt"> 的内容，这样再获取
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteEdit);
		if(typedArray != null){
			defaultColor = typedArray.getColor(R.styleable.NoteEdit_noteLineColor, defaultColor);
		}
		paint.setColor(defaultColor);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// 获取文本框的总体高度
		int height = getHeight();
		// 获取一行的高度
		int lineHeight = getLineHeight();
		// 计算出需要画多少行
		int num = height / lineHeight;
		// 获取 lineBound
		getLineBounds(0, rect);
		
		int currentY = lineHeight;
		// 画文本框每一行baseLine
		for(int i = 0; i < num; i++){
			currentY = lineHeight * i;
			canvas.drawLine(rect.left, currentY, rect.right, currentY, paint);
		}
		super.onDraw(canvas);
	}
}
