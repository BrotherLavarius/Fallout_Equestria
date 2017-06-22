package com.redsparkle.foe.items.guns;

import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.inits.EntityBullet;
import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by hoijima on 20.01.17.
 */
public class LaserPistol extends ItemFirearm {

    public boolean isGun;
    public int clipRounds = 31;


    public LaserPistol() {
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        isGun = true;

    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        if (nbt == null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setBoolean("isgun", isGun);
            stack.setTagCompound(tag);
        }

        return super.initCapabilities(stack, nbt);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        this.bullet = new EntityBullet(worldIn, playerIn);
        this.effect = EnumParticleTypes.FIREWORKS_SPARK;

        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= 30) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPEmpty, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }

            } else {

                int num = (int) Math.random() * 100 % 3;
                switch (num) {
                    case 0:
                        worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot1, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                        break;
                    case 1:
                        worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot2, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                        break;
                    case 2:
                        worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot3, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                        break;
                }


                worldIn.spawnEntity(bullet(worldIn, playerIn));
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                playerIn.cameraYaw = -0.1F;
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot3, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            worldIn.spawnEntity(bullet(worldIn, playerIn));
        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }


    @Override
    public boolean isAmmo(ItemStack stack) {

        return stack.getItem() instanceof Battery;
    }


}
