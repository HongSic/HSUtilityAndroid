package hs.utility.android.Control;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

//http://mantdu.tistory.com/722
//view.getRootView().getContext()
/**
 * Created by ParkHongSic (조장찡) on 2016-09-06.
 */
public class HSListView extends ListView{
    /**
     * The below 3 Contructors MUST be here
     */

    /**
     * only used as the view is created in the source
     */
    public HSListView(Context context) {super(context);  }

    /**
     * used the case view of xml file is defined
     */
    public HSListView(Context context, AttributeSet attrs) {super(context, attrs); }

    /**
     * used the case PROPERTY is defined in xml
     */
    public HSListView(Context context, AttributeSet attrs, int defStyle) {super(context, attrs, defStyle);}

}
