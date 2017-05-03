package com.fourmob.datetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.fourmob.datetimepicker.R;
import com.fourmob.datetimepicker.Utils;

public class TextViewWithCircularIndicator extends TextView {
    
	private final int mCircleColor;
	private final int whiteColor;
    private Paint mCirclePaint = new Paint();
    private Paint mWhitePaint = new Paint();
	private boolean mDrawCircle;
	private final String mItemIsSelectedText;
	private final int mRadius;
	private Context mContext;

	public TextViewWithCircularIndicator(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
        mContext = context;
		Resources res = context.getResources();
//		mCircleColor = res.getColor(R.color.blue);
		mCircleColor = res.getColor(R.color.red_normal);
		whiteColor = res.getColor(R.color.white);
		mRadius = res.getDimensionPixelOffset(R.dimen.month_select_circle_radius);
		mItemIsSelectedText = context.getResources().getString(R.string.item_is_selected);
        
		init();
	}

	private void init() {
		mWhitePaint.setColor(whiteColor);
		mWhitePaint.setTextSize(Utils.dip2px(mContext,22));
		mWhitePaint.setTextAlign(Paint.Align.CENTER);//设置以基线中间点为x，y坐标位置

		mCirclePaint.setFakeBoldText(true);
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setColor(mCircleColor);
		mCirclePaint.setTextAlign(Paint.Align.CENTER);
		mCirclePaint.setStyle(Paint.Style.FILL);
		mCirclePaint.setAlpha(255);
	}

	public void drawIndicator(boolean drawIndicator) {
		mDrawCircle = drawIndicator;
	}

	public CharSequence getContentDescription() {
		CharSequence text = getText();
		if (mDrawCircle) {
			text = String.format(mItemIsSelectedText, text);
        }
		return text;
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mDrawCircle) {
			int width = getWidth();
			int heigth = getHeight();
			int radius = Math.min(width, heigth) / 2;
			canvas.drawCircle(width / 2, heigth / 2, radius, mCirclePaint);


			Paint.FontMetrics fontMetrics = mWhitePaint.getFontMetrics();
			float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
			float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
			Log.i("tag", "onDraw: top--->");
			Log.i("tag", "onDraw: bottom--->");

			int baseLineY = (int) (heigth / 2 - top/2 - bottom/2);//基线中间点的y轴计算公式


			canvas.drawText(getText().toString(),(width / 2), baseLineY,mWhitePaint);
		}
	}
}