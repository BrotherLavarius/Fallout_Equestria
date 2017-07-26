package com.redsparkle.api.utils;
import com.redsparkle.api.items.helpers.Item_Instances.Item_bullet;
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
import com.redsparkle.foe.items.utility.AdvancedEnviropmentMonitor;
import com.redsparkle.foe.items.utility.PipBuck;
import net.minecraft.item.Item;
/**
 * Created by hoijima on 23.06.17.
 */
public class GlobalItemArray_For_init {
    public static final String[] ItemNames = new String[]{
            //---------------------UTILITY--------------------------
            GlobalNames.PipbuckTT,
            GlobalNames.LevelingCrystall,
            GlobalNames.AEM,
            //---------------------FOOD--------------------------
            GlobalNames.blamko_macCheese,
            GlobalNames.dandy_boy_apples,
            GlobalNames.Fancy_mare_Snack_Cakes,
            GlobalNames.gumDrops,
            GlobalNames.potato_crisps,
            GlobalNames.sugar_bombs,
            //---------------------MEDS--------------------------
            GlobalNames.RadAway,
            GlobalNames.RadX,
            GlobalNames.redHealPotion,
            GlobalNames.whiteHealpotion,
            GlobalNames.purpleHealPotion,
            GlobalNames.darkHealPotion,
            //---------------------AMMO--------------------------
            GlobalNames.TenMMAmmo,
            GlobalNames.TenMMClip,
            GlobalNames.Battery,
            GlobalNames.FourTenMMAmmo,
            GlobalNames.FourTenMMClip,
            GlobalNames.shell,
            //---------------------GUNS--------------------------
            GlobalNames.TenMM,
            GlobalNames.LaserPistol,
            GlobalNames.FourTenMM,
            GlobalNames.db_shoutgun,
            //---------------------PLACEHOLDER--------------------
            "bullet",
            //---------------------AMMO--------------------------
            GlobalNames.flare,
            //---------------------GUNS--------------------------
            GlobalNames.flare_gun,
            GlobalNames.TenMMCase,
            GlobalNames.FourTenMMCase
    };
    // MEDS
    public static RadX radx;
    public static RadAway radAway;
    public static RedHealPotion redHealPotion;
    public static WhiteHealpotion whiteHealpotion;
    public static PurpleHealPotion purpleHealPotion;
    public static DarkHealPotion darkHealPotion;
    // UTILITY
    public static PipBuck pipbuck;
    public static LvlingCrystall lvlingCrystall;
    public static AdvancedEnviropmentMonitor aem;
    // FOOD
    public static Potato_Crisps potato_crisps;
    public static Blamko_MacCheese blamko_macCheese;
    public static Dandy_Boy_Apples dandy_boy_apples;
    public static GumDrops gumDrops;
    public static Sugar_Bombs sugar_bombs;
    public static Fancy_mare_Snack_Cakes fancy_mare_snack_cakes;
    // AMMO and CLIPS
    public static TenMMammo tenMMAmmo;
    public static TenMMClip tenMMClip;
    public static FourTenMMammo fourTenMMammo;
    public static FourTenMMClip fourTenMMClip;
    public static Battery battery;
    public static SShell sShell;
    public static FlareShell flare;
    public static TenMMbulletCase tenMMbulletCase;
    public static FourTenMMbulletCase fourTenMMbulletCase;
    // GUNS
    public static TenMM tenMM;
    public static FourTenMM fourTenMM;
    public static LaserPistol laserPistol;
    public static SB_shoutgun sb_shoutgun;
    public static FlareGun flareGun;
    // PLACEHOLDER
    public static Item_bullet itb;
    public static final Item[] AllInit = new Item[]{
            //---------------------UTILITY--------------------------
            pipbuck,                                    //0
            lvlingCrystall,//1
            aem,//2
            //--------------FOOD--------------------------
            blamko_macCheese,//3
            dandy_boy_apples,//4
            fancy_mare_snack_cakes,//5
            gumDrops,//6
            potato_crisps,//7
            sugar_bombs,//8
            //--------------MEDS--------------------------
            radAway,//9
            radx,//10
            redHealPotion,//11
            whiteHealpotion,//12
            purpleHealPotion,//13
            darkHealPotion,//14
            //--------------AMMO--------------------------
            tenMMAmmo,//15
            tenMMClip,//16
            battery,//17
            fourTenMMammo,//18
            fourTenMMClip,//19
            sShell,//20
            //--------------GUNS--------------------------
            tenMM,//21
            laserPistol,//22
            fourTenMM,//23
            sb_shoutgun,//24
            //--------------PLACEHOLDER--------------------
            itb,//25
            //--------------AMMO--------------------------
            flare,//26
            //--------------GUNS--------------------------
            flareGun,//27
            tenMMbulletCase,//28
            fourTenMMbulletCase//29
    };
    public static Item[] obj = new Item[]{
            new PipBuck(),
            new LvlingCrystall(),
            new AdvancedEnviropmentMonitor(),
            new Blamko_MacCheese(),
            new Dandy_Boy_Apples(),
            new GumDrops(),
            new Potato_Crisps(),
            new Fancy_mare_Snack_Cakes(),
            new Sugar_Bombs(),
            new RadAway(),
            new RadX(),
            new RedHealPotion(),
            new WhiteHealpotion(),
            new PurpleHealPotion(),
            new DarkHealPotion(),
            new TenMMammo(),
            new TenMMClip(),
            new Battery(),
            new FourTenMMammo(),
            new FourTenMMClip(),
            new SShell(),
            new TenMM(),
            new LaserPistol(),
            new FourTenMM(),
            new SB_shoutgun(),
            new Item_bullet(),
            new FlareShell(),
            new FlareGun(),
            new TenMMbulletCase(),
            new FourTenMMbulletCase()
    };
}
