package com.example.omw.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author robin �Զ���ͼ���View
 */
public class ChartView extends View {
	private String[] yAxis = { "0", "100", "200", "300", "400", "500", "600",
			"700" };
	private String[] xAxis = { "����", "�Ϻ�", "����", "�ɶ�", "����", "����", "����", "֣��" };
	private int[] cityValues = new int[] { 1, 2, 3, 4, 5, 6, 1, 2};

	/**
	 * Y����ÿһ�еļ��
	 */
	private static final int Y_LINE_SPACING = 10;
	/**
	 * x����ļ��
	 */
	private static final int X_LINE_SPACING = 15;

	// ��x��y ����ı�
	private Paint xyPaint;

	private Paint hLinePaint;

	private Paint borderPaint;

	private int leftPadding = 10;

	private int topPadding = 20;

	private float textSize = 35;

	private float xStrWidth = 0;

	private float yStrWidth = 0;
	
	private RectF rect;
	
	private Paint rectPaint;

	public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ChartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChartView(Context context) {
		this(context, null);
	}

	private void init() {
		xyPaint = new Paint();   //���廭��
		xyPaint.setColor(Color.BLACK);
		xyPaint.setTextSize(textSize);

		hLinePaint = new Paint();	//X��
		hLinePaint.setColor(Color.BLUE);
		hLinePaint.setAntiAlias(true);

		borderPaint = new Paint();	//Y��
		borderPaint.setColor(Color.YELLOW);
		borderPaint.setAntiAlias(true);
		
		rectPaint =new Paint();	//���λ���
		rectPaint.setColor(Color.CYAN);
		rect=new RectF();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ������EXACTLY��50dp��match_parent
		int wSize = MeasureSpec.getSize(widthMeasureSpec);
		int hSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int xLength = xAxis.length;
		int wl = 0;
		if (MeasureSpec.AT_MOST == widthMode) {// wrap_content
			Log.d("CharView", "AT_MOST");
			String xStr = xAxis[xAxis.length - 1];
			String yStr = yAxis[yAxis.length - 1];
			xStrWidth = getStringWidth(xStr, xyPaint); //�ı�
			yStrWidth = getStringWidth(yStr, xyPaint);  //�ı�
			wl = (int) ((xStrWidth + X_LINE_SPACING) * xLength + leftPadding
					+ yStrWidth + 15);
			wSize = wl+100;
		} else if (MeasureSpec.EXACTLY == widthMode) {
			Log.d("CharView", "EXACTLY");
		} else {
			Log.d("CharView", "UNSPECIFIED");
		}

		int hl = 0;
		if (MeasureSpec.AT_MOST == heightMode) {
			// ��ȡ����ĸ߶�
			float wordSpacing = xyPaint.getFontSpacing();

			hl = (int) ((wordSpacing + Y_LINE_SPACING) * (yAxis.length + 1) + topPadding);
			hSize = hl;
		}
		setMeasuredDimension(wSize, hSize);// ����View�Ĵ�С
	}

	/**
	 * @param xStr
	 * @param xyPaint
	 * @return �����ı��Ŀ��
	 */
	private int getStringWidth(String xStr, Paint xyPaint) {
		int ret = 0;
		if (xStr != null && xyPaint != null) {
			int len = xStr.length();
			float[] widths = new float[len];
			xyPaint.getTextWidths(xStr, widths);
			for (int i = 0; i < widths.length; i++) {
				ret += widths[i];
			}
		}
		return ret;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		// ��view
		drawContent(canvas);
		canvas.restore();
	}

	/**
	 * @param canvas
	 *            ��������
	 */
	private void drawContent(Canvas canvas) {
		// ��һ�������� y �� ����������
		float sx = leftPadding;
		int yLength = yAxis.length;
		float fontSpacing = xyPaint.getFontSpacing();
		float yAxisHeight = yLength * (fontSpacing + Y_LINE_SPACING);
		float sy = 0;
		int width = getWidth();
		for (int i = 0; i < yLength; i++) {
			String text = yAxis[i];
			sy = (int) (topPadding + yAxisHeight - (fontSpacing + Y_LINE_SPACING)
					* i);
			// ���ı�
			canvas.drawText(text, sx, sy, xyPaint);

			// ����
			canvas.drawLine(sx + yStrWidth + 5, sy, width - (leftPadding * 2),
					sy, hLinePaint);
		}

		// ��y���ֱ��
		canvas.drawLine(sx + yStrWidth + 5, topPadding, sx + yStrWidth + 5,
				yAxisHeight + topPadding, borderPaint);

		// ��x�������
		float nextX = 0;
		for (int i = 0; i < xAxis.length; i++) {
			String text = xAxis[i];
			nextX = (sx + xStrWidth + 5 + (xStrWidth + 20) * i);
			canvas.drawText(text, nextX + 10, yAxisHeight + topPadding
					+ fontSpacing, xyPaint);
		}
		
		// ���ƾ���
		int cvCount = cityValues.length;
		for (int i = 0; i < cvCount; i++) {
					int cv = cityValues[i];
					if (cv > yAxis.length) {
						cv = yAxis.length;
					}
					nextX = sx + xStrWidth + 5 + (xStrWidth + 20) * i;
					rect.left = nextX + 10;
					rect.top = topPadding + (yAxis.length - cv)
							* (fontSpacing + Y_LINE_SPACING);
					rect.right = rect.left + xStrWidth;
					rect.bottom = yAxisHeight + topPadding;
					canvas.drawRect(rect, rectPaint);
				}
	}

}
