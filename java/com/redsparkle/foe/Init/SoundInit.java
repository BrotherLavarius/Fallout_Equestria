package com.redsparkle.foe.Init;

import com.redsparkle.foe.main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hoijima on 14.12.16.
 */
public class SoundInit {
    public static SoundEvent lowentensity_rad;
    public static SoundEvent mediumentensity_rad;
    public static SoundEvent highentensity_rad;
    public static SoundEvent enervation;


    public static SoundEvent tenmm_shot;
    public static SoundEvent tenmm_dry;
    public static SoundEvent tenmm_reload;

    public static SoundEvent four_tenmm_shot;
    public static SoundEvent four_tenmm_dry;
    public static SoundEvent four_tenmm_reload;

    public static SoundEvent db_shotgun_shot;
    public static SoundEvent db_shotgun_reload;
    public static SoundEvent db_shotgun_dry;

    public static SoundEvent flaregun_shot;
    public static SoundEvent flaregun_dry;
    public static SoundEvent flaregun_reload;

    public static SoundEvent plasma_shot;
    public static SoundEvent plasma_dry;
    public static SoundEvent plasma_reload;

    public static SoundEvent flamer_shot;
    public static SoundEvent flamer_reload;

    public static SoundEvent laser_fire_var_One;
    public static SoundEvent laser_fire_var_Two;
    public static SoundEvent laser_fire_var_Tree;
    public static SoundEvent laser_reload;
    public static SoundEvent laser_dry;

    public static SoundEvent clip_load;

    public static SoundEvent tenmm_clip_out;
    public static SoundEvent four_tenmm_clip_out;
    public static SoundEvent db_shotgun_clip_out;
    public static SoundEvent flaregun_clip_out;
    public static SoundEvent laser_clip_out;
    public static SoundEvent plasma_clip_out;


//    public static SoundEvent[] guns = {
//            tenmm_shot,                         //	0
//            tenmm_dry,                          //	1
//            tenmm_reload,                       //	2
//            four_tenmm_shot,                  //	3
//            four_tenmm_dry,                  //	4
//            four_tenmm_reload,                  //	5
//            db_shotgun_shot,                  //	6
//            db_shotgun_reload,                  //	7
//            db_shotgun_dry,                  //	8
//            flaregun_shot,                  //	9
//            flaregun_dry,                       //	10
//            flaregun_reload,                    //	11
//            plasma_shot,                        //	12
//            plasma_dry,                         //	13
//            plasma_reload,                      //	14
//            flamer_shot,                        //	15
//            flamer_reload,                      //	16
//            laser_fire_var_One,                 //	17
//            laser_fire_var_Two,                 //	18
//            laser_fire_var_Tree,                //	19
//            laser_reload,                       //	20
//            laser_dry,                          //	21
//            clip_load,                          //  22
//
//            tenmm_clip_out,                     //  23
//            four_tenmm_clip_out,                //  24
//            db_shotgun_clip_out,                //  25
//            flaregun_clip_out,                  //  26
//            laser_clip_out,                     //  27
//            plasma_clip_out                     //  28
//    };

