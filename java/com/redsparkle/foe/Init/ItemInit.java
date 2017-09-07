package com.redsparkle.foe.Init;

import com.redsparkle.api.items.helpers.Item_Instances.Item_bullet;
import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.items.armor.powered.*;
import com.redsparkle.foe.items.food.old.*;
import com.redsparkle.foe.items.guns.*;
import com.redsparkle.foe.items.guns.ammo.FlareShell.FlareShell;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMClip;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMammo;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMbulletCase;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
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
import com.redsparkle.foe.items.saddlebags.Trigger_Item;
import com.redsparkle.foe.items.saddlebags.ammo.Seven_mmAmmo;
import com.redsparkle.foe.items.saddlebags.ammo.Seven_mm_bulletCase;
import com.redsparkle.foe.items.saddlebags.clip.Seven_mmClip;
import com.redsparkle.foe.items.saddlebags.guns.Seven_mm_rifle;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by hoijima on 14.12.16.
 */
@Mod.EventBusSubscriber(modid = main.MODID)
public class ItemInit {

    public static final RadX radx = new RadX(GlobalNames.RadX);
    public static final RadAway radAway = new RadAway(GlobalNames.RadAway);
    public static final RedHealPotion redHealPotion = new RedHealPotion(GlobalNames.redHealPotion);
    public static final WhiteHealpotion whiteHealpotion = new WhiteHealpotion(GlobalNames.whiteHealpotion);
    public static final PurpleHealPotion purpleHealPotion = new PurpleHealPotion(GlobalNames.purpleHealPotion);
    public static final DarkHealPotion darkHealPotion = new DarkHealPotion(GlobalNames.darkHealPotion);
    public static final PipBuck pipbuck = new PipBuck(GlobalNames.PipbuckTT);
    public static final LvlingCrystall lvlingCrystall = new LvlingCrystall(GlobalNames.LevelingCrystall);
    public static final AdvancedEnviropmentMonitor aem = new AdvancedEnviropmentMonitor(GlobalNames.AEM);
    public static final Potato_Crisps potato_crisps = new Potato_Crisps(GlobalNames.potato_crisps);
    public static final Blamko_MacCheese blamko_macCheese = new Blamko_MacCheese(GlobalNames.blamko_macCheese);
    public static final Dandy_Boy_Apples dandy_boy_apples = new Dandy_Boy_Apples(GlobalNames.dandy_boy_apples);
    public static final GumDrops gumDrops = new GumDrops(GlobalNames.gumDrops);
    public static final Sugar_Bombs sugar_bombs = new Sugar_Bombs(GlobalNames.sugar_bombs);
    public static final Fancy_mare_Snack_Cakes fancy_mare_snack_cakes = new Fancy_mare_Snack_Cakes(GlobalNames.Fancy_mare_Snack_Cakes);


    public static final TenMMammo tenMMAmmo = new TenMMammo(GlobalNames.TenMMAmmo);
    public static final TenMMClip tenMMClip = new TenMMClip(GlobalNames.TenMMClip);
    public static final FourTenMMammo fourTenMMammo = new FourTenMMammo(GlobalNames.FourTenMMAmmo);
    public static final FourTenMMClip fourTenMMClip = new FourTenMMClip(GlobalNames.FourTenMMClip);
    public static final Battery battery = new Battery(GlobalNames.Battery);
    public static final SShell sShell = new SShell(GlobalNames.shell);
    public static final FlareShell flare = new FlareShell(GlobalNames.flare);
    public static final TenMMbulletCase tenMMbulletCase = new TenMMbulletCase(GlobalNames.TenMMCase);
    public static final FourTenMMbulletCase fourTenMMbulletCase = new FourTenMMbulletCase(GlobalNames.FourTenMMCase);

    public static final Seven_mmAmmo seven_mmAmmo = new Seven_mmAmmo(GlobalNames.Seven_mmAmmo);
    public static final Seven_mmClip seven_mmClip = new Seven_mmClip(GlobalNames.Seven_mmClip);
    public static final Seven_mm_bulletCase seven_mm_bulletCase = new Seven_mm_bulletCase(GlobalNames.Seven_mm_bulletCase);


