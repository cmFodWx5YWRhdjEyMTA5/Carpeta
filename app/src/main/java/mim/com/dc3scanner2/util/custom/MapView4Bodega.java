package mim.com.dc3scanner2.util.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.ImgRegion;
import mim.com.dc3scanner2.util.LongPressGestureListener;
import mim.com.dc3scanner2.util.interfaces.CanvasCommand;

public class MapView4Bodega extends View implements CanvasCommand {
    private Context context;

    private DisplayMetrics metrics;


    private boolean firstDraw = true;

    private List<ImgRegion> regionList = new ArrayList<>();
    private ImgRegion currentRegion = null;
    private int widthPixels;
    private int heightPixels;
    private int imageHeight = 0;
    private int imageWidth = 0;
    private int widthBuffer = 0;
    private int heightBuffer = 0;
    private int widthOffset = 0;
    private int heightOffset = 0;

    private boolean drawMarker = false;

    private GestureDetectorCompat mGestureDetector;
    private LongPressGestureListener longPressGestureListener;
    private int xMarkerPos = 0;
    private int yMarkerPos = 0;
    private int markerIncrement = 20;

    private int resource;

    public MapView4Bodega(Context context, int resource) {
        super(context);
        this.context = context;

        this.resource = resource;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        BitmapFactory.Options options = new BitmapFactory.Options();
        if (metrics == null) {

            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), resource, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;

            metrics = context.getResources().getDisplayMetrics();
            widthPixels = metrics.widthPixels;
            //heightPixels = metrics.heightPixels;
            widthOffset = widthPixels / 2;
            heightOffset = heightPixels / 3;
        }
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);


        Log.d("dasdsa PINTANDO!!!", "DSADSADAS");

        options.inSampleSize = calculateInSampleSize(options, widthPixels, heightPixels);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        BitmapFactory.decodeResource(getResources(), resource, options);

        if (drawMarker) {
            canvas.drawBitmap(drawMarkerOnCanvas(), xMarkerPos, yMarkerPos, paint);
        }

        //canvas.drawText("hola!!!", metrics.widthPixels / 2, metrics.heightPixels / 2, paint);
        //canvas.drawBitmap(bitmap,0,0,paint);
    }

    private Bitmap drawMarkerOnCanvas() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.in_activve, options);
        int imageHeight = options.outHeight;
        int mageWidth = options.outWidth;

        // Calculate inSampleSize
        //Log.d("TAMAÑO MARCADOR: ","X: "+(widthPixels / 12)+ " Y: "+(heightPixels / 12));
        options.inSampleSize = calculateInSampleSize(options, 45, 70);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), R.drawable.in_activve, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public void setParentHeight(int height) {
        heightPixels = height;
    }

    @Override
    public void moveLeft() {
        Log.d("DASDSADSADAS", "OSCURIDAD!!!!!!!!!");

        if ((xMarkerPos - markerIncrement) < 0) {
            xMarkerPos = 0;
        } else {
            xMarkerPos = xMarkerPos - markerIncrement;
        }

        invalidate();

    }

    @Override
    public void moveRight() {

        if ((xMarkerPos + markerIncrement) < widthPixels) {
            xMarkerPos = xMarkerPos + markerIncrement;
        }

        invalidate();

    }

    @Override
    public void moveTop() {

        if ((yMarkerPos - markerIncrement) < 0) {
            yMarkerPos = 0;
        } else {
            yMarkerPos = yMarkerPos - markerIncrement;
        }

        invalidate();
    }

    @Override
    public void moveBottom() {

        if ((yMarkerPos + markerIncrement) < heightPixels) {
            yMarkerPos = yMarkerPos + markerIncrement;
        }

        invalidate();
    }

    @Override
    public void placeMarker() {

        if (!drawMarker) {
            xMarkerPos = widthPixels / 2;
            yMarkerPos = heightPixels / 2;
            drawMarker = true;
        } else {
            drawMarker = false;
        }
        invalidate();
    }

    @Override
    public List<Integer> markPos() {
        if (drawMarker) {
            List<Integer> posList = new ArrayList<>();
            // posList.add(widthBuffer + xMarkerPos);
            //posList.add(heightBuffer + yMarkerPos)
            int finalValuex = (854 * (widthBuffer + xMarkerPos)) / widthPixels;
            int finalValuey = (726 * (heightBuffer + yMarkerPos)) / heightPixels;
            posList.add(finalValuex + (45));
            posList.add(finalValuey + (70));
            return posList;
        } else {
            return null;
        }
    }

    @Override
    public void updatePosition(float x, float y) {

    }

}
