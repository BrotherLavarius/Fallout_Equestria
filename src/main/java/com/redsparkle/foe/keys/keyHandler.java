package com.redsparkle.foe.keys;

import com.redsparkle.foe.main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by NENYN on 1/12/2017.
 */
public class keyHandler {
    public static KeyBinding reload,
            pipbuck,
            testButton,
            saddlebags,
            sbag_shooter,
            interaction_mode,
            reloadLSB,
            reloadRSB;

    public static String reloadS = "Reload the gun";
    public static String pipbuckS = "open PipBuckDummyOverlayItem GUI";
    public static String testButtonS = "Button for testing stuffs";

    public static String saddlebagsS = "Saddlebags Screen";

    public static String sbag_shooter_S = "Trigger for saddle guns";

    public static String interaction_mode_S = "Switch between interaction and gun fire";


    public static String reloadLSB_S = "Reload left Saddle gun";
    public static String reloadRSB_S = "Reload right Saddle gun";


    public static void register() {
        reload = new KeyBinding(reloadS, Keyboard.KEY_R, main.MODID);
        pipbuck = new KeyBinding(pipbuckS, Keyboard.KEY_TAB, main.MODID);
        testButton = new KeyBinding(testButtonS, Keyboard.KEY_END, main.MODID);
        saddlebags = new KeyBinding(saddlebagsS, Keyboard.KEY_P, main.MODID);

        sbag_shooter = new KeyBinding(sbag_shooter_S, Keyboard.KEY_B, main.MODID);

        interaction_mode = new KeyBinding(interaction_mode_S, Keyboard.KEY_V, main.MODID);

        reloadLSB = new KeyBinding(reloadLSB_S, Keyboard.KEY_COMMA, main.MODID);
        reloadRSB = new KeyBinding(reloadRSB_S, Keyboard.KEY_PERIOD, main.MODID);

        ClientRegistry.registerKeyBinding(reload);
        ClientRegistry.registerKeyBinding(pipbuck);
        ClientRegistry.registerKeyBinding(testButton);

        ClientRegistry.registerKeyBinding(reloadLSB);
        ClientRegistry.registerKeyBinding(reloadRSB);

        ClientRegistry.registerKeyBinding(saddlebags);

        ClientRegistry.registerKeyBinding(sbag_shooter);
        ClientRegistry.registerKeyBinding(interaction_mode);


    }


}
