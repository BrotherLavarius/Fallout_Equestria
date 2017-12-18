package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.api.items.helpers.Item_Instances.FoeScrap;
import com.redsparkle.foe.items.armor.powered.*;
import com.redsparkle.foe.items.food.old.*;
import com.redsparkle.foe.items.guns.*;
import com.redsparkle.foe.items.guns.ammo.FlareShell.FlareShell;
import com.redsparkle.foe.items.guns.ammo.FourFourMag.FourFourMagAmmo;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMClip;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMammo;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMbulletCase;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.Plasma.PLasma_Cartridge;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMSubClip;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMammo;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMbulletCase;
import com.redsparkle.foe.items.guns.ammo.shell.SShell;
import com.redsparkle.foe.items.meds.HealPotions.DarkHealPotion;
import com.redsparkle.foe.items.meds.HealPotions.PurpleHealPotion;
import com.redsparkle.foe.items.meds.HealPotions.RedHealPotion;
import com.redsparkle.foe.items.meds.HealPotions.WhiteHealpotion;
import com.redsparkle.foe.items.meds.RadAway;
import com.redsparkle.foe.items.meds.RadX;
import com.redsparkle.foe.items.misc_one_Time_use.LvlingCrystall;
import com.redsparkle.foe.items.saddlebags.Saddlebags;
import com.redsparkle.foe.items.saddlebags.Saddlebags_army;
import com.redsparkle.foe.items.saddlebags.Trigger_Item;
import com.redsparkle.foe.items.saddlebags.ammo.Five_mmAmmo;
import com.redsparkle.foe.items.saddlebags.ammo.Five_mm_bulletCase;
import com.redsparkle.foe.items.saddlebags.ammo.Seven_mmAmmo;
import com.redsparkle.foe.items.saddlebags.ammo.Seven_mm_bulletCase;
import com.redsparkle.foe.items.saddlebags.clip.Five_mmClip;
import com.redsparkle.foe.items.saddlebags.clip.Seven_mmClip;
import com.redsparkle.foe.items.saddlebags.clip.flammenwerfer_ammo;
import com.redsparkle.foe.items.saddlebags.guns.Five_mm_minigun;
import com.redsparkle.foe.items.saddlebags.guns.Seven_mm_rifle;
import com.redsparkle.foe.items.saddlebags.guns.bass_cannon;
import com.redsparkle.foe.items.saddlebags.guns.flammenwarfer;
import com.redsparkle.foe.items.scrap.*;
import com.redsparkle.foe.items.utility.AdvancedEnviropmentMonitor;
import com.redsparkle.foe.items.utility.PipBuck;
import com.redsparkle.foe.main;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;


/**
 * Created by hoijima on 14.12.16.
 */
@Mod.EventBusSubscriber(modid = main.MODID)
public class ItemInit {

    public static final RadX radx = new RadX(GlobalNames.radX);
    public static final RadAway radAway = new RadAway(GlobalNames.radaway);
    public static final RedHealPotion redHealPotion = new RedHealPotion(GlobalNames.redhealpotion);
    public static final WhiteHealpotion whiteHealpotion = new WhiteHealpotion(GlobalNames.whitehealpotion);
    public static final PurpleHealPotion purpleHealPotion = new PurpleHealPotion(GlobalNames.purplehealpotion);
    public static final DarkHealPotion darkHealPotion = new DarkHealPotion(GlobalNames.darkhealpotion);
    public static final PipBuck pipbuck = new PipBuck(GlobalNames.pipbucktt);
    public static final LvlingCrystall lvlingCrystall = new LvlingCrystall(GlobalNames.levelingcrystall);
    public static final AdvancedEnviropmentMonitor aem = new AdvancedEnviropmentMonitor(GlobalNames.aem);
    public static final Potato_Crisps potato_crisps = new Potato_Crisps(GlobalNames.potato_crisps);
    public static final Blamko_MacCheese blamko_macCheese = new Blamko_MacCheese(GlobalNames.blamko_maccheese);
    public static final Dandy_Boy_Apples dandy_boy_apples = new Dandy_Boy_Apples(GlobalNames.dandy_boy_apples);
    public static final GumDrops gumDrops = new GumDrops(GlobalNames.gumDrops);
    public static final Sugar_Bombs sugar_bombs = new Sugar_Bombs(GlobalNames.sugar_bombs);
    public static final Fancy_mare_Snack_Cakes fancy_mare_snack_cakes = new Fancy_mare_Snack_Cakes(GlobalNames.fancy_mare_snack_cakes);

