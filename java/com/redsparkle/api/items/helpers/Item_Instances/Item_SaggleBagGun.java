package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

/**
 * Created by hoijima on 27.06.17.
 */
public abstract class Item_SaggleBagGun extends Item_Firearm {
    public Item casing;
    public SoundEvent shot;
    public SoundEvent dry;
    public float cameraYaw;
    public SoundEvent shot_var1;
    public SoundEvent shot_var2;
    public SoundEvent shot_var3;
    public Object ammoItem;
    public int damage;
    public int BaseDamage;
    public int clipRounds;
    public EntityBullet bullet;
    public EnumParticleTypes effect;
    public Integer[] invArray = {8, 9, 10, 11};
    public String gunName;
    public String projectile;

    public Item_SaggleBagGun(String itemName) {
        super(itemName);
        this.clipRounds = 32;
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }

    @Override
    public ItemStack findAmmo(EntityPlayer player) {
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(i);
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getActiveItemStack());
    }


}
