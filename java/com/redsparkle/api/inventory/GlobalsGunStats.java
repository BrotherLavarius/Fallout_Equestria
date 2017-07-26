package com.redsparkle.api.inventory;

public enum GlobalsGunStats {
    /**
     * 1.Damage (plain simple damage)
     * 2.Clipsize (goes like basic lvl of bullets you expect to fire + 3)
     * 3.NearEmpty
     * 4.Empty
     */


    TEN_MM(10, 13, 11, 12),
    FOUR_TEN_MM(7, 15, 13, 14),
    LASER_PISTOL(12, 31, 29, 30),
    FLARE_GUN(1, 1, 1, 1),
    DB_SHOUTGUN(1, 2, 1, 2);

    private int Damage;
    private int Clipsize;
    private int NearEmpty;
    private int Empty;


    GlobalsGunStats(int Damage, int Clipsize, int NearEmpty, int Empty) {
        this.Damage = Damage;
        this.Clipsize = Clipsize;
        this.NearEmpty = NearEmpty;
        this.Empty = Empty;
    }

    public int getDamage() {
        return Damage;
    }

    public int Clipsize() {
        return Clipsize;
    }

    public int NearEmpty() {
        return NearEmpty;
    }

    public int Empty() {
        return Empty;
    }
}






