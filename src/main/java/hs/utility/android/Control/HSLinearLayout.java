package hs.utility.android.Control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by ParkHongSic (조장찡) on 2016-09-06.
 */
public class HSLinearLayout extends LinearLayout {
    public HSLinearLayout(Context context){super(context);}
    public HSLinearLayout(Context context, AttributeSet attrs){super(context);}

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }
}
