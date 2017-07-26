package com.redsparkle.foe.PotionsAndEffects;

import net.minecraft.init.Bootstrap;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
/**
 * Created by hoijima on 01.03.17.
 */
public class ModEffects {
    public static final Potion BuckAddiction;
    public static final Potion JetAddiction;
    public static final Potion MentAddiction;
    public static final Potion Famished;
    public static final Potion Hungry;
    public static final Potion Peckish;
    public static final Potion Ravenous;
    public static final Potion Starving;
    public static final Potion Enervation;
    public static final Potion RadPoison;
    public static final Potion Fatigue;
    public static final Potion Infection;
    public static final Potion Insomnia;
    public static final Potion Lethargy;
    public static final Potion Parasites;
    public static final Potion Weakness;
    public static final Potion PostCryogenicSyndrome;
    public static final Potion Thirsty;
    public static final Potion Parched;
    public static final Potion Dehydrated;
    public static final Potion MildlyDehydrated;
    public static final Potion SeverelyDehydrated;
    public static final Potion LightOverWeight;
    public static final Potion MediumOverWeight;
    public static final Potion HeavyOverWeight;
    static {
        if (!Bootstrap.isRegistered()) {
            throw new RuntimeException("Accessed MobEffects before Bootstrap!");
        } else {
            BuckAddiction = getRegisteredMobEffect("BuckAddiction");
            JetAddiction = getRegisteredMobEffect("JetAddiction");
            MentAddiction = getRegisteredMobEffect("MentAddiction");
            Famished = getRegisteredMobEffect("Famished");
            Hungry = getRegisteredMobEffect("Hungry");
            Peckish = getRegisteredMobEffect("Peckish");
            Ravenous = getRegisteredMobEffect("Ravenous");
            Starving = getRegisteredMobEffect("Starving");
            Enervation = getRegisteredMobEffect("Enervation");
            RadPoison = getRegisteredMobEffect("RadPoison");
            Fatigue = getRegisteredMobEffect("Fatigue");
            Infection = getRegisteredMobEffect("Infection");
            Insomnia = getRegisteredMobEffect("Insomnia");
            Lethargy = getRegisteredMobEffect("Lethargy");
            Parasites = getRegisteredMobEffect("Parasites");
            Weakness = getRegisteredMobEffect("Weakness");
            PostCryogenicSyndrome = getRegisteredMobEffect("PostCryogenicSyndrome");
            Thirsty = getRegisteredMobEffect("Thirsty");
            Parched = getRegisteredMobEffect("Parched");
            Dehydrated = getRegisteredMobEffect("Dehydrated");
            MildlyDehydrated = getRegisteredMobEffect("MildlyDehydrated");
            SeverelyDehydrated = getRegisteredMobEffect("SeverelyDehydrated");
            LightOverWeight = getRegisteredMobEffect("LightOverWeight");
            MediumOverWeight = getRegisteredMobEffect("MediumOverWeight");
            HeavyOverWeight = getRegisteredMobEffect("HeavyOverWeight");
        }
    }
    @Nullable
    private static Potion getRegisteredMobEffect(String id) {
        Potion potion = Potion.REGISTRY.getObject(new ResourceLocation(id));
        if (potion == null) {
            throw new IllegalStateException("Invalid ModEffect requested: " + id);
        } else {
            return potion;
        }
    }
}
