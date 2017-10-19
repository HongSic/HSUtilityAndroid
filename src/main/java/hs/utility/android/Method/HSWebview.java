package hs.utility.android.Method;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by ParkHongSic(조장찡) on 2016-10-18.
 */

public class HSWebview
{
    final int WEBVIEW_TEXT_ZOOM = 100;

    protected Context context;
    protected WebView webview;
    protected ProgressDialog dialog_progress;

    public HSWebview(@NonNull Context context, @NonNull WebView webview)
    {
        this.context = context;
        this.webview = webview;
    }


    public HSWebview setLoading(boolean Show) {return setLoading(Show, false); }
    public HSWebview setLoading(final boolean Show, final boolean Cancleable)
    {
        /*
        if(Show)
        {Cancleable
            if(dialog_progress==null)dialog_progress = new ProgressDialog(context);
            dialog_progress.setMessage("로딩 중 입니다...");
            dialog_progress.setCanceledOnTouchOutside(false);
            dialog_progress.setCancelable(Cancleable);
            dialog_progress.setMax(100);
        }
        else {if(dialog_progress!=null)dialog_progress.dismiss(); dialog_progress = null;}*/
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(Show) {

                    if(dialog_progress==null)dialog_progress = new ProgressDialog(context);
                    {
                        dialog_progress.setCancelable(Cancleable);
                        dialog_progress.setCanceledOnTouchOutside(Cancleable);
                        dialog_progress.setMessage("로딩 중 입니다... (" + progress + "%)");
                        dialog_progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        dialog_progress.setProgress(progress);
                        try{dialog_progress.show();}
                        catch (Exception ex){Log.e("HSWebView Exception", "HSWebView::setLoading()", ex); }
                        if (progress >= 100) {
                            dialog_progress.setMessage("로딩 완료!!");
                            dialog_progress.dismiss();
                        }
                    }
                }
                else {dialog_progress.dismiss(); dialog_progress = null;}
            }
        });
        return this;
    }

    public HSWebview setZoomGesture(boolean On)
    {
        webview.getSettings().setBuiltInZoomControls(On);
        webview.getSettings().setDisplayZoomControls(!On);
        return this;
    }

    public HSWebview setTextZoomDefault()
    {
        WebSettings ws = webview.getSettings();
        ws.setTextZoom(WEBVIEW_TEXT_ZOOM);
        return this;
    }
    public HSWebview setTextZoom(int Size)
    {
        WebSettings ws = webview.getSettings();
        ws.setTextZoom(Size);
        return this;
    }
}

