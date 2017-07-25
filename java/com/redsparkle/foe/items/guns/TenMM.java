package com.redsparkle.foe.items.guns;

import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
 * Created by NENYN on 1/5/2017.
 */
public class TenMM extends Item_Firearm {

    public boolean isGun;
    public int clipRounds = GlobalsGunStats.TEN_MM.Clipsize();
    public Item casing;

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

        this.shot = SoundInit.tenmm_shot;
        this.dry = SoundInit.tenmm_dry;
        ItemStack itemstack = playerIn.getHeldItem(hand);
        casing = GlobalItemArray_For_init.AllInit[28];
        ItemStack caseStack = new ItemStack(casing);

        this.damage = GlobalsGunStats.TEN_MM.getDamage() + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();

        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= GlobalsGunStats.TEN_MM.NearEmpty()) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), dry, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }
            } else {
                worldIn.playSound(playerIn, playerIn.getPosition(), shot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    bullet(worldIn, playerIn);
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                playerIn.cameraYaw = -0.1F;
                AddCase(playerIn, caseStack);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }
        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), shot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                bullet(worldIn, playerIn);
            AddCase(playerIn, caseStack);
            playerIn.cameraYaw = -0.1F;


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
        tooltip.add("10 MM pistol");
        tooltip.add("Clip size: " + (clipRounds - 3));
        tooltip.add("Base Damage: " + GlobalsGunStats.TEN_MM.getDamage());
        tooltip.add("Your Damage: " + damage);

    }

}





