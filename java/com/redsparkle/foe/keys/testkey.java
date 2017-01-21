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
    public static KeyBinding ponymodel;
    public static String reloadS = "Reload the gun";

    public static void register()
    {
        reload = new KeyBinding(reloadS, Keyboard.KEY_R, main.MODID);

        ponymodel = new KeyBinding("Changethemode",Keyboard.KEY_P,main.MODID);


        ClientRegistry.registerKeyBinding(ponymodel);
        ClientRegistry.registerKeyBinding(reload);
    }
}
