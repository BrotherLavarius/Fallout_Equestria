package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import net.minecraft.util.SoundEvent;

import java.util.List;

/**
 * Created by hoijima on 25.07.17.
 */
public enum GlobalsGunStats {
    /**
     * 1.Damage (plain simple damage)
     * 2.Clipsize (goes like basic lvl of bullets you expect to fire + 3)
     * 3.velocity
     * 4.YawOffset
     * 5.Support for auto fire
     * 6.Projectile type (bullet,pellet,flame,laser,plasma,flare)
     * 7.Load Type by (clip,ammo)
     * 8.Gun name
     */
    TEN_MM(10, 10, 4.5F, 0.7F, false, "firearm", "clip", GlobalNames.TenMM),
    TEN_MM_SUB(8, 30, 4.5F, 0.5F, true, "firearm", "clip", GlobalNames.TenMMSub),
    FOURFOUR_REVOLVER(25, 6, 4.5F, 1.5F, false, "firearm", "ammo", GlobalNames.fortyfour_revolver),
    PLASMA_PISTOL(14, 19, 4.5F, 0F, false, "plasma", "clip", GlobalNames.magic_plasma_pistol),
    FOUR_TEN_MM(17, 9, 4.5F, 0.9F, false, "firearm", "clip", GlobalNames.FourTenMM),
    LASER_PISTOL(8, 30, 4.5F, 0F, false, "laser", "clip", GlobalNames.LaserPistol),
    FLARE_GUN(1, 1, 4.5F, 0F, false, "flare", "ammo", GlobalNames.flare_gun),
    DB_SHOUTGUN(1, 2, 4.5F, 0F, false, "pellet", "ammo",  GlobalNames.db_shoutgun),
    SEVEN_MM_RIFLE(23, 25, 4.5F, 0F, false, "firearm", "clip", GlobalNames.Seven_mm_rifle);



    private int Damage;
    private int Clipsize;
    private float velocity;
    private float YawOffset;
    private boolean autofireSupport;
    private String ProjectileType;
    private String LoadType;
    private List<SoundEvent> sounds;
    private String GunName;

    GlobalsGunStats(int Damage, int Clipsize, float velocity, float yawOffset, boolean autofireSupport, String ProjectileType, String LoadType, String GunName) {
        this.Damage = Damage;
        this.Clipsize = Clipsize;
        this.velocity = velocity;
        this.YawOffset = yawOffset;
        this.autofireSupport = autofireSupport;
        this.ProjectileType = ProjectileType;
        this.LoadType = LoadType;
        this.GunName = GunName;
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

    public String getLoadType() {
        return LoadType;
    }

    public String getGunName() {
        return GunName;
    }

}
