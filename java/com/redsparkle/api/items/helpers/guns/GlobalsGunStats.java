package com.redsparkle.api.items.helpers.guns;

/**
 * Created by hoijima on 25.07.17.
 */
public enum GlobalsGunStats {
    /**
     * 1.Damage (plain simple damage)
     * 2.Clipsize (goes like basic lvl of bullets you expect to fire + 3)
     * 3.NearEmpty
     * 4.Empty
     */


    TEN_MM(10, 10),
    FOUR_TEN_MM(7, 12),
    LASER_PISTOL(12, 30),
    FLARE_GUN(1, 1),
    DB_SHOUTGUN(1, 2);

    private int Damage;
    private int Clipsize;



    GlobalsGunStats(int Damage, int Clipsize) {
        this.Damage = Damage;
        this.Clipsize = Clipsize;

    }

    public int getDamage() {
        return Damage;
    }

    public int Clipsize() {
        return Clipsize;
    }


}
