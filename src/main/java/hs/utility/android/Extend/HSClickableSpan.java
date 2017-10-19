package hs.utility.android.Extend;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by ParkHongSic(조장찡) on 2016-10-14.
 * @author ParkHongSic(조장찡)
 */

public class HSClickableSpan extends ClickableSpan {
    private Object tag;

    public HSClickableSpan(){super();}
    public HSClickableSpan(Object Tag){setTag(Tag);}
    public HSClickableSpan(Object Tag, boolean Underline){setTag(Tag);setUnderline(Underline);}

    public void onClick(View widget){}
    public Object getTag(){return tag;}
    public void setTag(Object Tag){this.tag = Tag;}

    private boolean Underline = true;
    public HSClickableSpan setUnderline(boolean Underline){this.Underline = Underline; return this;}
    public boolean getUnderline(){return Underline;}


    @Override
    public void updateDrawState(TextPaint tp)
    {
        super.updateDrawState(tp);
        tp.setUnderlineText(Underline);
    }
    @Override
    public String toString(){return tag.toString();}
}
