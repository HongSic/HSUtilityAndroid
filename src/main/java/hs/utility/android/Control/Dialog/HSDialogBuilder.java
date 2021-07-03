package hs.utility.android.Control.Dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import hs.utility.Struct.Size;
import hs.utility.android.Control.Base.HSDialog;
import hs.utility.android.R;

/**
 * Created by ParkHongSic(조장찡) on 2016-10-21.
 */

@Deprecated
/** 클래스의 완성이 아직 덜되었습니다. */
public class HSDialogBuilder extends HSDialog
{
    LinearLayout pnl;
    View splitter_title;
    LinearLayout pnl_title;
    ImageView pic_title;
    TextView txt_Title;
    View splitter_text;
    TextView txt_Text;
    TextView btn_ok;
    TextView btn_cancle;


    public HSDialogBuilder(@NonNull final Context context)
    {
        super(context);
        super.setShowTitle(false);
        instance.setContentView(R.layout.dialog_hsdialog);
        Init();
        setShowTitle(true);
        setShowText(false);
    }


    @Override
    public HSDialogBuilder setShowTitle(boolean Show)
    {
        pnl_title.setVisibility(Show?View.INVISIBLE:View.VISIBLE);
        splitter_title.setVisibility(Show?View.INVISIBLE:View.VISIBLE);
        return this;
    }
    public boolean getShowTitle(){return pnl_title.getVisibility()==View.VISIBLE;}

    public HSDialogBuilder setShowText(boolean Show)
    {
        splitter_title.setVisibility(Show?View.INVISIBLE:View.VISIBLE);
        txt_Text.setVisibility(Show?View.INVISIBLE:View.VISIBLE);
        return this;
    }
    public boolean getShowText(){return pnl_title.getVisibility()==View.VISIBLE;}

    @Override
    public HSDialogBuilder setText(CharSequence text)
    {
        super.setText(text);
        txt_Text.setText(text);
        return this;
    }

    @Override
    public void show(){show(false);}
    public void show(boolean FullScreen)
    {
        if(super.getTitle()==null)
        if(!FullScreen)
        {
            if(pnl.getHeight() < 1)
            {
                pnl.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                super.setSize(new Size(pnl.getMeasuredWidth(), pnl.getMeasuredHeight()));
            }
        }
        super.show();
    }


    void Init()
    {
        pnl = (LinearLayout)instance.findViewById(R.id.hsdialog_pnl);
        pnl_title = (LinearLayout)instance.findViewById(R.id.hsdialog_pnl_title);
        splitter_title = (View)instance.findViewById(R.id.hsdialog_splitter_title);
        pic_title = (ImageView)instance.findViewById(R.id.hsdialog_pic_title);
        txt_Title = (TextView)instance.findViewById(R.id.hsdialog_title);
        splitter_text = (View)instance.findViewById(R.id.hsdialog_splitter_text);
        txt_Text = (TextView)instance.findViewById(R.id.hsdialog_text);
        btn_ok = (TextView)instance.findViewById(R.id.hsdialog_btn_cancle);
        btn_cancle = (TextView)instance.findViewById(R.id.hsdialog_btn_ok);
    }
}
