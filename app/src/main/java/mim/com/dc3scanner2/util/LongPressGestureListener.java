package mim.com.dc3scanner2.util;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class LongPressGestureListener extends GestureDetector.SimpleOnGestureListener  {
    private Context context;
    public LongPressGestureListener(Context context) {
        this.context=context;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        super.onLongPress(e);
        // e will give you the location and everything else you want
        // This is where you will be doing whatever you want to.
        Toast.makeText(context,"LONG PRESS...",Toast.LENGTH_LONG).show();
        int eIndex = MotionEventCompat.getActionIndex(e);
        float eX = MotionEventCompat.getX(e, eIndex);
        float eY = MotionEventCompat.getY(e, eIndex);
        Log.d("d","X:Y = " + eX + " : " + eY);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
}
