package com.chriswarrick.lockscreendarkifier;

public class Options {
    // Set hours depending on your needs. Example:
    static final int STARTS_AT = 22;
    static final int ENDS_AT = 6;
    static final boolean STARTS_HALF_PAST = true;
    static final boolean ENDS_HALF_PAST = true;

    // You probably don’t need to change this.
    static final int ADD_WHEN_BEFORE_MIDNIGHT = STARTS_AT > ENDS_AT ? 25 - STARTS_AT : 0;
    static final int NEAR_MS = 120 * 1000;
}
