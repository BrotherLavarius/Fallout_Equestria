package com.redsparkle.foe;

/**
 * Created by hoijima desu on 04.08.16 desu.
 */
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


/**
 * Registers this mod's {@link SoundEvent}s.
 *
 * @author Choonster
 */
@SuppressWarnings("WeakerAccess")
public class ModSoundEvents {
    public static SoundEvent RadMeter;
    public  static SoundEvent Enervation;

    /**
     * Register the {@link SoundEvent}s.
     */
    public static void registerSounds() {
        RadMeter = registerSound("RadMeter");
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