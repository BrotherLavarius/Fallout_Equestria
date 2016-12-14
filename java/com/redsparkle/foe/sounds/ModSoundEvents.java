package com.redsparkle.foe.sounds;

/**
 * Created by hoijima desu on 04.08.16 desu.
 */
import com.redsparkle.foe.main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


/**
 * Registers this mod's {@link SoundEvent}s.
 *
 * @author Choonster
 */
public class ModSoundEvents {
    public static SoundEvent LowEntensityRad;
    public static SoundEvent MediumEntensityRad;
    public static SoundEvent HighEntensityRad;
    public  static SoundEvent Enervation;

    /**
     * Register the {@link SoundEvent}s.
     */
    public static void registerSounds() {
        LowEntensityRad = registerSound("LowEntensityRad");
        MediumEntensityRad = registerSound("MediumEntensityRad");
        HighEntensityRad = registerSound("HighEntensityRad");
        Enervation = registerSound("Enervation");
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