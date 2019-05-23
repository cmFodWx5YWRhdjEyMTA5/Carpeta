package mim.com.dc3scanner2.util.custom;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

public class Led extends View {

    private Paint paint;
    private float x, y;
    private Context context;
    private int color;


    public Led(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        //paint.setColor(Color.GRAY);
    }

    public Led(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        paint = new Paint();
        //paint.setColor(Color.GRAY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final int w = metrics.widthPixels;
        final int h = metrics.heightPixels;

        float widthSpace = (float) (w * .20);

        Log.d("HOLA", String.valueOf(w));

        paint.setColor(color);
        // canvas.s
        //canvas.drawColor(Color.);
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, w / 12, paint);
    }


    public void setColor(int color) {
        this.color = color;
    }
}
