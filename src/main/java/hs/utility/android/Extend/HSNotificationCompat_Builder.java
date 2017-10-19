package hs.utility.android.Extend;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by ParkHongSic(조장찡) on 2016-09-23.
 * @author ParkHongSic(조장찡)
 */

public class HSNotificationCompat_Builder extends NotificationCompat.Builder {

    private Context context;
    private Object obj;

    public HSNotificationCompat_Builder(Context context){super(context); this.context = context;}
    public HSNotificationCompat_Builder setTag(Object tag){obj = tag; return this;}
    public Object getTag(){return obj;}
}
