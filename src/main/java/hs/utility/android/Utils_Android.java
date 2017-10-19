package hs.utility.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileDescriptor;
import java.security.MessageDigest;
import java.util.ArrayList;

import hs.utility.Utils;
import hs.utility.android.Struct.SizeAndroid;

/**
 * Created by ParkHongSic on 2016-09-18.
 * @author 박홍식 (ParkHongSic, 조장찡, gpo04174, HSKernel)
 */
public class Utils_Android extends Utils {

    public static SizeAndroid getResolution(Activity activity)
    {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
        {
            Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            return new SizeAndroid(activity, display.getWidth(), display.getHeight());
        }
        else
        {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return new SizeAndroid(activity, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
    }
    //http://androi.tistory.com/143 참고
    public static SizeAndroid getResolution(Context context)
    {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return new SizeAndroid(context, metrics.widthPixels, metrics.heightPixels);
    }
    public static SizeAndroid getResolutionDP(Context context)
    {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return new SizeAndroid(context, getPixelToDp(context, metrics.widthPixels), getPixelToDp(context, metrics.heightPixels), SizeAndroid.MeasureUnit.DP);
    }


    /** Pixel ---> DP*/
    public static int getPixelToDp(Context context, int Pixel) {
        float dp = 0;
        try
        {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            dp = Pixel / (metrics.densityDpi / 160f);
        }
        catch (Exception e) { }
        return (int) dp;
    }

    /** DP ---> Pixel*/
    public static int getDpToPixel(Context context, int DP) {
        float px = 0;
        try {px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP, context.getResources().getDisplayMetrics());}
        catch (Exception e) {}
        return (int) px;
    }

     /** DP ---> Pixel*/
    public static int getDpToPixel(Context context, float DP) {
        float px = 0;
        try {px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP, context.getResources().getDisplayMetrics());}
        catch (Exception e) { }
        return (int) px;
    }



    /**예전에 C#에서도 라벨 너비 자동으로 구하는 함수가 없어서 제가 직접 구현한거를 가져온것입니다. (대표: HS 플레이어) (안드로이드 TextView랑 C#의 Lable 이랑 차이가 많이나서 실제 사이즈구하는 부분 일부만 수정)
     TextCut, TextCut_Android, TextCutArray, TextCutArray_Android, Cut algorithms and function is developed by ParkHongSic(박홍식) and all right reserved.*/
    public static final float ZoomDefault = 1.05f;
    public static float Zoom = ZoomDefault;

    /**
     텍스트를 길이에 맞게 자릅니다.
     @param tv 적용이되는 라벨 입니다.
     @param Value 텍스트 입니다.
     @param Width 최대 가로 길이 입니다.*/
    public static String TextCut(TextView tv, String Value, int Width)
    {
        return TextCut(tv, Value, Width, Zoom);
    }

