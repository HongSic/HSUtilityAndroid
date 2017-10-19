package hs.utility.android.Gesture;

import java.util.EnumSet;
import java.util.Set;

import hs.utility.android.Event.HSEventArgs;

/**
 * Created by ParkHongSic on 2016-09-03.
 * @author ParkHongSic(조장찡)
 */
public class HSGestureEventArgs extends HSEventArgs {

    private Set<HSGestureSwypeEnum> SwypePosition = EnumSet.of(HSGestureSwypeEnum.UNKNOWN);

    public HSGestureEventArgs(EnumSet SwypePosition) {this.SwypePosition = SwypePosition;  }

    public Set<HSGestureSwypeEnum> getSwype() {
        return SwypePosition;
    }

    public HSGestureSwypeEnum getSwypeAction()
    {
        if(SwypePosition.contains(HSGestureSwypeEnum.FLING))return HSGestureSwypeEnum.FLING;
        else if(SwypePosition.contains(HSGestureSwypeEnum.SCROLL))return HSGestureSwypeEnum.SCROLL;
        else return HSGestureSwypeEnum.UNKNOWN;
    }
    public HSGestureSwypeEnum getSwypePosition()
    {
        for (HSGestureSwypeEnum Swype : SwypePosition)
        {
            if(Swype != HSGestureSwypeEnum.FLING || Swype != HSGestureSwypeEnum.SCROLL)
                return Swype;
        }
        return HSGestureSwypeEnum.UNKNOWN;
    }
}
