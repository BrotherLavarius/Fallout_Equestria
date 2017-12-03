package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.foe.main;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by hoijima on 28.07.17.
 */
public class FoeItem extends Item {
    public FoeItem(final String itemName) {

        setItemName(this, itemName);
    }


    /**
     * Set the registry name of {@code item} to {@code itemName} and the unlocalised name to the full registry name.
     *
     * @param item     The item
     * @param itemName The item's name
     */
    public static void setItemName(final Item item, final String itemName) {
        item.setRegistryName(main.MODID, itemName);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }
}