    /**
     텍스트를 길이에 맞게 자른다음 자른 줄들을 반환합니다.
     @param tv 적용이되는 라벨 입니다.
     @param Value 텍스트 입니다.
     @param Width 최대 가로 길이 입니다.
     @param Zoom 확대 배율 입니다.*/
    public static String TextCut(TextView tv, String Value, int Width, float Zoom)
    {
        if (Width == 0) return Value;

        String text = Value;

        String tmp = "...";
        try
        {
            //HS_Audio.LIBRARY.HSConsole.WriteLine(string.Format("==========================================\nText = {0}\n==========================================", Value));

            Paint paint = new Paint();
            Rect rec = new Rect();

            paint.setTypeface(Typeface.DEFAULT);// your preference here
            paint.setTextSize(tv.getTextSize()*Zoom);// have this the same as your text size

            int len = CUT(Value, paint, Width, tmp);
            text = StringUtility.remove(Value, len) + (len<1?Value:tmp);

            //paint.setTypeface(tv.getTypeface());
            //textSize = tv.getTextSize();
            //paint.setTextSize(textSize);
            //paint = tv.getPaint();
            //float width = paint.measureText(text);

            /*
            if(tv.getWidth() < 1)
            {
                tv.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                sf.Width = tv.getMeasuredWidth();
                sf.Height = tv.getMeasuredHeight();
            }
            else{sf = new Size(tv.getWidth(), tv.getHeight());}*/
            //HS_Audio.LIBRARY.HSConsole.WriteLine(string.Format("StringSize = {0}, diff = {1}, StringSize > diff = {2}\n", sf.Width, diff, sf.Width > diff));

            //if (len < Value.Length)HSConsole.WriteLine(string.Format("==========================================\nCutting Text...\nText = {0}\n==========================================", Value));
        }
        catch (Exception ex)
        {

        }
        finally {}
        return text;
    }
    public static String TextCut_Android(TextView tv, String Value, int Width, float Zoom)
    /**
     텍스트를 길이에 맞게 자른다음 자른 줄들을 반환합니다.
     @param tv 적용이되는 라벨 입니다.
     @param Value 텍스트 입니다.
     @param Width 최대 가로 길이 입니다.*/
    {
        if (Width == 0) return Value;

        SizeAndroid sf = new SizeAndroid(tv.getContext());
        String text = Value;

        String tmp = "...";
        try
        {
            //HS_Audio.LIBRARY.HSConsole.WriteLine(string.Format("==========================================\nText = {0}\n==========================================", Value));

            Paint paint = new Paint();
            Rect rec = new Rect();

            paint.setTypeface(Typeface.DEFAULT);// your preference here
            paint.setTextSize(tv.getTextSize()*Zoom);// have this the same as your text size

            paint.getTextBounds(text, 0, text.length(), rec);
            sf.setRect(rec);

            int len = CUT(Value, paint, Width, tmp);
            text = StringUtility.remove(Value, len) + (len<1?Value:tmp);
        }
        catch (Exception ex) {Log.e("HSUtilityAndroid", "TextCut_Android: ", ex); }
        finally {}
        return text;
    }
    @Deprecated
    public static String[] TextCutArray(TextView tv, String Value, int Width)
    /**
     텍스트를 길이에 맞게 자른다음 자른 줄들을 반환합니다. (Android 는 New 가 붙은 함수를 이용해주세요.)
     @param tv 적용이되는 라벨 입니다.
     @param Value 텍스트 입니다.
     @param Width 최대 가로 길이 입니다.
     @param Zoom 확대 (오차율) 스케일 입니다. (1.05 이 기본값)
     @author ParkHongSic(박홍식)*/
    {
        return TextCutArray(tv, Value, Width, Zoom);
    }
    public static String[] TextCutArray_Android(TextView tv, String Value, int Width)
    /**
     텍스트를 길이에 맞게 자른다음 자른 줄들을 반환합니다. (Android 는 New 가 붙은 함수를 이용해주세요.)
     @param tv 적용이되는 라벨 입니다.
     @param Value 텍스트 입니다.
     @param Width 최대 가로 길이 입니다.
     @param Zoom 확대 (오차율) 스케일 입니다. (1.05 이 기본값)
     @author ParkHongSic(박홍식)*/
    {
        return TextCutArray_Android(tv, Value, Width, Zoom);
    }
    @Deprecated
    public static String[] TextCutArray(TextView tv, String Value, int Width, float Zoom)
    {
        if (Width == 0) return new String[]{Value};
        int diff = Math.abs(Width);
        int len = 10;
        SizeAndroid sf = new SizeAndroid(tv.getContext());
        String text = Value;

        ArrayList<String> list = new ArrayList<>();
        try
        {
            //HS_Audio.LIBRARY.HSConsole.WriteLine(string.Format("==========================================\nText = {0}\n==========================================", Value));

            Paint paint = new Paint();
            Rect rec = new Rect();

            paint.setTypeface(Typeface.DEFAULT);// your preference here
            paint.setTextSize(tv.getTextSize()*Zoom);// have this the same as your text size

            paint.getTextBounds(text, 0, text.length(), rec);
            sf.setRect(rec);

            if (sf.Width < diff) return new String[]{Value};
            while(len > 0)
            {
                len = CUT(Value, paint, Width, null);
                list.add(len==0?Value: Utils.StringUtility.remove(Value, len));
                Value =Value.substring(len);
            }
        }
        catch (Exception ex) {}
        finally {}

        String[] ss = new String[list.size()];
        for(int i = 0; i < ss.length; i++)ss[i] = list.get(i);
        list.clear(); //램 누수 유발원인 제거
        return ss;
    }
    public static String[] TextCutArray_Android(TextView tv, String Value, int Width, float Zoom)
    {
        int len = 0;
        if (Width == 0 || CUT_Android(Value, tv, Width, null, Zoom) <= Value.length()) return new String[]{Value};
        ArrayList<String> list = new ArrayList<>();

        while(len > 0)
        {
            len = CUT_Android(Value, tv, Width, null, Zoom);
            list.add(len==0?Value: StringUtility.remove(Value, len));
            Value = Value.substring(len);
        }

        String[] ss = new String[list.size()];
        for(int i = 0; i < ss.length; i++)ss[i] = list.get(i);
        list.clear(); //램 누수 유발원인 제거
        return ss;
    }

    /**텍스트를 자릅니다.
     TextCut, TextCut_Android, TextCutArray, TextCutArray_Android, Cut algorithms and function is developed by ParkHongSic(박홍식) and all right reserved.*/

