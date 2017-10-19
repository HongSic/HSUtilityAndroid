package hs.utility.android.Control.Base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.WindowManager;

import hs.utility.android.Struct.SizeAndroid;
import hs.utility.android.Utils_Android;

/**
 * Created by ParkHongSic (조장찡) on 2016-11-15.
 */

public class HSView extends View
{
    Context context;
    private boolean Round;
    private float Radius = 100;

    public HSView(Context mContext)
    {
        super(mContext);
        context = mContext;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(Round)
        {
            Paint paint = new Paint();
            paint.setColor(0xFF0000);
            paint.setAlpha(255);
            paint.setStrokeWidth(2.0f);
            paint.setStyle(Paint.Style.STROKE);
            WindowManager mWinMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            SizeAndroid sz = Utils_Android.getResolution(getContext());
            int displayWidth = sz.Width;
            int displayHeight = sz.Height;
            canvas.drawCircle(displayWidth / 2, displayHeight / 4, Radius, paint);
            invalidate();
        }
    }

    public HSView setRound(boolean Round)
    {
        this.Round = Round;
        invalidate();
        return this;
    }
    public boolean getRound(){return Round;}
    public HSView setRadius(float Radius)
    {
        this.Radius = Radius;
        invalidate();
        return this;
    }
    public float getRadius() { return Radius;}
}
