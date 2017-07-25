package com.redsparkle.foe.items.guns.ammo.TenMM;

import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.api.items.helpers.guns.ItemClipHelpers;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMClip extends Item {

    public Item ammo = GlobalItemArray_For_init.tenMMAmmo;
    public int MaxDamage = GlobalsGunStats.TEN_MM.Clipsize()+1;

    public TenMMClip() {
        final int NUMBER_OF_BOXES = 1;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);

    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Ammo clip for TenMM pistol");
        tooltip.add("AMMO LEFT:" + (stack.getMaxDamage() - stack.getItemDamage() + "/" + stack.getMaxDamage()));
    }

    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {

        worldIn.setActiveHand(playerIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        ItemStack Istack = ItemClipHelpers.TenMMClipStackHelper(stack, worldIn, playerIn, MaxDamage);
        return Istack;

    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 5;
    }

}
