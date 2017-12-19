package com.redsparkle.api.Capability.block.Locks;

/**
 * Created by hoijima on 19.12.17.
 */
public interface LockInterface {
    int getComplex();

    void setComplex(int Complex);

    boolean getLockStatus();

    void setLockStatus(Boolean status);

}
