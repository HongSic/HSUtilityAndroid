package hs.utility.android.Gesture;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.EnumSet;

import hs.utility.Event.HSEvent;
import hs.utility.Event.HSEventHandler;

/**
 * Created by ParkHongSic on 2016-09-03.
 * @author ParkHongSic(조장찡)
 */
public class HSGestureDetector extends GestureDetector.SimpleOnGestureListener {

    public HSEvent<HSGestureEventArgs> TouchEvent = new HSEvent<>();

    private GestureDetectorCompat gestureDetector;
    private Context context;
    public boolean Processing = false;

    public HSGestureDetector(Context context)
    {
        gestureDetector = new GestureDetectorCompat(context, this);
        this.context = context;
    }
    public HSGestureDetector(View view)
    {
        gestureDetector = new GestureDetectorCompat(view.getContext(), this);
        this.context = view.getContext();
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                HSGestureDetector.this.onTouchEvent(event);
                return true;
            }
        });
    }
    @Deprecated
    public HSGestureDetector(){}

    private int SWIPE_MIN_DISTANCE = 120;
    private int SWIPE_MAX_OFF_PATH = 250;
    private int SWIPE_THRESHOLD_VELOCITY = 200;

    private EnumSet SwypeE = EnumSet.of(HSGestureSwypeEnum.UNKNOWN);

    //private GestureDetector gestureScanner;
    //public boolean onTouchEvent(MotionEvent me) { return gestureScanner.onTouchEvent(me);  }

    public void onTouchEvent(MotionEvent e){gestureDetector.onTouchEvent(e);}
    public void setaddEvent(HSEventHandler<HSGestureEventArgs> event){TouchEvent.addEventHandler(event);}



    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        HSGestureSwypeEnum e = DetectGesture(e1,e2, velocityX, velocityY);
        switch (e)
        {
            case UP:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.UP)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.UP);
                break;
            case DOWN:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.DOWN)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.UP);
                break;
            case RIGHT:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.RIGHT)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.UP);
                break;
            case LEFT:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.LEFT)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.FLING,HSGestureSwypeEnum.UP);
                break;
            default:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.FLING)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.FLING);
                break;
        }
        if(Processing)super.onFling(e1,e2, velocityX, velocityY);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        HSGestureSwypeEnum e = DetectGesture(e1,e2, distanceX, distanceX);
        switch (e)
        {
            case UP:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.UP)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.UP);
                break;
            case DOWN:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.DOWN)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.UP);
                break;
            case RIGHT:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.RIGHT)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.UP);
                break;
            case LEFT:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.LEFT)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.SCROLL,HSGestureSwypeEnum.UP);
                break;
            default:
                TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.SCROLL)));
                SwypeE = EnumSet.of(HSGestureSwypeEnum.SCROLL);
                break;
        }
        if(Processing)super.onScroll(e1,e2, distanceX, distanceX);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.DOWN)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.DOWN);
        if(Processing)super.onDown(e);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.PRESS_LONG)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.PRESS_LONG);
        if(Processing)super.onLongPress(e);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.PRESS)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.PRESS);
        if(Processing)super.onShowPress(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent ev) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.TOUCH_SINGLE)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.TOUCH_SINGLE);
        if(Processing)super.onSingleTapConfirmed(ev);
        return true;
    }


    @Override
    public boolean onDoubleTap(MotionEvent e) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.TOUCH_DOUBLE)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.TOUCH_DOUBLE);
        if(Processing)super.onDoubleTap(e);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.TOUCH_DOUBLE)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.TOUCH_DOUBLE);
        if(Processing)super.onDoubleTapEvent(e);
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        TouchEvent.raiseEvent(this, new HSGestureEventArgs(EnumSet.of(HSGestureSwypeEnum.TOUCH_SINGLE)));
        SwypeE = EnumSet.of(HSGestureSwypeEnum.TOUCH_SINGLE);
        if(Processing)super.onSingleTapUp(e);
        return true;
    }

    public EnumSet getLastGestureEvent(){return SwypeE;}

    private HSGestureSwypeEnum DetectGesture(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)return HSGestureSwypeEnum.UNKNOWN;

            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)return HSGestureSwypeEnum.RIGHT;
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)return HSGestureSwypeEnum.LEFT;
            // down to up swipe
            else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)return HSGestureSwypeEnum.UP;
            // up to down swipe
            else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)return HSGestureSwypeEnum.DOWN;
        } catch (Exception e) { }

        return HSGestureSwypeEnum.UNKNOWN;
    }
}
