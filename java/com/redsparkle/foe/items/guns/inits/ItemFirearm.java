package com.redsparkle.foe.items.guns.inits;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by NENYN on 1/21/2017.
 */
public abstract class ItemFirearm extends Item {

    public Object ammoItem;
    public int damage;
    public int clipRounds;
    public EntityBullet bullet;
    public EnumParticleTypes effect;
    public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    public ItemFirearm() {
        this.clipRounds = 32;
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }

    public ItemStack findAmmo(EntityPlayer player) {
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (this.isAmmo(itemstack)) {
                if (itemstack.getItemDamage() >= 12) {
                    return ItemStack.EMPTY;
                } else {
                    return itemstack;
                }
            }
            // }
        }
        return ItemStack.EMPTY;
    }

    public Entity bullet(World worldIn, EntityPlayer playerIn) {
        bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5.5F, 1.0F);
        bullet.setEffect(effect);
        bullet.setRenderYawOffset(5F);
        bullet.setDamage(damage);
        return bullet;
    }


    public boolean isAmmo(ItemStack stack) {

        return false;
    }

}
