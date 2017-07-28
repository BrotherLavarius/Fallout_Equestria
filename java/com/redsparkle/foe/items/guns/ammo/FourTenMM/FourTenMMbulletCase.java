package com.redsparkle.foe.items.guns.ammo.FourTenMM;

import com.redsparkle.foe.items.FoeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
/**
 * Created by NENYN on 1/12/2017.
 */
public class FourTenMMbulletCase extends FoeItem {
    public FourTenMMbulletCase(String fourTenMMCase) {

        super(fourTenMMCase);
        this.setMaxStackSize(64);
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Case from a 14mm bullet");
    }
}
