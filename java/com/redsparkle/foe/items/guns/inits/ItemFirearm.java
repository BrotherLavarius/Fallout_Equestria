package com.redsparkle.foe.items.guns.inits;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import com.redsparkle.foe.utils.InventoryManager;
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

    public void laser(World worldIn, EntityPlayer playerIn) {
        EntityLaser laser = new EntityLaser(worldIn, playerIn);
        laser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 0.5F);
        laser.setEffect(effect);
        laser.setRenderYawOffset(5F);
        laser.setDamage(damage);
        worldIn.spawnEntity(laser);
    }


    public void bullet(World worldIn, EntityPlayer playerIn) {
        EntityBullet bullet = new EntityBullet(worldIn, playerIn);
        bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 1.5F);
        bullet.setEffect(effect);
        bullet.setRenderYawOffset(5F);
        bullet.setDamage(damage);
        worldIn.spawnEntity(bullet);
    }
    public Entity flame(World worldIn, EntityPlayer playerIn) {
        EntityFlame flame = new EntityFlame(worldIn,playerIn);
        flame.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 3.0F);
        flame.setDamage(damage);
        return flame;
    }

    public void pellet(World worldIn, EntityPlayer playerIn) {
        Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn,playerIn),new Pellet_two(worldIn,playerIn),new Pellet_tree(worldIn,playerIn), new Pellet_four(worldIn,playerIn),new Pellet_five(worldIn,playerIn), new Pellet_six(worldIn,playerIn)};
        for(int i =0; i <=(pellets.length-1);i++){
            pellets[i].setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 15.5F);
            pellets[i].setRenderYawOffset(10F);
            pellets[i].setDamage(damage);
            worldIn.spawnEntity(pellets[i]);
        }
    }


    public boolean isAmmo(ItemStack stack) {

        return false;
    }

    public void AddCase(EntityPlayer playerIn, ItemStack casing) {
        if (InventoryManager.AddItemToExistingStack(playerIn, casing) != ItemStack.EMPTY) {
            InventoryManager.AddItemToExistingStack(playerIn, casing).grow(1);
        } else {
            playerIn.inventory.addItemStackToInventory(casing);
        }

    }
}