    public static final Aluminum aluminum = new Aluminum(GlobalNames.aluminum);
    public static final Asbestos asbestos = new Asbestos(GlobalNames.asbestos);
    public static final Ballistic_fiber ballistic_fiber = new Ballistic_fiber(GlobalNames.ballistic_fiber);
    public static final Bolts bolts = new Bolts(GlobalNames.bolts);
    public static final Ceramic ceramic = new Ceramic(GlobalNames.ceramic);
    public static final Cloth cloth = new Cloth(GlobalNames.cloth);
    public static final Concrete concrete = new Concrete(GlobalNames.concrete);
    public static final Copper copper = new Copper(GlobalNames.copper);
    public static final Cork cork = new Cork(GlobalNames.cork);
    public static final Crystal crystal = new Crystal(GlobalNames.crystal);
    public static final Electronic_parts electronic_parts = new Electronic_parts(GlobalNames.electronic_parts);
    public static final Fiberglass fiberglass = new Fiberglass(GlobalNames.fiberglass);
    public static final Fiber_optics fiber_optics = new Fiber_optics(GlobalNames.fiber_optics);
    public static final Gears gears = new Gears(GlobalNames.gears);
    public static final Glue glue = new Glue(GlobalNames.glue);
    public static final Lead lead = new Lead(GlobalNames.lead);
    public static final Nuclear_material nuclear_material = new Nuclear_material(GlobalNames.nuclear_material);
    public static final Oil oil = new Oil(GlobalNames.oil);
    public static final Plastic plastic = new Plastic(GlobalNames.plastic);
    public static final Rubber rubber = new Rubber(GlobalNames.rubber);
    public static final Silver silver = new Silver(GlobalNames.silver);
    public static final Spring spring = new Spring(GlobalNames.spring);
    public static final Steel steel = new Steel(GlobalNames.steel);


    public static final FourFourMagAmmo fourFourMagAmmo = new FourFourMagAmmo(GlobalNames.fortyfour_ammo);
    public static final SShell sShell = new SShell(GlobalNames.shell);
    public static final FlareShell flare = new FlareShell(GlobalNames.flare);
    public static final TenMMammo tenMMAmmo = new TenMMammo(GlobalNames.tenmmammo);
    public static final FourTenMMammo fourTenMMammo = new FourTenMMammo(GlobalNames.fourtenmmammo);
    public static final Seven_mmAmmo seven_mmAmmo = new Seven_mmAmmo(GlobalNames.seven_mmammo);
    public static final Five_mmAmmo five_mmAmmo = new Five_mmAmmo(GlobalNames.five_mmammo);


    public static final TenMMClip tenMMClip = new TenMMClip(GlobalNames.tenmmclip, GlobalsGunStats.TEN_MM.getClipsize(), "for " + GlobalsGunStats.TEN_MM.getGunName());
    public static final TenMMSubClip tenMMSubclip = new TenMMSubClip(GlobalNames.tenmmsubclip, GlobalsGunStats.TEN_MM_SUB.getClipsize(), "for " + GlobalsGunStats.TEN_MM_SUB.getGunName());
    public static final FourTenMMClip fourTenMMClip = new FourTenMMClip(GlobalNames.fourtenmmclip, GlobalsGunStats.FOUR_TEN_MM.getClipsize(), "for " + GlobalsGunStats.FOUR_TEN_MM.getGunName());
    public static final Battery battery = new Battery(GlobalNames.Battery, GlobalsGunStats.SEVEN_MM_RIFLE.getClipsize(), "for " + GlobalsGunStats.LASER_PISTOL.getGunName());
    public static final Seven_mmClip seven_mmClip = new Seven_mmClip(GlobalNames.seven_mmclip, GlobalsGunStats.SEVEN_MM_RIFLE.getClipsize(), "for " + GlobalsGunStats.SEVEN_MM_RIFLE.getGunName());
    public static final PLasma_Cartridge plasma_cartridge = new PLasma_Cartridge(GlobalNames.plasma_cartidge, GlobalsGunStats.PLASMA_PISTOL.getClipsize(), GlobalsGunStats.PLASMA_PISTOL.getGunName());
    public static final Five_mmClip five_mmClip = new Five_mmClip(GlobalNames.five_mmclip, GlobalsGunStats.FIVE_MM_MINIGUN.getClipsize(), GlobalsGunStats.FIVE_MM_MINIGUN.getGunName());
    public static final flammenwerfer_ammo fl_ammo = new flammenwerfer_ammo(GlobalNames.flammenwarfer_ammo, GlobalsGunStats.FLAMMENWERFER.getClipsize(), GlobalsGunStats.FLAMMENWERFER.getGunName());


