package com.redsparkle.foe.items.guns;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.utils.GlobalWeaponsStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.FlareShell.FlareShell;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 24.06.17.
 */
public class FlareGun extends Item_Firearm {

    public boolean isGun;
    public int clipRounds = GlobalWeaponsStats.flaregun_Rounds;
    public EntityPlayer playerIn;
    public World worldIn;

    public FlareGun() {
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
        this.damage = GlobalWeaponsStats.flaregun_Damage;

        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() == (clipRounds)) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.guns[10], SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }
            } else {
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.guns[9], SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                if (worldIn.isRemote) {
                    flare(worldIn, playerIn);
                }
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }
        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.guns[9], SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (worldIn.isRemote) {
                flare(worldIn, playerIn);
            }

        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public boolean isAmmo(ItemStack stack) {

        return stack.getItem() instanceof FlareShell;
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Flare Gun");
        tooltip.add("Clip size: " + clipRounds);
        tooltip.add("Damage: " + GlobalWeaponsStats.flaregun_Damage);


    }

    public void flare(World worldIn, EntityPlayer playerIn) {
        EntityFlare flare = new EntityFlare(worldIn, playerIn);
        flare.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 3.0F);
        flare.setDamage(damage);
        worldIn.spawnEntity(flare);
    }
}

