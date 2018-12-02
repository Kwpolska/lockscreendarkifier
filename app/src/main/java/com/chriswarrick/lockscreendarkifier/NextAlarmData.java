package com.chriswarrick.lockscreendarkifier;

public class NextAlarmData {
    StateRequest nextState;
    long timeInMillis;
    StateRequest setNow;

    public NextAlarmData(StateRequest nextState, long timeInMillis) {
        this.nextState = nextState;
        this.timeInMillis = timeInMillis;
        this.setNow = StateRequest.DO_NOTHING;
    }

    public NextAlarmData(StateRequest nextState, long timeInMillis, StateRequest setNow) {
        this.nextState = nextState;
        this.timeInMillis = timeInMillis;
        this.setNow = setNow;
    }
}
