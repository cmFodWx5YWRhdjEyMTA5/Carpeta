package mim.com.dc3scanner2.util.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.ImgRegion;
import mim.com.dc3scanner2.util.LongPressGestureListener;
import mim.com.dc3scanner2.util.interfaces.CanvasCommand;

public class MapView4BodegaImprovedMap extends View implements CanvasCommand, GestureDetector.OnGestureListener {
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

    public MapView4BodegaImprovedMap(Context context, int resource) {
        super(context);
        this.context = context;
        mGestureDetector = new GestureDetectorCompat(context, this);
        this.resource = resource;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        displayData();
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);


        Log.d("dasdsa PINTANDO!!!", "DSADSADAS");

        InputStream in = getResources().openRawResource(resource);
        try {
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(in, false);

            Rect bounds = new Rect();
            if (firstDraw) {
                bounds.left = 0;
                bounds.top = 0;
                bounds.right = widthPixels;
                bounds.bottom = heightPixels;
                firstDraw = false;

                Bitmap bitmap = decoder.decodeRegion(bounds, null);
                canvas.drawBitmap(bitmap, 0, 0, paint);
                invalidate();
            } else {

                bounds.left = widthBuffer;
                bounds.top = heightBuffer;
                bounds.right = widthPixels + widthBuffer;
                bounds.bottom = heightPixels + heightBuffer;
                Bitmap bitmap = decoder.decodeRegion(bounds, null);
                canvas.drawBitmap(bitmap, 0, 0, paint);

            }


            if (drawMarker) {
                canvas.drawBitmap(drawMarkerOnCanvas(), xMarkerPos, yMarkerPos, paint);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //canvas.drawText("hola!!!", metrics.widthPixels / 2, metrics.heightPixels / 2, paint);
        //canvas.drawBitmap(bitmap,0,0,paint);
    }

    private void displayData() {
        if (metrics == null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
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
        if (!drawMarker) {
            if (!((widthBuffer - widthOffset) < 0)) {
                widthBuffer = widthBuffer - widthOffset;

            } else {
                widthBuffer = 0;
            }
        } else {
            if ((xMarkerPos - markerIncrement) < 0) {
                xMarkerPos = 0;
            } else {
                xMarkerPos = xMarkerPos - markerIncrement;
            }
        }
        invalidate();

    }

    @Override
    public void moveRight() {
        if (!drawMarker) {
            if ((widthBuffer + widthPixels + widthOffset) < imageWidth) {
                widthBuffer = widthBuffer + widthOffset;
            } else {

            }
        } else {
            if ((xMarkerPos + markerIncrement) < widthPixels) {
                xMarkerPos = xMarkerPos + markerIncrement;
            }
        }
        invalidate();

    }

    @Override
    public void moveTop() {
        if (!drawMarker) {
            if (!((heightBuffer - heightOffset) < 0)) {
                heightBuffer = heightBuffer - heightOffset;
            } else {
                heightBuffer = 0;
            }
        } else {
            if ((yMarkerPos - markerIncrement) < 0) {
                yMarkerPos = 0;
            } else {
                yMarkerPos = yMarkerPos - markerIncrement;
            }
        }
        invalidate();
    }

    @Override
    public void moveBottom() {
        if (!drawMarker) {
            Log.d("moveBottom", "MOVEBOTTOM");
            if ((heightBuffer + heightPixels + heightOffset) < imageHeight) {
                heightBuffer = heightBuffer + heightOffset;
            }
        } else {
            if ((yMarkerPos + markerIncrement) < heightPixels) {
                yMarkerPos = yMarkerPos + markerIncrement;
            }
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
            posList.add(widthBuffer + xMarkerPos);
            posList.add(heightBuffer + yMarkerPos);
            return posList;

        } else {
            return null;
        }
    }

    @Override
    public void updatePosition(float x, float y) {

        displayData();

        int xMap = (int)(( x / widthPixels) * (imageWidth));
        int yMap = (int)(( y / heightPixels) * (imageHeight));

        widthBuffer = 0;
        heightBuffer = 0;
        while ((widthBuffer + widthPixels) < xMap) {
            widthBuffer = widthBuffer + widthOffset;
        }
        while ((heightBuffer + heightPixels) < yMap) {
            heightBuffer = heightBuffer + heightOffset;
        }


        //Toast.makeText(context, "x: " + xMap + " y: " + yMap + " wBuff: " + widthBuffer + " hBuff: " + heightBuffer, Toast.LENGTH_LONG).show();


        //invalidate();
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Toast.makeText(context, "LONG PRESS...", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
