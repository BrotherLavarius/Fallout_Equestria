package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.Armor_material;
import com.redsparkle.foe.items.armor.powered.*;
import com.redsparkle.foe.items.armor.powered.t40legs;
import com.redsparkle.foe.items.armor.powered.t50legs;
import com.redsparkle.foe.items.armor.powered.t60legs;
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
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

import static com.redsparkle.api.utils.GlobalNames.*;
import static com.redsparkle.foe.Init.GlobalsGunStats.*;

/**
 * Created by hoijima on 18.12.17.
 */
public enum ModItems {


    RADX(new RadX(radX), radX),
    RADAWAY(new RadAway(radaway), radaway),
    REDHEALPOTION(new RedHealPotion(redhealpotion), redhealpotion),
    WHITEHEALPOTION(new WhiteHealpotion(whitehealpotion), whitehealpotion),
    PURPLEHEALPOTION(new PurpleHealPotion(purplehealpotion), purplehealpotion),
    DARKHEALPOTION(new DarkHealPotion(darkhealpotion), darkhealpotion),
    PIPBUCK(new PipBuck(pipbucktt), pipbucktt),
    LVLINGCRYSTALL(new LvlingCrystall(levelingcrystall), levelingcrystall),
    AEM(new AdvancedEnviropmentMonitor(aem), aem),
    POTATO_CRISPS(new Potato_Crisps(potato_crisps), potato_crisps),
    BLAMKO_MACCHEESE(new Blamko_MacCheese(blamko_maccheese), blamko_maccheese),
    DANDY_BOY_APPLES(new Dandy_Boy_Apples(dandy_boy_apples), dandy_boy_apples),
    GUMDROPS(new GumDrops(gumDrops), gumDrops),
    SUGAR_BOMBS(new Sugar_Bombs(sugar_bombs), sugar_bombs),
    FANCY_MARE_SNACK_CAKES(new Fancy_mare_Snack_Cakes(fancy_mare_snack_cakes), fancy_mare_snack_cakes),
    ALUMINUM(new Aluminum(aluminum), aluminum),
    ASBESTOS(new Asbestos(asbestos), asbestos),
    BALLISTIC_FIBER(new Ballistic_fiber(ballistic_fiber), ballistic_fiber),
    BOLTS(new Bolts(bolts), bolts),
    CERAMIC(new Ceramic(ceramic), ceramic),
    CLOTH(new Cloth(cloth), cloth),
    CONCRETE(new Concrete(concrete), concrete),
    COPPER(new Copper(copper), copper),
    CORK(new Cork(cork), cork),
    CRYSTAL(new Crystal(crystal), crystal),
    ELECTRONIC_PARTS(new Electronic_parts(electronic_parts), electronic_parts),
    FIBERGLASS(new Fiberglass(fiberglass), fiberglass),
    FIBER_OPTICS(new Fiber_optics(fiber_optics), fiber_optics),
    GEARS(new Gears(gears), gears),
    GLUE(new Glue(glue), glue),
    LEAD(new Lead(lead), lead),
    NUCLEAR_MATERIAL(new Nuclear_material(nuclear_material), nuclear_material),
    OIL(new Oil(oil), oil),
    PLASTIC(new Plastic(plastic), plastic),
    RUBBER(new Rubber(rubber), rubber),
    SILVER(new Silver(silver), silver),
    SPRING(new Spring(spring), spring),
    STEEL(new Steel(steel), steel),

    FOURFOURMAGAMMO(new FourFourMagAmmo(fortyfour_ammo), fortyfour_ammo),
    SSHELL(new SShell(shell), shell),
    FLARE(new FlareShell(flare), flare),
    TENMMAMMO(new TenMMammo(tenmmammo), tenmmammo),
    FOURTENMMAMMO(new FourTenMMammo(fourtenmmammo), fourtenmmammo),
    SEVEN_MMAMMO(new Seven_mmAmmo(seven_mmammo), seven_mmammo),
    FIVE_MMAMMO(new Five_mmAmmo(five_mmammo), five_mmammo),
    TENMMCLIP(new TenMMClip(tenmmclip, TEN_MM.getClipsize(), "for " + TEN_MM.getGunName()), tenmmclip),
    TENMMSUBCLIP(new TenMMSubClip(tenmmsubclip, TEN_MM_SUB.getClipsize(), "for " + TEN_MM_SUB.getGunName()), tenmmsubclip),
    FOURTENMMCLIP(new FourTenMMClip(fourtenmmclip, FOUR_TEN_MM.getClipsize(), "for " + FOUR_TEN_MM.getGunName()), fourtenmmclip),
    BATTERY(new Battery(Battery, SEVEN_MM_RIFLE.getClipsize(), "for " + LASER_PISTOL.getGunName()), Battery),
    SEVEN_MMCLIP(new Seven_mmClip(seven_mmclip, SEVEN_MM_RIFLE.getClipsize(), "for " + SEVEN_MM_RIFLE.getGunName()), seven_mmclip),
    PLASMA_CARTRIDGE(new PLasma_Cartridge(plasma_cartidge, GlobalsGunStats.PLASMA_PISTOL.getClipsize(), GlobalsGunStats.PLASMA_PISTOL.getGunName()), plasma_cartidge),
    FIVE_MMCLIP(new Five_mmClip(five_mmclip, FIVE_MM_MINIGUN.getClipsize(), FIVE_MM_MINIGUN.getGunName()), five_mmclip),
    FL_AMMO(new flammenwerfer_ammo(flammenwarfer_ammo, FLAMMENWERFER.getClipsize(), FLAMMENWERFER.getGunName()), flammenwarfer_ammo),
    SEVEN_MM_BULLETCASE(new Seven_mm_bulletCase(seven_mm_bulletcase), seven_mm_bulletcase),
    TENMMBULLETCASE(new TenMMbulletCase(tenmmcase), tenmmcase),
    FOURTENMMBULLETCASE(new FourTenMMbulletCase(fourtenmmcase), fourtenmmcase),
    FIVE_MM_BULLETCASE(new Five_mm_bulletCase(five_mm_bulletcase), five_mm_bulletcase),


