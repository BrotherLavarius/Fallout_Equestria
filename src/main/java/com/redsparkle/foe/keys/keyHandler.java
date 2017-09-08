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
            fire_LSB,
            fire_RSB,
            reloadLSB,
            reloadRSB;

    public static String reloadS = "Reload the gun";
    public static String pipbuckS = "open PipBuckDummyOverlayItem GUI";
    public static String testButtonS = "Button for testing stuffs";

    public static String saddlebagsS = "Saddlebags Screen";

    public static String sbag_shooter_S = "Trigger for saddle guns";

    public static String shootLSB_S = "Shoot left Saddle gun";
    public static String shootRSB_S = "Shoot right Saddle gun";

    public static String reloadLSB_S = "Reload left Saddle gun";
    public static String reloadRSB_S = "Reload right Saddle gun";


    public static void register() {
        reload = new KeyBinding(reloadS, Keyboard.KEY_R, main.MODID);
        pipbuck = new KeyBinding(pipbuckS, Keyboard.KEY_TAB, main.MODID);
        testButton = new KeyBinding(testButtonS, Keyboard.KEY_END, main.MODID);
        saddlebags = new KeyBinding(saddlebagsS, Keyboard.KEY_P, main.MODID);

        sbag_shooter = new KeyBinding(sbag_shooter_S, Keyboard.KEY_B, main.MODID);

        fire_LSB = new KeyBinding(shootLSB_S, Keyboard.KEY_LBRACKET, main.MODID);
        fire_RSB = new KeyBinding(shootRSB_S, Keyboard.KEY_RBRACKET, main.MODID);

        reloadLSB = new KeyBinding(reloadLSB_S, Keyboard.KEY_COMMA, main.MODID);
        reloadRSB = new KeyBinding(reloadRSB_S, Keyboard.KEY_PERIOD, main.MODID);

        ClientRegistry.registerKeyBinding(reload);
        ClientRegistry.registerKeyBinding(pipbuck);
        ClientRegistry.registerKeyBinding(testButton);

        ClientRegistry.registerKeyBinding(fire_LSB);
        ClientRegistry.registerKeyBinding(fire_RSB);

        ClientRegistry.registerKeyBinding(reloadLSB);
        ClientRegistry.registerKeyBinding(reloadRSB);

        ClientRegistry.registerKeyBinding(saddlebags);

        ClientRegistry.registerKeyBinding(sbag_shooter);

    }


}
