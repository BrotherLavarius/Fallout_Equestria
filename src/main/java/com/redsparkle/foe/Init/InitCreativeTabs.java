package com.redsparkle.foe.Init;
import com.redsparkle.api.utils.GlobalNames;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
/**
 * Created by hoijima desu on 06.08.16 desu.
 */
public class InitCreativeTabs {
    public static final CreativeTabs Fallout_blocks = new CreativeTabs(GlobalNames.Creative_tab_Blocks) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Item.getItemFromBlock(Blocks.COAL_BLOCK));
        }
    };
    public static final CreativeTabs Fallout_Food = new CreativeTabs(GlobalNames.Creative_tab_Food) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.APPLE);
        }
    };
    public static final CreativeTabs Fallout_meds = new CreativeTabs(GlobalNames.Creative_tab_Meds) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.POTIONITEM);
        }
    };


    public static final CreativeTabs Fallout_scrap = new CreativeTabs(GlobalNames.Creative_tab_Scrap) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.STRING);
        }
    };

    public static final CreativeTabs Fallout_guns = new CreativeTabs(GlobalNames.Creative_tab_Guns) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.BOW);
        }
    };
    public static final CreativeTabs Fallout_ammo = new CreativeTabs(GlobalNames.Creative_tab_Ammo) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.ARROW);
        }
    };
    public static final CreativeTabs Fallout_stats_blocks = new CreativeTabs(GlobalNames.Creative_tab_EFFBlocks) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK));
        }
    };
    public static final CreativeTabs Fallout_armor = new CreativeTabs(GlobalNames.Creative_tab_Armor) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.ARMOR_STAND);
        }
    };
}
