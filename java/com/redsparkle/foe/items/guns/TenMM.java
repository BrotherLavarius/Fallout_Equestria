package com.redsparkle.foe.items.guns;

import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.inits.EntityBullet;
import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import com.redsparkle.foe.utils.AmmunitionListing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by NENYN on 1/5/2017.
 */
public class TenMM extends ItemFirearm {


    public static Item ammoItem = AmmunitionListing.TenMMClip;
    public boolean isGun;
    public int damage = 15;
    public int clipRounds = 13;
    public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public EntityBullet entitybullet ;


    public SoundEvent outOfammo = SoundInit.tenMMOOA;
    public SoundEvent reload = SoundInit.tenMMReload;
    public SoundEvent shoot = SoundInit.tenMMShot;


    public TenMM() {
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


        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= 12) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMOOA, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }

            } else {

//                worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMShot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
//                BulletEntity entitybullet = new BulletEntity(worldIn, playerIn);
//                entitybullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5.5F, 1.0F);
//                worldIn.spawnEntity(entitybullet);
//                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
//                playerIn.cameraYaw = -0.1F;
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }


        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMShot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!worldIn.isRemote) {


//                ItemArrow itemarrow = (ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
//                EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, playerIn);
//                entityarrow.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 10F, 0F);
//                worldIn.spawnEntity(entityarrow);


                //EntitySnowball entitysnowball = new EntitySnowball(worldIn, playerIn);
                //entitysnowball.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                //worldIn.spawnEntity(entitysnowball);

                entitybullet = new EntityBullet(worldIn, playerIn);
                entitybullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 5.5F, 1.0F);

                worldIn.spawnEntity(entitybullet);
            }

        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
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
        return stack.getItem() instanceof TenMMClip;
    }


}