    private static int size = 0;
    public static void registerSounds() {


        lowentensity_rad = registerSound("LowEntensityRad");
        mediumentensity_rad = registerSound("MediumEntensityRad");
        highentensity_rad = registerSound("HighEntensityRad");
        enervation = registerSound("Enervation");


        tenmm_shot = new SoundEvent(new ResourceLocation(main.MODID, "tenmm_shot"));
        tenmm_dry = new SoundEvent(new ResourceLocation(main.MODID, "tenmm_dry"));
        tenmm_reload = new SoundEvent(new ResourceLocation(main.MODID, "tenmm_reload"));
        four_tenmm_shot = new SoundEvent(new ResourceLocation(main.MODID, "four_tenmm_shot"));
        four_tenmm_dry = new SoundEvent(new ResourceLocation(main.MODID, "four_tenmm_dry"));
        four_tenmm_reload = new SoundEvent(new ResourceLocation(main.MODID, "four_tenmm_reload"));
        db_shotgun_shot = new SoundEvent(new ResourceLocation(main.MODID, "db_shotgun_shot"));
        db_shotgun_reload = new SoundEvent(new ResourceLocation(main.MODID, "db_shotgun_reload"));
        db_shotgun_dry = new SoundEvent(new ResourceLocation(main.MODID, "db_shotgun_dry"));
        flaregun_shot = new SoundEvent(new ResourceLocation(main.MODID, "flaregun_shot"));
        flaregun_dry = new SoundEvent(new ResourceLocation(main.MODID, "flaregun_dry"));
        flaregun_reload = new SoundEvent(new ResourceLocation(main.MODID, "flaregun_reload"));
        plasma_shot = new SoundEvent(new ResourceLocation(main.MODID, "plasma_shot"));
        plasma_dry = new SoundEvent(new ResourceLocation(main.MODID, "plasma_dry"));
        plasma_reload = new SoundEvent(new ResourceLocation(main.MODID, "plasma_reload"));
        flamer_shot = new SoundEvent(new ResourceLocation(main.MODID, "flamer_shot"));
        flamer_reload = new SoundEvent(new ResourceLocation(main.MODID, "flamer_reload"));
        laser_fire_var_One = new SoundEvent(new ResourceLocation(main.MODID, "laser_fire_var_One"));
        laser_fire_var_Two = new SoundEvent(new ResourceLocation(main.MODID, "laser_fire_var_Two"));
        laser_fire_var_Tree = new SoundEvent(new ResourceLocation(main.MODID, "laser_fire_var_Tree"));
        laser_reload = new SoundEvent(new ResourceLocation(main.MODID, "laser_reload"));
        laser_dry = new SoundEvent(new ResourceLocation(main.MODID, "laser_dry"));
        clip_load = new SoundEvent(new ResourceLocation(main.MODID, "clip_load"));
        tenmm_clip_out = new SoundEvent(new ResourceLocation(main.MODID, "tenmm_clip_out"));
        four_tenmm_clip_out = new SoundEvent(new ResourceLocation(main.MODID, "four_tenmm_clip_out"));
        db_shotgun_clip_out = new SoundEvent(new ResourceLocation(main.MODID, "db_shotgun_clip_out"));
        flaregun_clip_out = new SoundEvent(new ResourceLocation(main.MODID, "flaregun_clip_out"));
        laser_clip_out = new SoundEvent(new ResourceLocation(main.MODID, "laser_clip_out"));
        plasma_clip_out = new SoundEvent(new ResourceLocation(main.MODID, "plasma_clip_out"));

        tenmm_shot= register(null, "tenmm_shot");
        tenmm_dry= register(null, "tenmm_dry");
        tenmm_reload= register(null, "tenmm_reload");
        four_tenmm_shot= register(null, "four_tenmm_shot");
        four_tenmm_dry= register(null, "four_tenmm_dry");
        four_tenmm_reload= register(null, "four_tenmm_reload");
        db_shotgun_shot= register(null, "db_shotgun_shot");
        db_shotgun_reload= register(null, "db_shotgun_reload");
        db_shotgun_dry= register(null, "db_shotgun_dry");
        flaregun_shot= register(null, "flaregun_shot");
        flaregun_dry= register(null, "flaregun_dry");
        flaregun_reload= register(null, "flaregun_reload");
        plasma_shot= register(null, "plasma_shot");
        plasma_dry= register(null, "plasma_dry");
        plasma_reload= register(null, "plasma_reload");
        flamer_shot= register(null, "flamer_shot");
        flamer_reload= register(null, "flamer_reload");
        laser_fire_var_One= register(null, "laser_fire_var_One");
        laser_fire_var_Two= register(null, "laser_fire_var_Two");
        laser_fire_var_Tree= register(null, "laser_fire_var_Tree");
        laser_reload= register(null, "laser_reload");
        laser_dry= register(null, "laser_dry");
        clip_load= register(null, "clip_load");
        tenmm_clip_out= register(null, "tenmm_clip_out");
        four_tenmm_clip_out= register(null, "four_tenmm_clip_out");
        db_shotgun_clip_out= register(null, "db_shotgun_clip_out");
        flaregun_clip_out= register(null, "flaregun_clip_out");
        laser_clip_out= register(null, "laser_clip_out");
        plasma_clip_out= register(null, "plasma_clip_out");
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

    public static SoundEvent register(String folder, String name) {
        ResourceLocation location = new ResourceLocation(main.MODID + ":" + name);
        SoundEvent event = new SoundEvent(location);

        SoundEvent.REGISTRY.register(size, location, event);
        size++;
        return event;
    }



}
