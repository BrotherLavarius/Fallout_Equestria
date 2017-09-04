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
    public static KeyBinding saddlebags;

    public static KeyBinding sbag_shooter;
    public static KeyBinding shootLSB;
    public static KeyBinding shootRSB;

    public static KeyBinding reloadLSB;
    public static KeyBinding reloadRSB;

    public static String reloadS = "Reload the gun";
    public static String pipbuckS = "open PipBuckDummyOverlayItem GUI";
    public static String testButtonS = "Button for testing stuffs";

    public static String saddlebagsS = "Saddlebags Screen";

    public static String sbag_shooter_S = "Trigger for saddle guns";

    public static String shootLSB_S = "Shoot left Saddle gun";
    public static String shootRSB_S = "Shoot right Saddle gun";

    public static String reloadLSB_S = "Shoot left Saddle gun";
    public static String reloadRSB_S = "Shoot right Saddle gun";


    public static void register() {
        reload = new KeyBinding(reloadS, Keyboard.KEY_R, main.MODID);
        pipbuck = new KeyBinding(pipbuckS, Keyboard.KEY_TAB, main.MODID);
        testButton = new KeyBinding(testButtonS, Keyboard.KEY_END, main.MODID);
        saddlebags = new KeyBinding(saddlebagsS, Keyboard.KEY_P, main.MODID);

        sbag_shooter = new KeyBinding(sbag_shooter_S, Keyboard.KEY_B, main.MODID);

        shootLSB = new KeyBinding(shootLSB_S, Keyboard.KEY_J, main.MODID);
        shootRSB = new KeyBinding(shootRSB_S, Keyboard.KEY_K, main.MODID);

        reloadLSB = new KeyBinding(reloadLSB_S, Keyboard.KEY_N, main.MODID);
        reloadRSB = new KeyBinding(reloadRSB_S, Keyboard.KEY_M, main.MODID);

        ClientRegistry.registerKeyBinding(reload);
        ClientRegistry.registerKeyBinding(pipbuck);
        ClientRegistry.registerKeyBinding(testButton);
        ClientRegistry.registerKeyBinding(saddlebags);

        ClientRegistry.registerKeyBinding(sbag_shooter);

        ClientRegistry.registerKeyBinding(shootLSB);
        ClientRegistry.registerKeyBinding(shootRSB);
    }
}
