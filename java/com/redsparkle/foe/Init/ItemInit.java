package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.items.armor.powered.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hoijima on 14.12.16.
 */
public class ItemInit {

    //ARMOR
    public static t40head t40head;
    public static t40body t40body;
    public static t40legs t40legs;

    public static t50head t50head;
    public static t50body t50body;
    public static t50legs t50legs;

    public static t60head t60head;
    public static t60body t60body;
    public static t60legs t60legs;


    //public static ItemSimple itemSimple;  // this holds the unique instance of your block

    public static void preInitCommon() {
        // each instance of your item should have two names:
        // 1) a registry name that is used to uniquely identify this item.  Should be unique within your mod.  use lower case.
        // 2) an 'unlocalised name' that is used to retrieve the text name of your item in the player's language.  For example-
        //    the unlocalised name might be "water", which is printed on the user's screen as "Wasser" in German or
        //    "aqua" in Italian.
        //
        //    Multiple items can have the same unlocalised name - for example
        //  +----RegistryName-------+----UnlocalisedName----+
        //  |  burning_candle       +       candle          |
        //  |  extinguished_candle  +       candle          |
        //  +-----------------------+-----------------------+
        //
        //----------------------MEDS-----------------

        for (int i = 0; i <= (GlobalItemArray_For_init.obj.length - 1); i++) {
            GlobalItemArray_For_init.AllInit[i] = GlobalItemArray_For_init.obj[i];
            GlobalItemArray_For_init.AllInit[i].setUnlocalizedName(GlobalItemArray_For_init.ItemNames[i]);
            GlobalItemArray_For_init.AllInit[i].setRegistryName(GlobalItemArray_For_init.ItemNames[i]);
            GameRegistry.register(GlobalItemArray_For_init.AllInit[i]);
        }


        //----------------------ARMOR-------------------

        t40head = (t40head) (new t40head(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName(GlobalNames.T40Head));
        t40head.setRegistryName(GlobalNames.T40Head);
        GameRegistry.register(t40head);

        t40body = (t40body) (new t40body(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName(GlobalNames.T40Body));
        t40body.setRegistryName(GlobalNames.T40Body);
        GameRegistry.register(t40body);


        t50head = (t50head) (new t50head(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName(GlobalNames.T50Head));
        t50head.setRegistryName(GlobalNames.T50Head);
        GameRegistry.register(t50head);

        t50body = (t50body) (new t50body(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName(GlobalNames.T50Body));
        t50body.setRegistryName(GlobalNames.T50Body);
        GameRegistry.register(t50body);


        t60head = (t60head) (new t60head(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName(GlobalNames.T60Head));
        t60head.setRegistryName(GlobalNames.T60Head);
        GameRegistry.register(t60head);

        t60body = (t60body) (new t60body(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName(GlobalNames.T60Body));
        t60body.setRegistryName(GlobalNames.T60Body);
        GameRegistry.register(t60body);


        t40legs = (t40legs) (new t40legs(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName(GlobalNames.T40Legs));
        t40legs.setRegistryName(GlobalNames.T40Legs);
        GameRegistry.register(t40legs);

        t50legs = (t50legs) (new t50legs(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName(GlobalNames.T50Legs));
        t50legs.setRegistryName(GlobalNames.T50Legs);
        GameRegistry.register(t50legs);

        t60legs = (t60legs) (new t60legs(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName(GlobalNames.T60Legs));
        t60legs.setRegistryName(GlobalNames.T60Legs);
        GameRegistry.register(t60legs);

//        //----------------------PLACEHOLDER--------------------
//        itb = (Item_bullet) (new Item_bullet().setUnlocalizedName("bullet"));
//        itb.setRegistryName("bullet");
//        GameRegistry.register(itb);
    }

    public static void InitCommon() {
    }

    public static void postInitCommon() {
    }
}
