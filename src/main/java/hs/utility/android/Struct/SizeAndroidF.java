package khs.utility.android.Struct;

import android.content.Context;
import android.graphics.RectF;

import kr.arumnarae.finder.Libs.HSUtillity.Structs.SizeF;

/**
 * Created by 조장찡 on 2016-10-15.
 * @author 박홍식 (ParkHongSic, 조장찡, gpo04174, HSKernel)
 */

public class SizeAndroidF extends SizeF
{
    public enum MeasureUnit {DP, Pixel}

    public RectF Rectangle;
    private MeasureUnit Unit = MeasureUnit.Pixel;
    private Context context;

    @Deprecated
    public SizeAndroidF(){super();}
    public SizeAndroidF(Context context){super(); Rectangle = new RectF(); this.context = context;}
    public SizeAndroidF(Context context, MeasureUnit Unit){super(); Rectangle = new RectF(); this.context = context;this.Unit = Unit; }
    public SizeAndroidF(Context context, float Width, float Height){super(Width, Height); this.context = context;}
    public SizeAndroidF(Context context, RectF Rectangle){super(); setRect(Rectangle); this.context = context;}
    public SizeAndroidF(Context context, float Width, float Height, MeasureUnit Unit){super(Width, Height); this.Unit = Unit; this.context = context;}
    public SizeAndroidF(Context context, RectF Rectangle, MeasureUnit Unit){super(); setRect(Rectangle); this.Unit = Unit; this.context = context;}

    public SizeAndroidF setRect(RectF Rectangle)
    {
        this.Rectangle = Rectangle;
        super.Width = Rectangle.width();
        super.Height = Rectangle.height();
        return this;
    }
    @Override
    public SizeAndroidF setSize(float Width, float Height)
    {
        super.setSize(Width, Height);
        if(this.Rectangle==null)this.Rectangle = new RectF(0, 0, Width, Height);
        else this.Rectangle = new RectF(this.Rectangle.left, this.Rectangle.top, this.Rectangle.left+Width, this.Rectangle.left+Height);
        return this;
    }

    public SizeAndroidF setContext(Context context){this.context = context; return this;}

    //오늘 요거 4개 구현하기
    public SizeAndroidF setSizeDP(int WidthDP, int HeightDP)
    {
        if (Unit == MeasureUnit.DP)
        {
            this.Width = WidthDP;
            this.Height = HeightDP;
        }
        else
        {
            this.Width = getDPtoPX(context, WidthDP);
            this.Height =getDPtoPX(context, HeightDP);
        }
        return this;
    }
    public SizeAndroidF setSizePX(int Width, int Height)
    {
        if (Unit == MeasureUnit.Pixel)
        {
            this.Width = Width;
            this.Height = Height;
        }
        else
        {
            this.Width = (int) getPXtoDP(context, Width);
            this.Height = (int) getPXtoDP(context, Height);
        }
        return this;
    }
    public SizeAndroidF setUnit(MeasureUnit Unit)
    {
        this.Unit = Unit;
        if(Unit != this.Unit)
        {
            this.Width = Unit == MeasureUnit.Pixel ? getDPtoPX(context, Width) : getPXtoDP(context, Width);
            this.Height = Unit == MeasureUnit.Pixel ? getDPtoPX(context, Height) : getPXtoDP(context, Height);
        }
        return this;
    }

    public SizeF getSize(){return this;}
    public SizeF getSizeDP()
    {
        if (Unit != MeasureUnit.DP)
        {
            SizeF sz = new SizeF();
            sz.Width = (int) getPXtoDP(context, Width);
            sz.Height = (int) getPXtoDP(context, Height);
            return sz;
        }
        else return this;
    }
    public SizeF getSizePX()
    {
        if (Unit != MeasureUnit.Pixel)
        {
            SizeF sz = new SizeF();
            sz.Width = (int) getDPtoPX(context, Width);
            sz.Height = (int) getDPtoPX(context, Height);
            return sz;
        }
        else return this;
    }
    public MeasureUnit getUnit(){return Unit;}
    public Context getContext(){return this.context;}


    public static float getDPtoPX(final Context context, float DP)
    {
        return DP * context.getResources().getDisplayMetrics().density;
    }
    public static float getPXtoDP(final Context context, float PX)
    {
        return PX / context.getResources().getDisplayMetrics().density;
    }

    @Override
    public boolean equals(Object obj)
    {
        SizeAndroidF size = (SizeAndroidF)obj;
        if(size == null) return false;
        else return Width == size.Width && Height == size.Height;
    }
}
