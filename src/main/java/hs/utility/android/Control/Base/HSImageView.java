package hs.utility.android.Control.Base;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by ParkHongSic (조장찡) on 2016-09-03.
 */
public class HSImageView extends android.widget.ImageView{

    public boolean Enable = true;
    //protected boolean Rounding = false;

    // 라운드처리 강도 값을 크게하면 라운드 범위가 커짐
    //public float RoundinGradius = 18.0f;


    public HSImageView(Context context) { super(context);}

    public HSImageView(Context context, AttributeSet attrs) {this(context, attrs, 0); }

    public HSImageView(Context context, AttributeSet attrs, int defStyle) {super(context, attrs, defStyle); }


    @Override
    public void setEnabled(boolean Enable)
    {
        this.Enable = Enable;
        super.setEnabled(Enable);
    }
    public boolean getEnable(){return Enable;}

    /*
    public HSImageView setRounding(boolean Rounding){this.Rounding = Rounding;return this;}
    public boolean getRounding(){return Rounding;}*/

}
