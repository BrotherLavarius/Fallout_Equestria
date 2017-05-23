package com.redsparkle.foe.keys;

import com.redsparkle.foe.main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by NENYN on 1/12/2017.
 */
public class keyHandler {
    public static KeyBinding reload;
    public static KeyBinding pipbuck;
    public static KeyBinding testButton;
    public static String reloadS = "Reload the gun";
    public static String pipbuckS = "open PipBuckDummyOverlayItem GUI";
    public static String testButtonS = "Button for testing stuffs";

    public static void register() {
        reload = new KeyBinding(reloadS, Keyboard.KEY_R, main.MODID);
        pipbuck = new KeyBinding(pipbuckS, Keyboard.KEY_TAB, main.MODID);
        testButton = new KeyBinding(testButtonS, Keyboard.KEY_END, main.MODID);

        ClientRegistry.registerKeyBinding(reload);
        ClientRegistry.registerKeyBinding(pipbuck);
        ClientRegistry.registerKeyBinding(testButton);

    }
}
