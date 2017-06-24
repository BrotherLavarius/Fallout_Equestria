package com.redsparkle.foe.items.guns.inits;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.spreadPellet_shotgun.*;
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
    public EntityLaser laser;
    public EntityFlame flame;
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

    public Entity laser(World worldIn, EntityPlayer playerIn) {
        laser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 0.5F);
        laser.setEffect(effect);
        laser.setRenderYawOffset(5F);
        laser.setDamage(damage);
        return laser;
    }


    public Entity bullet(World worldIn, EntityPlayer playerIn) {
        bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 1.5F);
        bullet.setEffect(effect);
        bullet.setRenderYawOffset(5F);
        bullet.setDamage(damage);
        return bullet;
    }
    public Entity flame(World worldIn, EntityPlayer playerIn) {
        flame.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 3.0F);
        flame.setDamage(damage);
        return flame;
    }

    public Entity[] pellet(World worldIn, EntityPlayer playerIn) {
        Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn,playerIn),new Pellet_two(worldIn,playerIn),new Pellet_tree(worldIn,playerIn), new Pellet_four(worldIn,playerIn),new Pellet_five(worldIn,playerIn), new Pellet_six(worldIn,playerIn)};
        for(int i =0; i <=(pellets.length-1);i++){
            pellets[i].setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 15.5F);
            pellets[i].setRenderYawOffset(10F);
            pellets[i].setDamage(damage);
        }
        return pellets;
    }

    public boolean isAmmo(ItemStack stack) {

        return false;
    }

}
