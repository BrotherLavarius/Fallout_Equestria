package com.redsparkle.foe.items.guns.bullets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 30.05.17.
 */
public class TenMMbullet extends Item {
    public TenMMbullet() {
        this.setMaxStackSize(1);
    }


    //public EntityProjectile createBullet(World worldIn, ItemStack stack, EntityLivingBase shooter)
    //{
    //   EntityProjectile entityProjectile = new EntityBullet(worldIn, shooter);
    //    return entityProjectile;
    //}

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("place holder bullet");
    }
}
