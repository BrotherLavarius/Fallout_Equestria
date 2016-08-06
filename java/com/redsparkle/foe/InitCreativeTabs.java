package com.redsparkle.foe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by hoijima desu on 06.08.16 desu.
 */
public class InitCreativeTabs {
    public static final CreativeTabs Fallout_blocks = new CreativeTabs("FOE blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.COAL_BLOCK);
        }
        public int getItemIconDamage() {
            return 4;
        }
    };
    public static final CreativeTabs Fallout_meds = new CreativeTabs("FOE meds") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemById(Item.getIdFromItem(Items.POTIONITEM));
        }
        public int getItemIconDamage() {
            return 4;
        }
    };
    public static final CreativeTabs Fallout_guns = new CreativeTabs("FOE guns") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemById(Item.getIdFromItem(Items.BOW));
        }
        public int getItemIconDamage() {
            return 4;
        }
    };
    public static final CreativeTabs Fallout_ammo = new CreativeTabs("FOE ammo") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemById(Item.getIdFromItem(Items.ARROW));
        }
        public int getItemIconDamage() {
            return 4;
        }
    };
    public static final CreativeTabs Fallout_stats_blocks = new CreativeTabs("FOE status blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.REDSTONE_BLOCK);
        }
        public int getItemIconDamage() {
            return 4;
        }
    };
}
