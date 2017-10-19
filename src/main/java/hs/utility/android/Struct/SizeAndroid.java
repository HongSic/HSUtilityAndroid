package hs.utility.android.Struct;

import android.graphics.Rect;

import hs.utility.Struct.Size;

/**
 * Created by 조장찡 on 2016-10-15.
 */

public class SizeAndroid extends Size {

    public Rect Rectangle;
    private MeasureUnit Unit = MeasureUnit.Pixel;

    public SizeAndroid(){super(); Rectangle = new Rect();}
    public SizeAndroid(int Width, int Height){super(Width, Height);}
    public SizeAndroid(Rect Rectangle){super(); setRect(Rectangle);}
    public SizeAndroid(int Width, int Height, MeasureUnit Unit){super(Width, Height); this.Unit = Unit;}
    public SizeAndroid(Rect Rectangle, MeasureUnit Unit){super(); setRect(Rectangle); this.Unit = Unit;}

    public void setRect(Rect Rectangle)
    {
        this.Rectangle = Rectangle;
        super.Width = Rectangle.width();
        super.Height = Rectangle.height();
    }
    @Override
    public void setSize(int Width, int Height)
    {
        super.setSize(Width, Height);
        if(this.Rectangle==null)this.Rectangle = new Rect(0, 0, Width, Height);
        else this.Rectangle = new Rect(this.Rectangle.left, this.Rectangle.top, this.Rectangle.left+Width, this.Rectangle.left+Height);
    }

    public enum MeasureUnit {DP, Pixel}
}