    TENMM(new TenMM(TEN_MM.getGunName(), TEN_MM, TENMMCLIP.getITEM(), TENMMAMMO.getITEM()), TEN_MM.getGunName()),
    TENMMSUB(new TenMMSub(TEN_MM_SUB.getGunName(), TEN_MM_SUB, TENMMSUBCLIP.getITEM(), TENMMAMMO.getITEM()), TEN_MM_SUB.getGunName()),
    FOURTENMM(new FourTenMM(FOUR_TEN_MM.getGunName(), FOUR_TEN_MM, FOURTENMMCLIP.getITEM(), FOURTENMMAMMO.getITEM()), FOUR_TEN_MM.getGunName()),
    PLASMA_PISTOL(new Plasma_pistol(GlobalsGunStats.PLASMA_PISTOL.getGunName(), GlobalsGunStats.PLASMA_PISTOL, PLASMA_CARTRIDGE.getITEM(), Items.AIR), GlobalsGunStats.PLASMA_PISTOL.getGunName()),
    LASERPISTOL(new LaserPistol(LASER_PISTOL.getGunName(), LASER_PISTOL, BATTERY.getITEM(), Items.AIR), LASER_PISTOL.getGunName()),
    FOURFOURREVOLVER(new FourFourRevolver(FOURFOUR_REVOLVER.getGunName(), FOURFOUR_REVOLVER, Items.AIR, FOURFOURMAGAMMO.getITEM()), FOURFOUR_REVOLVER.getGunName()),
    DB_SHOUTGUN(new DB_shoutgun(GlobalsGunStats.DB_SHOUTGUN.getGunName(), GlobalsGunStats.DB_SHOUTGUN, Items.AIR, SSHELL.getITEM()), GlobalsGunStats.DB_SHOUTGUN.getGunName()),
    FLAREGUN(new FlareGun(FLARE_GUN.getGunName(), FLARE_GUN, Items.AIR, FLARE.getITEM()), FLARE_GUN.getGunName()),
    TRIGGER_ITEM(new Trigger_Item(trigger_Item), trigger_Item),
    SEVEN_MM_RIFLE_RS(new Seven_mm_rifle(SEVEN_MM_RIFLE.getGunName() + "_rs", "RS", SEVEN_MM_RIFLE, SEVEN_MMCLIP.getITEM(), SEVEN_MMAMMO.getITEM()), SEVEN_MM_RIFLE.getGunName() + "_rs"),
    SEVEN_MM_RIFLE_LS(new Seven_mm_rifle(SEVEN_MM_RIFLE.getGunName() + "_ls", "LS", SEVEN_MM_RIFLE, SEVEN_MMCLIP.getITEM(), SEVEN_MMAMMO.getITEM()), SEVEN_MM_RIFLE.getGunName() + "_ls"),
    FIVE_MM_MINIGUN_RS(new Five_mm_minigun(FIVE_MM_MINIGUN.getGunName() + "_rs", "RS", FIVE_MM_MINIGUN, FIVE_MMCLIP.getITEM(), FIVE_MMAMMO.getITEM()), FIVE_MM_MINIGUN.getGunName() + "_rs"),
    FIVE_MM_MINIGUN_LS(new Five_mm_minigun(FIVE_MM_MINIGUN.getGunName() + "_ls", "LS", FIVE_MM_MINIGUN, FIVE_MMCLIP.getITEM(), FIVE_MMAMMO.getITEM()), FIVE_MM_MINIGUN.getGunName() + "_ls"),
    FLAM_LS(new flammenwarfer(FLAMMENWERFER.getGunName() + "_ls", "LS", FLAMMENWERFER, FL_AMMO.getITEM(), Items.AIR), FLAMMENWERFER.getGunName() + "_ls"),
    FLAM_RS(new flammenwarfer(FLAMMENWERFER.getGunName() + "_rs", "RS", FLAMMENWERFER, FL_AMMO.getITEM(), Items.AIR), FLAMMENWERFER.getGunName() + "_rs"),
    BASSCANNON_RS(new bass_cannon(BASS_CANNON.getGunName() + "_rs", "RS", BASS_CANNON, BATTERY.getITEM(), Items.AIR), BASS_CANNON.getGunName() + "_rs"),


    SADLEBAGS(new Saddlebags(saddlebags), saddlebags),
    SADDLEBAGS_ARMY(new Saddlebags_army(saddlebags_army), saddlebags_army),

    T40HEAD(new t40head(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.HEAD, t40Head), t40Head),
    T40BODY(new t40body(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.CHEST, t40Body), t40Body),
    T40LEGS(new t40legs(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.LEGS, t40legs), t40legs),
    T50HEAD(new t50head(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.HEAD, t50Head), t50Head),
    T50BODY(new t50body(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.CHEST, t50Body), t50Body),
    T50LEGS(new t50legs(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.LEGS, t50legs), t50legs),
    T60HEAD(new t60head(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.HEAD, t60Head), t60Head),
    T60BODY(new t60body(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.CHEST, t60Body), t60Body),
    T60LEGS(new t60legs(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.LEGS, t60legs), t60legs);


    public Object ITEM;
    public String NAME;

    ModItems(Object object, String name) {
        this.ITEM = object;
        this.NAME = name;
    }

    public Item getITEM() {
        return (Item) ITEM;
    }

    public String getNAME() {
        return NAME;
    }
}
