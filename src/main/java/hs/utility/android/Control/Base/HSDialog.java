package hs.utility.android.Control.Base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import hs.utility.Interface.IDisposable;
import hs.utility.Struct.Size;
import hs.utility.android.R;

/**
 * Created by ParkHongSic(조장찡) on 2016-10-18.
 */

public class HSDialog implements IDisposable
{
    protected Dialog instance;
    protected Context mContext;

    protected HSDialog(){}

    public HSDialog(@NonNull final Context context)
    {
        this.mContext = context;
        instance = new Dialog(context);
        Init();
    }
    public HSDialog(@NonNull final View context)
    {
        this.mContext = context.getRootView().getContext();
        instance = new Dialog(mContext);
        Init();
    }
    public HSDialog(@NonNull final Context context, int RLayout)
    {
        this.mContext = context;
        instance = new Dialog(context);
        setContentView(RLayout);
        Init();
    }
    public HSDialog(@NonNull final View context, int RLayout)
    {
        this.mContext = context.getRootView().getContext();
        instance = new Dialog(mContext);
        setContentView(RLayout);
        Init();
    }
    public HSDialog(@NonNull final Context context, View view)
    {
        this.mContext = context;
        instance = new Dialog(context);
        setContentView(view);
        Init();
    }
    public HSDialog(@NonNull final View context, View view)
    {
        this.mContext = context.getRootView().getContext();
        instance = new Dialog(mContext);
        setContentView(view);
        Init();
    }
    public HSDialog(@NonNull final Context context, boolean ShowTitle)
    {
        this.mContext = context;
        instance = new Dialog(context);
        setShowTitle(ShowTitle);
        Init();
    }
    public HSDialog(@NonNull final View context, boolean ShowTitle)
    {
        this.mContext = context.getRootView().getContext();
        instance = new Dialog(mContext);
        setShowTitle(ShowTitle);
        Init();
    }
    public HSDialog(@NonNull final Context context, int RLayout, boolean ShowTitle)
    {
        this.mContext = context;
        instance = new Dialog(context);
        setShowTitle(ShowTitle);
        setContentView(RLayout);
        Init();
    }
    public HSDialog(@NonNull final View context, int RLayout, boolean ShowTitle)
    {
        this.mContext = context.getRootView().getContext();
        instance = new Dialog(mContext);
        setShowTitle(ShowTitle);
        setContentView(RLayout);
        Init();
    }
    public HSDialog(@NonNull final Context context, View view, boolean ShowTitle)
    {
        this.mContext = context;
        instance = new Dialog(context);
        setShowTitle(ShowTitle);
        setContentView(view);
        Init();
    }
    public HSDialog(@NonNull final View context, View view, boolean ShowTitle)
    {
        this.mContext = context.getRootView().getContext();
        instance = new Dialog(mContext);
        setShowTitle(ShowTitle);
        setContentView(view);
    }

    private void Init()
    {
        instance.getWindow().setBackgroundDrawableResource(R.drawable.view_background_light);
    }

    public final HSDialog setContentView(int RLayout){ instance.setContentView(RLayout); return this;}
    public final HSDialog setContentView(View view){ instance.setContentView(view); return this;}

    protected CharSequence mTitle = "";
    public HSDialog setTitle(CharSequence Title){mTitle = Title; instance.setTitle(Title); return this;}
    //public void setTitle(Html Title){title = Title; txt_title.setText(Title);}
    public CharSequence getTitle(){return mTitle;}

    protected CharSequence mText;
    public HSDialog setText(CharSequence Text){mText = Text; return this;}
    public CharSequence getText(){return mText;}

    @Deprecated
    public TextView getTitleControl()
    {
        TextView textView = (TextView) instance.findViewById(android.R.id.message);
        return textView;
    }

    public HSDialog setTransparent(float Opacity)
    {
        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = Opacity;
        instance.getWindow().setAttributes(lpWindow);
        return this;
    }
    public float getTransparent(){ return instance.getWindow().getAttributes().dimAmount; }

    protected HSDialog setShowTitle(boolean Show) {instance.requestWindowFeature(Show?Window.FEATURE_CUSTOM_TITLE:Window.FEATURE_NO_TITLE); return this;}

    public Size getSize()
    {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(instance.getWindow().getAttributes());
        return new Size (lp.width, lp.height);
    }
    public HSDialog setSize(Size size)
    {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(instance.getWindow().getAttributes());
        lp.width = size.Width;
        lp.height = size.Height;
        instance.getWindow().setAttributes(lp);
        return this;
    }
    public HSDialog setiOSStyle() {instance.getWindow().setBackgroundDrawableResource(R.drawable.dialog_style_ios); return this;}
    public int getWidth(){return getSize().Width;}
    public HSDialog setWidth(int Width){return setSize(new Size(Width, getHeight()));}
    public int getHeight(){return getSize().Height;}
    public HSDialog setHeight(int Height){return setSize(new Size(getWidth(), Height));}


    public final Context getContext(){return instance.getContext();}
    public final Context getContextBase(){return mContext;}
    @Deprecated
    public final Activity getActivity(){return getDialog().getOwnerActivity();}

    public final void startActivity(Intent intent){getContext().startActivity(intent);}

    public void show(){try{instance.show();}catch (Exception ex){ Toast.makeText(mContext, "다이얼로그 호출 실패!!\n\n"+ex.getMessage(), Toast.LENGTH_SHORT).show();}}
    @Deprecated
    public void hide(){instance.dismiss(); }
    public void exit(){hide(); Dispose();}

    public HSDialog setCancelable(boolean Cancleable){instance.setCancelable(Cancleable); return this;}
    public HSDialog setCanceledOnTouchOutside(boolean Cancleable){instance.setCanceledOnTouchOutside(Cancleable); return this;}

    /*
        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        dialog.getWindow().setAttributes(lpWindow);
    */

    public final View findViewById(int id){return instance.findViewById(id);}

    public void Dispose(){mContext = null; instance = null; System.gc();}

    public Dialog getDialog(){return instance;}
}
