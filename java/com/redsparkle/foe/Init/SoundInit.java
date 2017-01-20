package com.redsparkle.foe.Init;

import com.redsparkle.foe.main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hoijima on 14.12.16.
 */
public class SoundInit {
    public static SoundEvent LowEntensityRad;
    public static SoundEvent MediumEntensityRad;
    public static SoundEvent HighEntensityRad;
    public static SoundEvent Enervation;
    public static SoundEvent tenMMShot;
    public static SoundEvent tenMMOOA;
    public static SoundEvent tenMMReload;
    public static SoundEvent tenMMClipLoad;
    public static SoundEvent tenMMClipIn;
    public static SoundEvent tenMMClipOut;

    public static SoundEvent laserPShot1;
    public static SoundEvent laserPShot2;
    public static SoundEvent laserPShot3;
    public static SoundEvent laserPBoltClose;
    public static SoundEvent laserPBoltOpen;
    public static SoundEvent laserPBoltMagIn;
    public static SoundEvent laserPEmpty;

    public static void registerSounds() {
        LowEntensityRad = registerSound("LowEntensityRad");
        MediumEntensityRad = registerSound("MediumEntensityRad");
        HighEntensityRad = registerSound("HighEntensityRad");
        Enervation = registerSound("Enervation");


        tenMMShot = registerSound("tenMMShot");
        tenMMReload = registerSound("tenMMReload");
        tenMMOOA = registerSound("tenMMOutOfAmmo");
        tenMMClipLoad = registerSound("tenMMClipLoad");
        tenMMClipIn = registerSound("tenMMClipIn");
        tenMMClipOut = registerSound("tenMMClipOut");

        laserPShot1 = registerSound("laserPShot1");
        laserPShot2 = registerSound("laserPShot2");
        laserPShot3 = registerSound("laserPShot3");
        laserPBoltClose = registerSound("laserPBoltClose");
        laserPBoltOpen = registerSound("laserPBoltOpen");
        laserPBoltMagIn = registerSound("laserPBoltMagIn");
        laserPEmpty = registerSound("laserPEmpty");


    }

    /**
     * Register a {@link SoundEvent}.
     *
     * @param soundName The SoundEvent's name without the testmod3 prefix
     * @return The SoundEvent
     */
    private static SoundEvent registerSound(String soundName) {
        final ResourceLocation soundID = new ResourceLocation(main.MODID, soundName);
        return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
    }
}
