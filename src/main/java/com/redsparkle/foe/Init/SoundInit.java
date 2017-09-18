package com.redsparkle.foe.Init;

import com.redsparkle.foe.main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoijima on 14.12.16.
 */
@SuppressWarnings("WeakerAccess")

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = main.MODID)
public class SoundInit {
    public static final SoundEvent lowentensity_rad     = registerSound("LowEntensityRad");
    public static final SoundEvent mediumentensity_rad  = registerSound("MediumEntensityRad");
    public static final SoundEvent highentensity_rad    = registerSound("HighEntensityRad");
    public static final SoundEvent enervation           = registerSound("Enervation");
    public static final SoundEvent tenmm_shot           = registerSound("tenmm_shot");
    public static final SoundEvent tenmm_dry            = registerSound("tenmm_dry");
    public static final SoundEvent tenmm_reload         = registerSound("tenmm_reload");
    public static final SoundEvent four_tenmm_shot      = registerSound("four_tenmm_shot");
    public static final SoundEvent four_tenmm_dry       = registerSound("four_tenmm_dry");
    public static final SoundEvent four_tenmm_reload    = registerSound("four_tenmm_reload");
    public static final SoundEvent db_shotgun_shot      = registerSound("db_shotgun_shot");
    public static final SoundEvent db_shotgun_reload    = registerSound("db_shotgun_reload");
    public static final SoundEvent db_shotgun_dry       = registerSound("db_shotgun_dry");
    public static final SoundEvent flaregun_shot        = registerSound("flaregun_shot");
    public static final SoundEvent flaregun_dry         = registerSound("flaregun_dry");
    public static final SoundEvent flaregun_reload      = registerSound("flaregun_reload");
    public static final SoundEvent plasma_shot          = registerSound("plasma_shot");
    public static final SoundEvent plasma_dry           = registerSound("plasma_dry");
    public static final SoundEvent plasma_reload        = registerSound("plasma_reload");
    public static final SoundEvent flamer_shot          = registerSound("flamer_shot");
    public static final SoundEvent flamer_reload        = registerSound("flamer_reload");
    public static final SoundEvent laser_shot = registerSound("laser_fire");
    public static final SoundEvent laser_reload         = registerSound("laser_reload");
    public static final SoundEvent laser_dry            = registerSound("laser_dry");
    public static final SoundEvent clip_load            = registerSound("clip_load");
    public static final SoundEvent tenmm_clip_out       = registerSound("tenmm_clip_out");
    public static final SoundEvent four_tenmm_clip_out  = registerSound("four_tenmm_clip_out");
    public static final SoundEvent db_shotgun_clip_out  = registerSound("db_shotgun_clip_out");
    public static final SoundEvent flaregun_clip_out    = registerSound("flaregun_clip_out");
    public static final SoundEvent laser_clip_out       = registerSound("laser_clip_out");
    public static final SoundEvent plasma_clip_out      = registerSound("plasma_clip_out");

    public static final Map<String, List<SoundEvent>> lookup = new HashMap<String, List<SoundEvent>>();
    public static List<SoundEvent> tenmm;
    public static List<SoundEvent> four_tenmm;
    public static List<SoundEvent> db_shotgun;
    public static List<SoundEvent> flaregun;
    public static List<SoundEvent> laser;
    public static List<SoundEvent> plasma;


    /**
     *
     * Register a {@link SoundEvent}.
     *
     * @param soundName The SoundEvent's name without the testmod3 prefix
     * @return The SoundEvent
     */
    @SideOnly(Side.CLIENT)
    private static SoundEvent registerSound(String soundName) {
        ResourceLocation soundID = new ResourceLocation(main.MODID, soundName);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }

    @SubscribeEvent
    public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
        final IForgeRegistry<SoundEvent> registry = event.getRegistry();

        final SoundEvent[] soundEvents = {
                lowentensity_rad,
                mediumentensity_rad,
                highentensity_rad,
                enervation,
                tenmm_shot,
                tenmm_dry,
                tenmm_reload,
                four_tenmm_shot,
                four_tenmm_dry,
                four_tenmm_reload,
                db_shotgun_shot,
                db_shotgun_reload,
                db_shotgun_dry,
                flaregun_shot,
                flaregun_dry,
                flaregun_reload,
                plasma_shot,
                plasma_dry,
                plasma_reload,
                flamer_shot,
                flamer_reload,
                laser_shot,
                laser_reload,
                laser_dry,
                clip_load,
                tenmm_clip_out,
                four_tenmm_clip_out,
                db_shotgun_clip_out,
                flaregun_clip_out,
                laser_clip_out,
                plasma_clip_out
        };
        registry.registerAll(soundEvents);

        tenmm = new ArrayList<SoundEvent>();
        four_tenmm = new ArrayList<SoundEvent>();
        db_shotgun = new ArrayList<SoundEvent>();
        flaregun = new ArrayList<SoundEvent>();
        laser = new ArrayList<SoundEvent>();
        plasma = new ArrayList<SoundEvent>();

        tenmm.add(tenmm_shot);
        tenmm.add(tenmm_dry);
        tenmm.add(tenmm_reload);
        tenmm.add(tenmm_clip_out);

        four_tenmm.add(four_tenmm_shot);
        four_tenmm.add(four_tenmm_dry);
        four_tenmm.add(four_tenmm_reload);
        four_tenmm.add(four_tenmm_clip_out);

        db_shotgun.add(db_shotgun_shot);
        db_shotgun.add(db_shotgun_dry);
        db_shotgun.add(db_shotgun_reload);
        db_shotgun.add(db_shotgun_clip_out);

        flaregun.add(flaregun_shot);
        flaregun.add(flaregun_dry);
        flaregun.add(flaregun_reload);
        flaregun.add(flaregun_clip_out);

        laser.add(laser_shot);
        laser.add(laser_dry);
        laser.add(laser_reload);
        laser.add(laser_clip_out);

        plasma.add(plasma_shot);
        plasma.add(plasma_dry);
        plasma.add(plasma_reload);
        plasma.add(plasma_clip_out);

        lookup.put(GlobalsGunStats.TEN_MM.getGunName()          ,tenmm);
        lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), tenmm);
        lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), tenmm);

        lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName()     ,four_tenmm);

        lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), tenmm);
        lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName()     ,db_shotgun);
        lookup.put(GlobalsGunStats.FLARE_GUN.getGunName()       ,flaregun);

        lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName()    ,laser);
        lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), plasma);

        lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), tenmm);
        lookup.put(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName(), tenmm);






    }
}

