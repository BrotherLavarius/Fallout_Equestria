package com.redsparkle.api.Capability.Items.Gun;

/**
 * Created by hoijima on 25.07.17.
 */
public interface IGunInterface {

    void addAmmo(int ammo);

    void removeAmmo(int ammo);

    int getAmmo();

    void setAmmo(int ammo);

    int getMaxAmmo();

    void setMaxAmmo(int ammo);

    void setClipStatus(boolean clipInside);

    boolean clipInserted();



}
