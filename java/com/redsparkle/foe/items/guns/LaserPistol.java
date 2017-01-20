package com.redsparkle.foe.items.guns;

import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.EntityBullet;
import com.redsparkle.foe.items.guns.inits.laserFired.EntityLaser;
import com.redsparkle.foe.utils.AmmunitionListing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by hoijima on 20.01.17.
 */
public class LaserPistol extends Item{

    public static Item ammoItem = AmmunitionListing.Battery;
    public boolean isGun;
    public int clipRounds = 31;
    public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public Class<? extends EntityLaser> laserClass;



    public LaserPistol() {
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.laserClass = EntityLaser.class;
        isGun = true;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        if (nbt == null)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setBoolean("isgun", isGun);
            stack.setTagCompound(tag);
        }

        return super.initCapabilities(stack, nbt);
    }



    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);


        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= 30) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPEmpty, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }

            } else {

                int num = (int) Math.random()*100 %3;
                switch(num)
                {
                    case 0 : worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot1, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));break;
                    case 1 : worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot2, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));break;
                    case 2 : worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot3, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));break;
                }


                EntityLaser entitylaser = new EntityLaser(worldIn, playerIn);
                worldIn.spawnParticle(EnumParticleTypes.REDSTONE, playerIn.getPosition().getX(), playerIn.getPosition().getY(),playerIn.getPosition().getZ(), 0.0D, 0.0D, 0.0D, new int[5]);
                entitylaser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(entitylaser);
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }


        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot3, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            EntityLaser entitylaser = new EntityLaser(worldIn, playerIn);
            entitylaser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitylaser);

        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
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

    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof Battery;
    }

}
