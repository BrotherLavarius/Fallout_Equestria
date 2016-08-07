package com.redsparkle.foe;

/**
 * Created by hoijima desu on 07.08.16 desu.
 */
public class Factory implements IRadiation {

    private long rads = 0;

    @Override
    public long rads() {
        return 0;
    }

    @Override
    public void rads(long value) {

        this.rads = value;
    }
}
