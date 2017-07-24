package com.redsparkle.foe.items.guns;

import com.redsparkle.api.capa.skills.SkillsFactoryProvider;
import com.redsparkle.api.inventory.GlobalsGunStats;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
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
public class SB_shoutgun extends Item_Firearm {

    public boolean isGun;
    public int clipRounds = GlobalsGunStats.DB_SHOUTGUN.Clipsize();


    public SB_shoutgun() {
        this.setMaxStackSize(1);
        this.setMaxDamage(2);
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
        this.shot = SoundInit.db_shotgun_shot;
        this.dry = SoundInit.db_shotgun_dry;

        ItemStack itemstack = playerIn.getHeldItem(hand);
        this.damage = GlobalsGunStats.DB_SHOUTGUN.getDamage() + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= GlobalsGunStats.DB_SHOUTGUN.Empty()) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), dry, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }
            } else {
                worldIn.playSound(playerIn, playerIn.getPosition(), shot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

                pellet(worldIn, playerIn);
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                playerIn.cameraYaw = 3.9F;
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }


        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), shot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            pellet(worldIn, playerIn);

            playerIn.cameraYaw = 2.9F;
        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }


    @Override
    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof TenMMClip;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Shotgun");
        tooltip.add("Clip size: " + clipRounds);
        tooltip.add("Base Damage: " + GlobalsGunStats.DB_SHOUTGUN.getDamage() + "*6 / " + (GlobalsGunStats.DB_SHOUTGUN.getDamage() * 6));
        tooltip.add("Your Damage: " + damage + "*6 / " + (damage * 6));

    }

}

