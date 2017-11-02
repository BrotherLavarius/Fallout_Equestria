package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.foe.Init.GlobalsGunStats;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by NENYN on 1/21/2017.
 */
public abstract class Item_Firearm extends FoeItem {

    public int Damage;
    public int Clipsize;
    public Item ClipItem;
    public Item AmmoItem;
    public float velocity;
    public float YawOffset;
    public boolean autofireSupport;
    public String ProjectileType;

    public GlobalsGunStats params;

    public Item_Firearm(final String itemName, final GlobalsGunStats params, Item ClipItem, Item AmmoItem) {
        super(itemName);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(params.getClipsize());
        this.params = params;
        this.Damage = params.getDamage();
        this.Clipsize = params.getClipsize();
        this.ClipItem = ClipItem;
        this.AmmoItem = AmmoItem;
        this.velocity = params.getVelocity();
        this.YawOffset = params.getYawOffset();
        this.autofireSupport = params.getAutofireSupport();
        this.ProjectileType = params.getProjectileType();


    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        IGunInterface iammo;
        if (stack.hasCapability(GunFactoryProvider.GUN, null)) {
            iammo = stack.getCapability(GunFactoryProvider.GUN, null);
            if (iammo.getMaxAmmo() == 0) {
                iammo.setMaxAmmo(Clipsize);
            }
            stack.setItemDamage(iammo.getMaxAmmo() - iammo.getAmmo());
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return super.initCapabilities(stack, nbt);

    }

}

