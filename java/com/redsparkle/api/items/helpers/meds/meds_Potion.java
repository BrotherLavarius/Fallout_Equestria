package com.redsparkle.api.items.helpers.meds;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 17.06.17.
 */
public abstract class meds_Potion extends Item {
    public int NUMBER_OF_BOXES;
    public int MaxDamage;
    public float HealMomentln;
    public float HealMoment;
    public String potionId;
    public int durationIn;
    public int amplifierIn;
    public String potion;
    public int duration;
    public int amplifier;


    public meds_Potion() {

        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.potion = potionId;
        this.duration = durationIn;
        this.amplifier = amplifierIn;
        this.HealMoment = HealMomentln;
    }


    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("");
    }

    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {
        worldIn.setActiveHand(playerIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer entityplayer = (EntityPlayer) entityLiving;
        if (entityLiving instanceof EntityPlayer) {
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        }
        if (stack.getCount() > 1) {
            ItemStack excessStack = new ItemStack(stack.getItem());
            excessStack.setCount(stack.getCount() - 1);
            entityplayer.inventory.addItemStackToInventory(excessStack);
            stack.setCount(1);
        }
        entityplayer.heal(this.HealMoment);
        entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(this.potion), this.duration, this.amplifier));
        Item air = Items.AIR;
        ItemStack airS = new ItemStack(air);
        return airS;


    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }


}

