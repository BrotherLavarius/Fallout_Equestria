package com.redsparkle.foe.items.guns;

import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.LaserFired.EntityLaser;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

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
        this.laser = new EntityLaser(worldIn, playerIn);
        this.damage = 20 + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getEnergyWeapons();

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


                worldIn.spawnEntity(laser(worldIn, playerIn));
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                playerIn.cameraYaw = -0.1F;
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.laserPShot3, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            worldIn.spawnEntity(laser(worldIn, playerIn));
        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }


    @Override
    public boolean isAmmo(ItemStack stack) {

        return stack.getItem() instanceof Battery;
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Laser pistol");
        tooltip.add("Clip size: " + (clipRounds-1));
        tooltip.add("Damage: " + damage);

    }

}
