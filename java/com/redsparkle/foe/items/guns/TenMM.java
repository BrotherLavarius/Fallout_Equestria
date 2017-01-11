package com.redsparkle.foe.items.guns;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.TenMMClip;
import com.redsparkle.foe.items.guns.inits.EntityBullet;
import com.redsparkle.foe.utils.InventoryManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;

/**
 * Created by NENYN on 1/5/2017.
 */
public class TenMM extends Item {


    public Item ammoItem = ItemInit.tenMMClip;
    public int damage = 15;
    public int clipRounds = 13;
    public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public Class<? extends EntityBullet> bulletClass;


    public TenMM() {
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.bulletClass = EntityBullet.class;

    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);


        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() == 12) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMOOA, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                } else {
                    // ---------------_RELOAD CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMReload, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    findAmmo(playerIn).shrink(1);
                    playerIn.getHeldItem(hand).setItemDamage(0);
                    Item emptyclip = ItemInit.tenMMClip;
                    ItemStack emptyClipStack = new ItemStack(emptyclip);
                    emptyClipStack.setItemDamage(12);
                    playerIn.inventory.setInventorySlotContents(InventoryManager.FindEmpty(playerIn), emptyClipStack);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                }
            } else {
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMShot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                try {
                    Constructor<? extends EntityBullet> bulletConstructor = bulletClass.getConstructor(World.class, EntityLivingBase.class);
                    EntityBullet bullet = bulletConstructor.newInstance(worldIn, playerIn);
                    bullet.damage = this.damage;
                    worldIn.spawnEntity(bullet);
                    itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.tenMMShot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            try {
                Constructor<? extends EntityBullet> bulletConstructor = bulletClass.getConstructor(World.class, EntityLivingBase.class);
                EntityBullet bullet = bulletConstructor.newInstance(worldIn, playerIn);
                bullet.damage = this.damage;
                worldIn.spawnEntity(bullet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
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