    public static final Seven_mm_bulletCase seven_mm_bulletCase = new Seven_mm_bulletCase(GlobalNames.seven_mm_bulletcase);
    public static final TenMMbulletCase tenMMbulletCase = new TenMMbulletCase(GlobalNames.tenmmcase);
    public static final FourTenMMbulletCase fourTenMMbulletCase = new FourTenMMbulletCase(GlobalNames.fourtenmmcase);
    public static final Five_mm_bulletCase five_mm_bulletCase = new Five_mm_bulletCase(GlobalNames.five_mm_bulletcase);

    public static final TenMM tenMM = new TenMM(GlobalsGunStats.TEN_MM.getGunName(), GlobalsGunStats.TEN_MM, tenMMClip, tenMMAmmo);
    public static final TenMMSub tenMMsub = new TenMMSub(GlobalsGunStats.TEN_MM_SUB.getGunName(), GlobalsGunStats.TEN_MM_SUB, tenMMSubclip, tenMMAmmo);
    public static final FourTenMM fourTenMM = new FourTenMM(GlobalsGunStats.FOUR_TEN_MM.getGunName(), GlobalsGunStats.FOUR_TEN_MM, fourTenMMClip, fourTenMMammo);
    public static final Plasma_pistol plasma_pistol = new Plasma_pistol(GlobalsGunStats.PLASMA_PISTOL.getGunName(), GlobalsGunStats.PLASMA_PISTOL, plasma_cartridge, Items.AIR);
    public static final LaserPistol laserPistol = new LaserPistol(GlobalsGunStats.LASER_PISTOL.getGunName(), GlobalsGunStats.LASER_PISTOL, battery, Items.AIR);