    @Deprecated
    private static int CUT(String Value, Paint paint, int Width, String AddString)
    {
        int len = 10;
        byte PlusFlag = 0, MinusFlag = 0;
        Rect rec = new Rect();
        int Diff = Math.abs(Width);
        String text = Value;
        SizeAndroid sf = new SizeAndroid();

        paint.getTextBounds(text, 0, text.length(), rec);
        sf.setRect(rec);
        if (sf.Width < Diff) return 0;

        Log.d("TextCut", "=========="+Value+"==========");
        try {
            while (len < Value.length()) {
                text = StringUtility.remove(Value, len) + (AddString == null ? "" : AddString);

                paint.getTextBounds(text, 0, text.length(), rec);
                sf.setRect(rec);

                if (sf.Width < Diff) { len++; if (MinusFlag > 0) break;else PlusFlag++; }
                else if (sf.Width > Diff) { len--; if (PlusFlag > 0) break; else MinusFlag++; }
                else break;
                //Log.d("TextCut", String.format("StringSize=%d, len=%d, PlusFlag=%d, MinusFlag=%s", sf.Width, len, PlusFlag, MinusFlag, Value));
            }
        }catch (Exception ex)
        {
            //Log.e("TextCut", String.format("Error!! - %s\n{StringSize=%d, len=%d, PlusFlag=%d, MinusFlag=%s}",ex.getMessage(), sf.Width, len, PlusFlag, MinusFlag, Value));
            len = 0;
        }

        return len;


        //paint.setTypeface(tv.getTypeface());
        //textSize = tv.getTextSize();
        //paint.setTextSize(textSize);
        //paint = tv.getPaint();
        //float width = paint.measureText(text);

            /*
            if(tv.getWidth() < 1)
            {
                tv.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                sf.Width = tv.getMeasuredWidth();
                sf.Height = tv.getMeasuredHeight();
            }
            else{sf = new Size(tv.getWidth(), tv.getHeight());}*/
        //HS_Audio.LIBRARY.HSConsole.WriteLine(string.Format("StringSize = {0}, diff = {1}, StringSize > diff = {2}\n", sf.Width, diff, sf.Width > diff));

        //if (len < Value.Length)HSConsole.WriteLine(string.Format("==========================================\nCutting Text...\nText = {0}\n==========================================", Value));
    }
    private static int CUT_Android(String Value, TextView tv, int Width, String AddString, float Zoom)
    {
        String text = Value;

        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextSize(tv.getTextSize() * Zoom);
        int result = paint.breakText(AddString==null?text:text+AddString, true, Math.abs(Width), null);
        return result<1?result:Value.length() - result;
    }

    public static class ImageUtility
    {
        public static Bitmap CropRound(Bitmap scaleBitmapImage, int targetWidth, int targetHeight) {

            Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                    targetHeight,Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(targetBitmap);
            Path path = new Path();
            path.addCircle(((float) targetWidth - 1) / 2,
                    ((float) targetHeight - 1) / 2,
                    (Math.min(((float) targetWidth),
                            ((float) targetHeight)) / 2),
                    Path.Direction.CCW);

            canvas.clipPath(path);
            Bitmap sourceBitmap = scaleBitmapImage;
            canvas.drawBitmap(sourceBitmap,
                    new Rect(0, 0, sourceBitmap.getWidth(),
                            sourceBitmap.getHeight()),
                    new Rect(0, 0, targetWidth,
                            targetHeight), null);
            return targetBitmap;
        }
        public static Bitmap CropRound(Bitmap scaleBitmapImage, int targetWidth, int targetHeight, int color) {

            Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                    targetHeight,Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(targetBitmap);
            canvas.drawColor(color);
            Path path = new Path();
            path.addCircle(((float) targetWidth - 1) / 2,
                    ((float) targetHeight - 1) / 2,
                    (Math.min(((float) targetWidth),
                            ((float) targetHeight)) / 2),
                    Path.Direction.CCW);

            canvas.clipPath(path);
            Bitmap sourceBitmap = scaleBitmapImage;
            canvas.drawBitmap(sourceBitmap,
                    new Rect(0, 0, sourceBitmap.getWidth(),
                            sourceBitmap.getHeight()),
                    new Rect(0, 0, targetWidth,
                            targetHeight), null);
            return targetBitmap;
        }


        public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth)
            {
                /*
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) > reqHeight
                        && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2;
                }*/

                // Calculate ratios of height and width to requested height and width
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);

