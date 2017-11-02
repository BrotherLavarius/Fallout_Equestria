package com.redsparkle.foe.Init;

import com.redsparkle.foe.main;
import com.redsparkle.foe.mobeffects.StaticPoison;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hoijima on 04.08.17.
 */
@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(main.MODID)
public class PotionInit {


    public static final StaticPoison STATICPOISON = new StaticPoison(true, 2, 2, 2, "staticpoison");

    @Mod.EventBusSubscriber(modid = main.MODID)
    public static class RegistrationHandler {
        /**
         * Register this mod's {@link Potion}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerPotions(final RegistryEvent.Register<Potion> event) {
            event.getRegistry().registerAll(
                    STATICPOISON
            );
        }
    }

}

