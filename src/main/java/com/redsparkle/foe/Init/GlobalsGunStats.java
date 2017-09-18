package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import net.minecraft.util.SoundEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 9. Basic inaccuracy - keep in mind this param will be divided by the skill amount
     * 10. BPS
     */
    TEN_MM(10, 10, 4.5F, 0.7F, false, "firearm", "clip", GlobalNames.TenMM, 15F, 0),
    TEN_MM_SUB(8, 30, 4.5F, 0.5F, true, "firearm", "clip", GlobalNames.TenMMSub, 15F, 130),
    FOURFOUR_REVOLVER(25, 6, 4.5F, 1.5F, false, "firearm", "ammo", GlobalNames.fortyfour_revolver, 15F, 0),
    PLASMA_PISTOL(14, 19, 4.5F, 0F, false, "plasma", "clip", GlobalNames.magic_plasma_pistol, 5F, 0),
    FOUR_TEN_MM(17, 9, 4.5F, 0.9F, false, "firearm", "clip", GlobalNames.FourTenMM, 15F, 0),
    LASER_PISTOL(8, 30, 4.5F, 0F, false, "laser", "clip", GlobalNames.LaserPistol, 15F, 0),
    FLARE_GUN(1, 1, 4.5F, 0F, false, "flare", "ammo", GlobalNames.flare_gun, 15F, 0),
    DB_SHOUTGUN(1, 2, 4.5F, 0F, false, "pellet", "ammo", GlobalNames.db_shoutgun, 150F, 0),
    SEVEN_MM_RIFLE(23, 25, 4.5F, 0F, false, "firearm", "clip", GlobalNames.Seven_mm_rifle, 15F, 0),
    FIVE_MM_MINIGUN(9, 200, 4.5F, 0F, true, "firearm", "clip", GlobalNames.Five_mm_minigun, 15F, 20);



    // Reverse-lookup map for getting a day from an abbreviation
    public static final Map<String, GlobalsGunStats> lookup = new HashMap<String, GlobalsGunStats>();

    static {
        for (GlobalsGunStats d : GlobalsGunStats.values()) {
            lookup.put(d.getGunName(), d);
        }
    }

    private int Damage;
    private int Clipsize;
    private float velocity;
    private float YawOffset;
    private boolean autofireSupport;
    private String ProjectileType;
    private String LoadType;
    private List<SoundEvent> sounds;
    private String GunName;
    private float inaccuracy;
    private int bps;

    GlobalsGunStats(int Damage, int Clipsize, float velocity, float yawOffset, boolean autofireSupport, String ProjectileType, String LoadType, String GunName, float inaccuracy, int bps) {
        this.Damage = Damage;
        this.Clipsize = Clipsize;
        this.velocity = velocity;
        this.YawOffset = yawOffset;
        this.autofireSupport = autofireSupport;
        this.ProjectileType = ProjectileType;
        this.LoadType = LoadType;
        this.GunName = GunName;
        this.inaccuracy = inaccuracy;
        this.bps = bps;
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

    public float getInaccuracy() {
        return inaccuracy;
    }

    public int getBps() {
        return bps;
    }
}
