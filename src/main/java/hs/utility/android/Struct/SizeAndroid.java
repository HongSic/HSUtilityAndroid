package hs.utility.android.Struct;

import android.content.Context;
import android.graphics.Rect;

import kr.arumnarae.finder.Libs.HSUtillity.Structs.Size;

/**
 * Created by 조장찡 on 2016-10-15.
 * @author 박홍식 (ParkHongSic, 조장찡, gpo04174, HSKernel)
 */

public class SizeAndroid extends Size
{
    public enum MeasureUnit {DP, Pixel}

    public Rect Rectangle;
    private MeasureUnit Unit = MeasureUnit.Pixel;
    private Context context;

    @Deprecated
    public SizeAndroid(){super();}
    public SizeAndroid(Context context){super(); Rectangle = new Rect(); this.context = context;}
    public SizeAndroid(Context context, MeasureUnit Unit){super(); Rectangle = new Rect(); this.context = context;this.Unit = Unit; }
    public SizeAndroid(Context context, int Width, int Height){super(Width, Height); this.context = context;}
    public SizeAndroid(Context context, Rect Rectangle){super(); setRect(Rectangle); this.context = context;}
    public SizeAndroid(Context context, int Width, int Height, MeasureUnit Unit){super(Width, Height); this.Unit = Unit; this.context = context;}
    public SizeAndroid(Context context, Rect Rectangle, MeasureUnit Unit){super(); setRect(Rectangle); this.Unit = Unit; this.context = context;}

    public SizeAndroid setRect(Rect Rectangle)
    {
        this.Rectangle = Rectangle;
        this.Width = Rectangle.width();
        this.Height = Rectangle.height();
        return this;
    }
    @Override
    public SizeAndroid setSize(int Width, int Height)
    {
        super.setSize(Width, Height);
        if(this.Rectangle==null)this.Rectangle = new Rect(0, 0, Width, Height);
        else this.Rectangle = new Rect(this.Rectangle.left, this.Rectangle.top, this.Rectangle.left+Width, this.Rectangle.left+Height);
        return this;
    }

    public SizeAndroid setContext(Context context){this.context = context; return this;}

    //오늘 요거 4개 구현하기
    public SizeAndroid setSizeDP(int WidthDP, int HeightDP)
    {
        if (Unit == MeasureUnit.DP)
        {
            this.Width = WidthDP;
            this.Height = HeightDP;
        }
        else
        {
            this.Width = getDPtoPX(context, WidthDP);
            this.Height = getDPtoPX(context, HeightDP);
        }
        return this;
    }
    public SizeAndroid setSizePX(int Width, int Height)
    {
        if (Unit == MeasureUnit.Pixel)
        {
            this.Width = Width;
            this.Height = Height;
        }
        else
        {
            this.Width = getPXtoDP(context, Width);
            this.Height = getPXtoDP(context, Height);
        }
        return this;
    }
    public SizeAndroid setUnit(MeasureUnit Unit)
    {
        this.Unit = Unit;
        if(Unit != this.Unit)
        {
            this.Width = Unit == MeasureUnit.Pixel ? getDPtoPX(context, Width) : getPXtoDP(context, Width);
            this.Height = Unit == MeasureUnit.Pixel ? getDPtoPX(context, Height) : getPXtoDP(context, Height);
        }
        return this;
    }

    public Size getSize(){return this;}
    public Size getSizeDP()
    {
        if (Unit != MeasureUnit.DP)
        {
            Size sz = new Size();
            sz.Width = getPXtoDP(context, Width);
            sz.Height = getPXtoDP(context, Height);
            return sz;
        }
        else return this;
    }
    public Size getSizePX()
    {
        if (Unit != MeasureUnit.Pixel)
        {
            Size sz = new Size();
            sz.Width = getDPtoPX(context, Width);
            sz.Height = getDPtoPX(context, Height);
            return sz;
        }
        else return this;
    }
    public MeasureUnit getUnit(){return Unit;}
    public Context getContext(){return this.context;}


    public static int getDPtoPX(final Context context, float DP)
    {
        return (int)(DP * context.getResources().getDisplayMetrics().density);
    }
    public static int getPXtoDP(final Context context, float PX)
    {
        return (int)(PX / context.getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean equals(Object obj)
    {
        SizeAndroid size = (SizeAndroid)obj;
        if(size == null) return false;
        else return Width == size.Width && Height == size.Height;
    }
}
