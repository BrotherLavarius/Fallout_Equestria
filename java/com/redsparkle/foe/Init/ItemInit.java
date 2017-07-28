package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.main;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import static com.redsparkle.api.utils.GlobalItemArray_For_init.*;

/**
 * Created by hoijima on 14.12.16.
 */
@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(main.MODID)
public class ItemInit {
    //ARMOR
    //public static ItemSimple itemSimple;  // this holds the unique instance of your block

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

    //----------------------ARMOR-------------------
    private static void rename_armor() {

        t40head.setRegistryName(GlobalNames.T40Head);
        t40body.setRegistryName(GlobalNames.T40Body);
        t40legs.setRegistryName(GlobalNames.T40Legs);
        t50head.setRegistryName(GlobalNames.T50Head);
        t50body.setRegistryName(GlobalNames.T50Body);
        t50legs.setRegistryName(GlobalNames.T50Legs);
        t60head.setRegistryName(GlobalNames.T60Head);
        t60body.setRegistryName(GlobalNames.T60Body);
        t60legs.setRegistryName(GlobalNames.T60Legs);
    }


    @Mod.EventBusSubscriber(modid = main.MODID)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    radx,
                    radAway,
                    redHealPotion,
                    whiteHealpotion,
                    purpleHealPotion,
                    darkHealPotion,
                    pipbuck,
                    lvlingCrystall,
                    aem,
                    potato_crisps,
                    blamko_macCheese,
                    dandy_boy_apples,
                    gumDrops,
                    sugar_bombs,
                    fancy_mare_snack_cakes,
                    tenMMAmmo,
                    tenMMClip,
                    fourTenMMammo,
                    fourTenMMClip,
                    battery,
                    sShell,
                    flare,
                    tenMMbulletCase,
                    fourTenMMbulletCase,
                    tenMM,
                    fourTenMM,
                    laserPistol,
                    sb_shoutgun,
                    flareGun,
                    itb,
                    t40head,
                    t40body,
                    t40legs,
                    t50head,
                    t50body,
                    t50legs,
                    t60head,
                    t60body,
                    t60legs
            };

            final IForgeRegistry<Item> registry = event.getRegistry();
            rename_armor();
            for (final Item item : items) {

                registry.register(item);
                ITEMS.add(item);
            }
        }
    }
}

