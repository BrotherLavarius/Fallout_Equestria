package com.redsparkle.api.utils;

import com.redsparkle.foe.Init.ItemInit;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 08.06.17.
 */
public class GlobalItemModelsInitArray {

    public static final Item[] AllInit = new Item[]{

            //---------------------ARMOR--------------------------

            ItemInit.t60head,
            ItemInit.t60body,
            ItemInit.t50head,
            ItemInit.t50body,
            ItemInit.t40head,
            ItemInit.t40body,
            ItemInit.t60legs,
            ItemInit.t50legs,
            ItemInit.t40legs

            //---------------------PLACEHOLDER--------------------

    };
    public static final String[] ItemNames = new String[]{

            //---------------------ARMOR--------------------------

            GlobalNames.T60Head,
            GlobalNames.T60Body,
            GlobalNames.T50Head,
            GlobalNames.T50Body,
            GlobalNames.T40Head,
            GlobalNames.T40Body,
            GlobalNames.T60Legs,
            GlobalNames.T50Legs,
            GlobalNames.T40Legs

    };

}
