package hs.utility.android.Gesture;

import java.util.EnumSet;
import java.util.Set;

import hs.utility.Event.HSEventArgs;

/**
 * Created by ParkHongSic on 2016-09-03.
 * @author ParkHongSic(조장찡)
 */
public class HSGestureEventArgs extends HSEventArgs {

    private Set<HSGestureSwypeEnum> SwypePosition = EnumSet.of(HSGestureSwypeEnum.UNKNOWN);

    public HSGestureEventArgs(EnumSet SwypePosition) {
        this.SwypePosition = SwypePosition;
    }

    public Set<HSGestureSwypeEnum> getSwypePosition() {
        return SwypePosition;
    }
}
