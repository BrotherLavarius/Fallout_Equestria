package com.redsparkle.api.items.helpers.food;

import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.FoeItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
/**
 * Created by hoijima on 09.06.17.
 */
public abstract class FoodMultipleUse extends FoeItem {
    public int foodLvl;
    public int NUMBER_OF_BOXES;
    public int MaxDamage;
    public int foodToAdd;
    public FoodMultipleUse(final String itemName) {
        super(itemName);
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_Food);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.foodLvl = foodToAdd;
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Food left:" + (stack.getMaxDamage() - stack.getItemDamage() + "/" + stack.getMaxDamage()));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        if (playerIn.getFoodStats().getFoodLevel() == 20) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }
        if (playerIn.isCreative()) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
    //TODO: fix food consumption algoritm, it is kinda trash
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer entityplayer = (EntityPlayer) entityLiving;
        if (!entityplayer.isCreative()) {
            if (entityLiving instanceof EntityPlayer) {
                worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            }
            if (stack.getCount() > 1) {
                ItemStack excessStack = new ItemStack(stack.getItem());
                excessStack.setCount(stack.getCount() - 1);
//            entityplayer.inventory.addItemStackToInventory(excessStack);
                entityplayer.inventory.setInventorySlotContents(InventoryManager.FindEmpty(entityplayer), excessStack);
                stack.setCount(1);
            }
            stack.setItemDamage(stack.getItemDamage() + 1);
            int food = entityplayer.getFoodStats().getFoodLevel();
            entityplayer.getFoodStats().setFoodLevel(food + foodToAdd);
            if (stack.getItemDamage() >= stack.getMaxDamage()) {
                Item air = Items.AIR;
                ItemStack airS = new ItemStack(air);
                return airS;
            } else {
                return stack;
            }
        }
        return stack;
    }
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }
}
