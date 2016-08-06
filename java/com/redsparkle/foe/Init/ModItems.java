package com.redsparkle.foe.Init;

import com.redsparkle.foe.items.meds.RadAway;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hoijima desu on 06.08.16 desu.
 */
public class ModItems {
    public static final Set<Item> items = new HashSet<>();
    public static RadAway radAway;


    public static void registerItems() {
    radAway = registerItem(new RadAway());
    }
    private static <T extends Item> T registerItem(T item) {
        GameRegistry.register(item);
        items.add(item);

        return item;
    }
}