                // Choose the smallest ratio as inSampleSize value, this will guarantee
                // a final image with both dimensions larger than or equal to the
                // requested height and width.
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }

            return inSampleSize;
        }


        public static int calculateInSampleSize_Ceil(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth)
            {
                /*
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) > reqHeight
                        && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2;
                }*/

                // Calculate ratios of height and width to requested height and width
                int heightRatio = (int)Math.ceil((float) height / (float) reqHeight);
                int widthRatio = (int)Math.ceil((float) width / (float) reqWidth);

                // Choose the smallest ratio as inSampleSize value, this will guarantee
                // a final image with both dimensions larger than or equal to the
                // requested height and width.
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }

            return inSampleSize;
        }

        public static Bitmap getBitmap(@NotNull Resources res, int id, int Width, int Height)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, id, options);
            options.inSampleSize = calculateInSampleSize(options, Width, Height);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, id, options);
        }
        public static Bitmap getBitmap(@NotNull File file, int Width, int Height)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getPath(), options);
            options.inSampleSize = calculateInSampleSize(options, Width, Height);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(file.getPath(), options);
        }
        public static Bitmap getBitmap(@NotNull byte[] array, int Width, int Height){ return getBitmap(array, 0, array.length, Width, Height); }
        public static Bitmap getBitmap(@NotNull byte[] array, int offset, int length, int Width, int Height)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(array, offset, length, options);
            options.inSampleSize = calculateInSampleSize(options, Width, Height);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeByteArray(array, offset, length, options);
        }
        public static Bitmap getBitmap(@NotNull FileDescriptor descriptor, int Width, int Height)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(descriptor, null, options);
            options.inSampleSize = calculateInSampleSize(options, Width, Height);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFileDescriptor(descriptor, null, options);
        }
        public static Bitmap getBitmap(@Nullable Drawable drawable)
        {
            if(drawable == null)return null;
            else return ((BitmapDrawable)drawable).getBitmap();
        }

        @Deprecated
        public static Drawable getDrawable(@Nullable Bitmap bitmap)
        {
            if(bitmap == null)return null;
            else return new BitmapDrawable(bitmap);
        }
        public static Drawable getDrawable(@Nullable Bitmap bitmap, @NotNull Resources res)
        {
            if(bitmap == null)return null;
            else return new BitmapDrawable(res, bitmap);
        }
    }

    public static class EtcUtility
    {
        //public static void PermisionRequest()

        public static void setClipboard(Context context, String Text)
        {

            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB)
            {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(Text);
            }
            else
            {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", Text);
                clipboard.setPrimaryClip(clip);
            }
        }

        //Log.d("SHA Key", "Hash: " + getHashKey(getContext()));
        public static String getAppSHAKey(Context context)
        {
            try
            {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature sig: info.signatures)
                {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(sig.toByteArray());
                    return Base64.encodeToString(md.digest(), Base64.DEFAULT);
                }
            }
            catch (Exception ex){ex.printStackTrace();}
            return null;
        }

        static Toast toast;
        public static void showToast(final Context context, final String Message, final int Length)
        {
            try
            {
                if(toast == null)toast = Toast.makeText(context, Message, Length);
                else
                {
                    toast.setDuration(Length);
                    toast.setText(Message);
                    //toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                toast = Toast.makeText(context, Message, Length);
                toast.show();
            }
        }
        public static void showToastForThread(final Activity activity, final String Message, final int Length)
        {
            activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            try
                            {
                                if(toast == null)toast = Toast.makeText(activity.getApplicationContext(), Message, Length);
                                else
                                {
                                    toast.setDuration(Length);
                                    toast.setText(Message);
                                    //toast.setGravity(Gravity.CENTER, 0, 0);
                                }
                                toast.show();
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                                toast = Toast.makeText(activity.getApplicationContext(), Message, Length);
                                toast.show();
                            }
                        }
                    } );
        }
        public static void showToastForThread(final Activity activity, final int ResStringId, final int Length)
        {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    try
                    {
                        if(toast == null)toast = Toast.makeText(activity.getApplicationContext(), ResStringId, Length);
                        else
                        {
                            toast.setDuration(Length);
                            toast.setText(ResStringId);
                            //toast.setGravity(Gravity.CENTER, 0, 0);
                        }
                        toast.show();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        toast = Toast.makeText(activity.getApplicationContext(), ResStringId, Length);
                        toast.show();
                    }
                }
            } );
        }

        public static PackageInfo getApplicationInfo(@NotNull Context context, @NotNull String PackageName)
        {
            PackageInfo pi = null;
            try
            {
                PackageManager pm = context.getPackageManager();
                pi = pm.getPackageInfo(PackageName,  PackageManager.GET_ACTIVITIES);
            }
            catch (PackageManager.NameNotFoundException e) {}
            catch (Exception ex){ex.printStackTrace();}

            return pi;
        }
    }
}
