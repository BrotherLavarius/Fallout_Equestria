package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.InitCreativeTabs;
import com.redsparkle.foe.items.FoeItem;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;


/**
 * Created by hoijima on 27.06.17.
 */
public abstract class Item_SaggleBagGun extends FoeItem {
    public SoundEvent shot_var1;
    public SoundEvent shot_var2;
    public SoundEvent shot_var3;

    public int Damage;
    public int Clipsize;
    public Item ClipItem;
    public Item AmmoItem;
    public float velocity;
    public float YawOffset;
    public boolean autofireSupport;
    public String ProjectileType;

    public String Side;
    public GlobalsGunStats params;

    public Item_SaggleBagGun(final String itemName, String side, final GlobalsGunStats params, Item ClipItem, Item AmmoItem) {
        super(itemName);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
        this.params = params;
        this.Damage = params.getDamage();
        this.Clipsize = params.getClipsize();
        this.ClipItem = ClipItem;
        this.AmmoItem = AmmoItem;
        this.velocity = params.getVelocity();
        this.YawOffset = params.getYawOffset();
        this.autofireSupport = params.getAutofireSupport();
        this.ProjectileType = params.getProjectileType();

        this.Side = side;

    }
}
