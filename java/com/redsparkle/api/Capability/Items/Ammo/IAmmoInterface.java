package com.redsparkle.api.Capability.Items.Ammo;

/**
 * Created by hoijima on 24.07.17.
 */
public interface IAmmoInterface {

    int getMaxAmmo();

    void setMaxAmmo(int Ammo);

    int getAmmo();

    void setAmmo(int Ammo);

    void addAmmo(int Ammo);
}