    public static final FourFourRevolver fourFourRevolver = new FourFourRevolver(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), GlobalsGunStats.FOURFOUR_REVOLVER, Items.AIR, fourFourMagAmmo);
    public static final DB_shoutgun db_shoutgun = new DB_shoutgun(GlobalsGunStats.DB_SHOUTGUN.getGunName(), GlobalsGunStats.DB_SHOUTGUN, Items.AIR, sShell);
    public static final FlareGun flareGun = new FlareGun(GlobalsGunStats.FLARE_GUN.getGunName(), GlobalsGunStats.FLARE_GUN, Items.AIR, flare);


    public static final Trigger_Item trigger_item = new Trigger_Item(GlobalNames.trigger_Item);

    public static final Seven_mm_rifle seven_mm_rifle_RS = new Seven_mm_rifle(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName() + "_rs", "RS", GlobalsGunStats.SEVEN_MM_RIFLE, seven_mmClip, seven_mmAmmo);
    public static final Seven_mm_rifle seven_mm_rifle_LS = new Seven_mm_rifle(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName() + "_ls", "LS", GlobalsGunStats.SEVEN_MM_RIFLE, seven_mmClip, seven_mmAmmo);

    public static final Five_mm_minigun five_mm_minigun_RS = new Five_mm_minigun(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName() + "_rs", "RS", GlobalsGunStats.FIVE_MM_MINIGUN, five_mmClip, five_mmAmmo);
    public static final Five_mm_minigun five_mm_minigun_LS = new Five_mm_minigun(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName() + "_ls", "LS", GlobalsGunStats.FIVE_MM_MINIGUN, five_mmClip, five_mmAmmo);

    public static final flammenwarfer flam_LS = new flammenwarfer(GlobalsGunStats.FLAMMENWERFER.getGunName() + "_ls", "LS", GlobalsGunStats.FLAMMENWERFER, fl_ammo, Items.AIR);
    public static final flammenwarfer flam_RS = new flammenwarfer(GlobalsGunStats.FLAMMENWERFER.getGunName() + "_rs", "RS", GlobalsGunStats.FLAMMENWERFER, fl_ammo, Items.AIR);

    public static final bass_cannon bassCannon_RS = new bass_cannon(GlobalsGunStats.BASS_CANNON.getGunName() + "_rs", "RS", GlobalsGunStats.BASS_CANNON, battery, Items.AIR);

    public static final Saddlebags sadlebags = new Saddlebags(GlobalNames.saddlebags);
    public static final Saddlebags_army saddlebags_army = new Saddlebags_army(GlobalNames.saddlebags_army);
    public static final t40head t40head = new t40head(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.HEAD);
    public static final t40body t40body = new t40body(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.CHEST);
    public static final t40legs t40legs = new t40legs(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.LEGS);
    public static final t50head t50head = new t50head(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.HEAD);
    public static final t50body t50body = new t50body(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.CHEST);
    public static final t50legs t50legs = new t50legs(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.LEGS);
    public static final t60head t60head = new t60head(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.HEAD);
    public static final t60body t60body = new t60body(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.CHEST);
    public static final t60legs t60legs = new t60legs(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.LEGS);

    public static final Map<Item,String> ITEMS = new HashMap<Item,String>();

    public static final Map<String, Object> gun_ammo_lookup = new HashMap<String, Object>();
    public static final Map<String, Object> gun_case_lookup = new HashMap<String, Object>();
    public static final Map<Object, Object> Clip_ammo_lookup = new HashMap<Object, Object>();
    public static final Map<String, Object> gun_lookup = new HashMap<String, Object>();
    public static final List<FoeScrap> scrap = new ArrayList<>();

    /**
     * Register this mod's {@link Item}s.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        t40head.setUnlocalizedName(GlobalNames.t40Head).setRegistryName(GlobalNames.t40Head);
        t40body.setUnlocalizedName(GlobalNames.t40Body).setRegistryName(GlobalNames.t40Body);
        t40legs.setUnlocalizedName(GlobalNames.t40legs).setRegistryName(GlobalNames.t40legs);
        t50head.setUnlocalizedName(GlobalNames.t50Head).setRegistryName(GlobalNames.t50Head);
        t50body.setUnlocalizedName(GlobalNames.t50Body).setRegistryName(GlobalNames.t50Body);
        t50legs.setUnlocalizedName(GlobalNames.t50legs).setRegistryName(GlobalNames.t50legs);
        t60head.setUnlocalizedName(GlobalNames.t60Head).setRegistryName(GlobalNames.t60Head);
        t60body.setUnlocalizedName(GlobalNames.t60Body).setRegistryName(GlobalNames.t60Body);
        t60legs.setUnlocalizedName(GlobalNames.t60legs).setRegistryName(GlobalNames.t60legs);


        final Item[] items = {


                aluminum,
                asbestos,
                ballistic_fiber,
                bolts,
                ceramic,
                cloth,
                concrete,
                copper,
                cork,
                crystal,
                electronic_parts,
                fiber_optics,
                fiberglass,
                gears,
                glue,
                lead,
                nuclear_material,
                oil,
                plastic,
                rubber,
                silver,
                spring,
                steel,

                radx,
                radAway,
                redHealPotion,
                whiteHealpotion,
                purpleHealPotion,
                darkHealPotion,
                pipbuck,
                lvlingCrystall,
                aem,
                potato_crisps,
                blamko_macCheese,
                dandy_boy_apples,
                gumDrops,
                sugar_bombs,
                fancy_mare_snack_cakes,

                tenMMAmmo,
                tenMMClip,
                tenMMSubclip,

                fourTenMMammo,
                fourTenMMClip,

                battery,
                plasma_cartridge,

                sShell,
                flare,
                fourFourMagAmmo,
                tenMMbulletCase,
                fourTenMMbulletCase,


                tenMM,
                tenMMsub,
                fourTenMM,

                laserPistol,
                plasma_pistol,

                fourFourRevolver,
                db_shoutgun,
                flareGun,


                t40head,
                t40body,
                t40legs,
                t50head,
                t50body,
                t50legs,
                t60head,
                t60body,
                t60legs,

                sadlebags,
                saddlebags_army,

                trigger_item,

                seven_mm_rifle_RS,
                seven_mm_rifle_LS,
                seven_mmAmmo,
                seven_mmClip,
                seven_mm_bulletCase,

                five_mm_minigun_LS,
                five_mm_minigun_RS,
                five_mmAmmo,
                five_mmClip,
                five_mm_bulletCase,

                flam_LS,
                flam_RS,
                fl_ammo,

                bassCannon_RS

        };


        for (final Item item : items) {
            ITEMS.put(item,item.getRegistryName().getResourcePath());
        }
        registry.registerAll(items);

        gun_ammo_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), tenMMClip);
        gun_ammo_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), fourTenMMClip);
        gun_ammo_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), sShell);
        gun_ammo_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), battery);
        gun_ammo_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), flare);
        gun_ammo_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), seven_mmClip);
        gun_ammo_lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), plasma_cartridge);
        gun_ammo_lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), fourFourMagAmmo);
        gun_ammo_lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), tenMMSubclip);
        gun_ammo_lookup.put(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName(), five_mmClip);
        gun_ammo_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), fl_ammo);
        gun_ammo_lookup.put(GlobalsGunStats.BASS_CANNON.getGunName(), battery);


        Clip_ammo_lookup.put(tenMMClip, tenMMAmmo);
        Clip_ammo_lookup.put(fourTenMMClip, fourTenMMammo);
        Clip_ammo_lookup.put(seven_mmClip, seven_mmAmmo);
        Clip_ammo_lookup.put(tenMMSubclip, tenMMAmmo);
        Clip_ammo_lookup.put(five_mmClip, five_mmAmmo);
        Clip_ammo_lookup.put(battery, Items.AIR);
        Clip_ammo_lookup.put(plasma_cartridge, Items.AIR);
        Clip_ammo_lookup.put(fourFourMagAmmo, Items.AIR);
        Clip_ammo_lookup.put(sShell, Items.AIR);
        Clip_ammo_lookup.put(flare, Items.AIR);
        Clip_ammo_lookup.put(fl_ammo, Items.AIR);

        gun_case_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), tenMMbulletCase);
        gun_case_lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), tenMMbulletCase);
        gun_case_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), fourTenMMbulletCase);
        gun_case_lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), seven_mm_bulletCase);
        gun_case_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), Items.AIR);
        gun_case_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), battery);

        gun_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), tenMM);
        gun_lookup.put(GlobalsGunStats.TEN_MM_SUB.getGunName(), tenMMsub);
        gun_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), fourTenMM);
        gun_lookup.put(GlobalsGunStats.FOURFOUR_REVOLVER.getGunName(), fourFourRevolver);
        gun_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), db_shoutgun);
        gun_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), laserPistol);
        gun_lookup.put(GlobalsGunStats.PLASMA_PISTOL.getGunName(), plasma_pistol);
        gun_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), flare);
        gun_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), seven_mm_rifle_LS);
        gun_lookup.put(GlobalsGunStats.FIVE_MM_MINIGUN.getGunName(), five_mm_minigun_LS);
        gun_lookup.put(GlobalsGunStats.FLAMMENWERFER.getGunName(), flam_LS);
        gun_lookup.put(GlobalsGunStats.BASS_CANNON.getGunName(), bassCannon_RS);


        scrap.add(aluminum);
        scrap.add(asbestos);
        scrap.add(ballistic_fiber);
        scrap.add(bolts);
        scrap.add(ceramic);
        scrap.add(cloth);
        scrap.add(concrete);
        scrap.add(copper);
        scrap.add(cork);
        scrap.add(crystal);
        scrap.add(electronic_parts);
        scrap.add(fiber_optics);
        scrap.add(fiberglass);
        scrap.add(gears);
        scrap.add(glue);
        scrap.add(lead);
        scrap.add(nuclear_material);
        scrap.add(oil);
        scrap.add(plastic);
        scrap.add(rubber);
        scrap.add(silver);
        scrap.add(spring);
        scrap.add(steel);

    }

}



