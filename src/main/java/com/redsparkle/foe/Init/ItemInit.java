package com.redsparkle.foe.Init;

import com.redsparkle.api.items.helpers.Item_Instances.FoeScrap;
import com.redsparkle.foe.main;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.redsparkle.foe.Init.ModItems.*;


/**
 * Created by hoijima on 14.12.16.
 */
@Mod.EventBusSubscriber(modid = main.MODID)
public class ItemInit {


    public static final Map<Item, String> ITEMS = new HashMap<Item, String>();

    public static final Map<String, Object> gun_ammo_lookup = new HashMap<>();
    public static final Map<String, Object> gun_case_lookup = new HashMap<>();
    public static final Map<Object, Object> Clip_ammo_lookup = new HashMap<>();
    public static final Map<String, Object> gun_lookup = new HashMap<>();
    public static final List<FoeScrap> scrap = new ArrayList<>();

    /**
     * Register this mod's {@link Item}s.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        for (ModItems items : ModItems.values()) {
            registry.register(items.getITEM());
            ITEMS.put(items.getITEM(), items.getNAME());
        }


        gun_ammo_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), TENMMCLIP.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), FOURTENMMCLIP.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), SSHELL.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), BATTERY.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), FLARE.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), SEVEN_MMCLIP.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), PLASMA_CARTRIDGE.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), FOURFOURMAGAMMO.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), TENMMSUBCLIP.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName(), FIVE_MMCLIP.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), FL_AMMO.getITEM());
        gun_ammo_lookup.put(GlobalsGunStats.BASS_CANNON.getGunName(), BATTERY.getITEM());

        Clip_ammo_lookup.put(TENMMCLIP.getITEM(), TENMMAMMO.getITEM());
        Clip_ammo_lookup.put(FOURTENMMCLIP.getITEM(), FOURTENMMAMMO.getITEM());
        Clip_ammo_lookup.put(SEVEN_MMCLIP.getITEM(), SEVEN_MMAMMO.getITEM());
        Clip_ammo_lookup.put(TENMMSUBCLIP.getITEM(), TENMMAMMO.getITEM());
        Clip_ammo_lookup.put(FIVE_MMCLIP.getITEM(), FIVE_MMAMMO.getITEM());
        Clip_ammo_lookup.put(BATTERY.getITEM(), Items.AIR);
        Clip_ammo_lookup.put(PLASMA_CARTRIDGE.getITEM(), Items.AIR);
        Clip_ammo_lookup.put(FOURFOURMAGAMMO.getITEM(), Items.AIR);
        Clip_ammo_lookup.put(SSHELL.getITEM(), Items.AIR);
        Clip_ammo_lookup.put(FLARE.getITEM(), Items.AIR);
        Clip_ammo_lookup.put(FL_AMMO.getITEM(), Items.AIR);

        gun_case_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), TENMMBULLETCASE.getITEM());
        gun_case_lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), TENMMBULLETCASE.getITEM());
        gun_case_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), FOURTENMMBULLETCASE.getITEM());
        gun_case_lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), SEVEN_MM_BULLETCASE.getITEM());
        gun_case_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), BATTERY.getITEM());

        gun_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), TENMM.getITEM());
        gun_lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), TENMMSUB.getITEM());
        gun_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), FOURTENMM.getITEM());
        gun_lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), FOURFOURREVOLVER.getITEM());
        gun_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), DB_SHOUTGUN.getITEM());
        gun_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), LASERPISTOL.getITEM());
        gun_lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), PLASMA_PISTOL.getITEM());
        gun_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), FLARE.getITEM());
        gun_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), SEVEN_MM_RIFLE_LS.getITEM());
        gun_lookup.put(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName(), FIVE_MM_MINIGUN_LS.getITEM());
        gun_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), FLAM_LS.getITEM());
        gun_lookup.put(GlobalsGunStats.BASS_CANNON.getGunName(), BASSCANNON_RS.getITEM());


        scrap.add((FoeScrap) ModItems.ALUMINUM.getITEM());
        scrap.add((FoeScrap) ModItems.ASBESTOS.getITEM());
        scrap.add((FoeScrap) ModItems.BALLISTIC_FIBER.getITEM());
        scrap.add((FoeScrap) ModItems.BOLTS.getITEM());
        scrap.add((FoeScrap) ModItems.CERAMIC.getITEM());
        scrap.add((FoeScrap) ModItems.CLOTH.getITEM());
        scrap.add((FoeScrap) ModItems.CONCRETE.getITEM());
        scrap.add((FoeScrap) ModItems.COPPER.getITEM());
        scrap.add((FoeScrap) ModItems.CORK.getITEM());
        scrap.add((FoeScrap) ModItems.CRYSTAL.getITEM());
        scrap.add((FoeScrap) ModItems.ELECTRONIC_PARTS.getITEM());
        scrap.add((FoeScrap) ModItems.FIBER_OPTICS.getITEM());
        scrap.add((FoeScrap) ModItems.FIBERGLASS.getITEM());
        scrap.add((FoeScrap) ModItems.GEARS.getITEM());
        scrap.add((FoeScrap) ModItems.GLUE.getITEM());
        scrap.add((FoeScrap) ModItems.LEAD.getITEM());
        scrap.add((FoeScrap) ModItems.NUCLEAR_MATERIAL.getITEM());
        scrap.add((FoeScrap) ModItems.OIL.getITEM());
        scrap.add((FoeScrap) ModItems.PLASTIC.getITEM());
        scrap.add((FoeScrap) ModItems.RUBBER.getITEM());
        scrap.add((FoeScrap) ModItems.SILVER.getITEM());
        scrap.add((FoeScrap) ModItems.SPRING.getITEM());
        scrap.add((FoeScrap) ModItems.STEEL.getITEM());

    }

}



