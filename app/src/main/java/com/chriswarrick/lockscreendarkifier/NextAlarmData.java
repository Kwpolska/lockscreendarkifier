package com.chriswarrick.lockscreendarkifier;

import java.util.Objects;

public class NextAlarmData implements Cloneable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NextAlarmData that = (NextAlarmData)o;
        return timeInMillis == that.timeInMillis && nextState == that.nextState && setNow == that.setNow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextState, timeInMillis, setNow);
    }

    @Override
    public String toString() {
        return "NextAlarmData{" +
                "nextState=" + nextState +
                ", timeInMillis=" + timeInMillis +
                ", setNow=" + setNow +
                '}';
    }

    @Override
    public NextAlarmData clone() {
        try {
            NextAlarmData clone = (NextAlarmData)super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
