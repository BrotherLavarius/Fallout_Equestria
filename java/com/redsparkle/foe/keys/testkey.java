package com.redsparkle.foe.keys;

import com.redsparkle.foe.main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by NENYN on 1/12/2017.
 */
public class testkey {
    public static KeyBinding reload ;
    public static String reloadS = "Hello USERNAME";

    public static void register()
    {
        reload = new KeyBinding(reloadS, Keyboard.KEY_R, main.MODID);

        ClientRegistry.registerKeyBinding(reload);
    }
}
