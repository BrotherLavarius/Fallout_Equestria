package com.redsparkle.foe.items.guns.ammo.FourTenMM;

import com.redsparkle.api.inventory.GlobalsGunStats;
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
public class FourTenMMClip extends Item {

    public Item ammo = GlobalItemArray_For_init.tenMMAmmo;
    public int MaxDamage = GlobalsGunStats.FOUR_TEN_MM.NearEmpty()+1;
    public FourTenMMClip() {
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
        tooltip.add("AMMO LEFT:" + (stack.getMaxDamage() - (stack.getItemDamage() + 1)) + "/" + (stack.getMaxDamage() - 2));
    }

    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {

        worldIn.setActiveHand(playerIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        ItemStack Istack = ItemClipHelpers.FourTenMMClipStackHelper(stack, worldIn, playerIn, MaxDamage);
        return Istack;

    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 5;
    }

}
