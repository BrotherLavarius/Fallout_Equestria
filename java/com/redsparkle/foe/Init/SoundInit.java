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


        tenmm_shot = registerSound("tenmm_shot");
        tenmm_dry = registerSound("tenmm_dry");
        tenmm_reload = registerSound("tenmm_reload");
        four_tenmm_shot = registerSound("four_tenmm_shot");
        four_tenmm_dry = registerSound("four_tenmm_dry");
        four_tenmm_reload = registerSound("four_tenmm_reload");
        db_shotgun_shot = registerSound("db_shotgun_shot");
        db_shotgun_reload = registerSound("db_shotgun_reload");
        db_shotgun_dry = registerSound("db_shotgun_dry");
        flaregun_shot = registerSound("flaregun_shot");
        flaregun_dry = registerSound("flaregun_dry");
        flaregun_reload = registerSound("flaregun_reload");
        plasma_shot = registerSound("plasma_shot");
        plasma_dry = registerSound("plasma_dry");
        plasma_reload = registerSound("plasma_reload");
        flamer_shot = registerSound("flamer_shot");
        flamer_reload = registerSound("flamer_reload");
        laser_fire_var_One = registerSound("laser_fire_var_One");
        laser_fire_var_Two = registerSound("laser_fire_var_Two");
        laser_fire_var_Tree = registerSound("laser_fire_var_Tree");
        laser_reload = registerSound("laser_reload");
        laser_dry = registerSound("laser_dry");
        clip_load = registerSound("clip_load");
        tenmm_clip_out = registerSound("tenmm_clip_out");
        four_tenmm_clip_out = registerSound("four_tenmm_clip_out");
        db_shotgun_clip_out = registerSound("db_shotgun_clip_out");
        flaregun_clip_out = registerSound("flaregun_clip_out");
        laser_clip_out = registerSound("laser_clip_out");
        plasma_clip_out = registerSound("plasma_clip_out");
    }

    /**
     * Register a {@link SoundEvent}.
     *
     * @param soundName The SoundEvent's name without the testmod3 prefix
     * @return The SoundEvent
     */
    private static SoundEvent registerSound(String soundName) {
        ResourceLocation soundID = new ResourceLocation(main.MODID, soundName);
        return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
    }



}
