package com.redsparkle.foe.Init;

import net.minecraft.item.Item;

/**
 * Created by hoijima on 25.07.17.
 */
public enum GlobalsGunStats {
    /**
     * 1.Damage (plain simple damage)
     * 2.Clipsize (goes like basic lvl of bullets you expect to fire + 3)
     * 3.ClipItem
     * 4.AmmoItem
     * 5.velocity
     * 6.YawOffset
     * 7.Support for auto fire
     * 8.Projectile type
     */
    TEN_MM(10, 10, 4.5F, 0F, false, "firearm"),
    FOUR_TEN_MM(7, 12, 4.5F, 0F, false, "firearm"),
    LASER_PISTOL(12, 30, 4.5F, 0F, false, "laser"),
    FLARE_GUN(1, 1, 4.5F, 0F, false, "flare"),
    DB_SHOUTGUN(1, 2, 4.5F, 0F, false, "pellet"),
    SEVEN_MM_RIFLE(14, 45, 4.5F, 0F, false, "firearm");


    private int Damage;
    private int Clipsize;
    private Item ClipItem;
    private Item AmmoItem;
    private float velocity;
    private float YawOffset;
    private boolean autofireSupport;
    private String ProjectileType;

    GlobalsGunStats(int Damage, int Clipsize, float velocity, float yawOffset, boolean autofireSupport, String ProjectileType) {
        this.Damage = Damage;
        this.Clipsize = Clipsize;
        this.velocity = velocity;
        this.YawOffset = yawOffset;
        this.autofireSupport = autofireSupport;
        this.ProjectileType = ProjectileType;
    }

    public int getDamage() {
        return Damage;
    }

    public int getClipsize() {
        return Clipsize;
    }

    public float getVelocity() {
        return velocity;
    }

    public float getYawOffset() {
        return YawOffset;
    }

    public boolean getAutofireSupport() {
        return autofireSupport;
    }

    public String getProjectileType() {
        return ProjectileType;
    }


}