    public static final TenMM tenMM = new TenMM(GlobalNames.TenMM, GlobalsGunStats.TEN_MM, tenMMClip, tenMMAmmo);
    public static final FourTenMM fourTenMM = new FourTenMM(GlobalNames.FourTenMM, GlobalsGunStats.FOUR_TEN_MM, fourTenMMClip, fourTenMMammo);
    public static final LaserPistol laserPistol = new LaserPistol(GlobalNames.LaserPistol, GlobalsGunStats.LASER_PISTOL, battery, Items.AIR);
    public static final SB_shoutgun sb_shoutgun = new SB_shoutgun(GlobalNames.db_shoutgun, GlobalsGunStats.DB_SHOUTGUN, Items.AIR, sShell);
    public static final FlareGun flareGun = new FlareGun(GlobalNames.flare_gun, GlobalsGunStats.FLARE_GUN, Items.AIR, flare);

    public static final Item_bullet itb = new Item_bullet("bullet");

    public static final Trigger_Item trigger_item = new Trigger_Item(GlobalNames.trigger_Item);

    public static final Seven_mm_rifle seven_mm_rifle_RS = new Seven_mm_rifle(GlobalNames.Seven_mm_rifle + "_rs", "RS", GlobalsGunStats.SEVEN_MM_RIFLE, seven_mmClip, seven_mmAmmo);
    public static final Seven_mm_rifle seven_mm_rifle_LS = new Seven_mm_rifle(GlobalNames.Seven_mm_rifle + "_ls", "LS", GlobalsGunStats.SEVEN_MM_RIFLE, seven_mmClip, seven_mmAmmo);


    public static final Saddlebags sadlebags = new Saddlebags(GlobalNames.saddlebags);
    public static final t40head t40head = new t40head(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.HEAD);
    public static final t40body t40body = new t40body(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.CHEST);
    public static final t40legs t40legs = new t40legs(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.LEGS);
    public static final t50head t50head = new t50head(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.HEAD);
    public static final t50body t50body = new t50body(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.CHEST);
    public static final t50legs t50legs = new t50legs(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.LEGS);
    public static final t60head t60head = new t60head(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.HEAD);
    public static final t60body t60body = new t60body(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.CHEST);
    public static final t60legs t60legs = new t60legs(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.LEGS);

    public static final Set<Item> ITEMS = new HashSet<>();

    public static final Map<String, Object> gun_ammo_lookup = new HashMap<String, Object>();

    /**
     * Register this mod's {@link Item}s.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        t40head.setUnlocalizedName(GlobalNames.T40Head).setRegistryName(GlobalNames.T40Head);
        t40body.setUnlocalizedName(GlobalNames.T40Body).setRegistryName(GlobalNames.T40Body);
        t40legs.setUnlocalizedName(GlobalNames.T40Legs).setRegistryName(GlobalNames.T40Legs);
        t50head.setUnlocalizedName(GlobalNames.T50Head).setRegistryName(GlobalNames.T50Head);
        t50body.setUnlocalizedName(GlobalNames.T50Body).setRegistryName(GlobalNames.T50Body);
        t50legs.setUnlocalizedName(GlobalNames.T50Legs).setRegistryName(GlobalNames.T50Legs);
        t60head.setUnlocalizedName(GlobalNames.T60Head).setRegistryName(GlobalNames.T60Head);
        t60body.setUnlocalizedName(GlobalNames.T60Body).setRegistryName(GlobalNames.T60Body);
        t60legs.setUnlocalizedName(GlobalNames.T60Legs).setRegistryName(GlobalNames.T60Legs);


        final Item[] items = {
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
                fourTenMMammo,
                fourTenMMClip,
                battery,
                sShell,
                flare,
                tenMMbulletCase,
                fourTenMMbulletCase,
                tenMM,
                fourTenMM,
                laserPistol,
                sb_shoutgun,
                flareGun,
                itb,
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
                trigger_item,
                seven_mm_rifle_RS,
                seven_mm_rifle_LS,
                seven_mmAmmo,
                seven_mmClip,
                seven_mm_bulletCase
        };


        for (final Item item : items) {


            ITEMS.add(item);
        }
        registry.registerAll(items);

        gun_ammo_lookup.put(GlobalsGunStats.TEN_MM.getGunName(), tenMMClip);
        gun_ammo_lookup.put(GlobalsGunStats.FOUR_TEN_MM.getGunName(), fourTenMMClip);
        gun_ammo_lookup.put(GlobalsGunStats.DB_SHOUTGUN.getGunName(), sShell);
        gun_ammo_lookup.put(GlobalsGunStats.LASER_PISTOL.getGunName(), battery);
        gun_ammo_lookup.put(GlobalsGunStats.FLARE_GUN.getGunName(), flare);
        gun_ammo_lookup.put(GlobalsGunStats.SEVEN_MM_RIFLE.getGunName(), seven_mmClip);

    }

}



