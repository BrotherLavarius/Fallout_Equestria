package com.redsparkle.foe.events.ServerSIdeONly;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Ammo.IAmmoInterface;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.rad.IRadiationCapability;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateAmmoHolders;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.api.Capability.Player.level.LevelFactoryProvider.LEVEL_CAPABILITY;

/**
 * Created by hoijima on 24.05.17.
 */
public class EventHandlerServerSidePre {
    //TODO: make a sync for guns
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        InventoryPlayer inv = e.player.inventory;
        IAdvInventory iadv = e.player.getCapability(IAdvProvider.Adv_Inv,null);
        for(int i = 0; i <= inv.getSizeInventory(); i++){
            if (inv.getStackInSlot(i).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(i).hasCapability(AmmoFactoryProvider.AMMO_STORAGE,null)){
                IAmmoInterface iammo = inv.getStackInSlot(i).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(iammo.getAmmo(),iammo.getMaxAmmo(),i,0,0),(EntityPlayerMP) e.player);
            }
        }
        for(int g =0; g < iadv.getSlots(); g++){
            if(iadv.getStackInSlot(g).getItem() instanceof Item_AmmoHolder && inv.getStackInSlot(g).hasCapability(AmmoFactoryProvider.AMMO_STORAGE,null)){
                IAmmoInterface iammo = inv.getStackInSlot(g).getCapability(AmmoFactoryProvider.AMMO_STORAGE,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(iammo.getAmmo(),iammo.getMaxAmmo(),g,1,0), (EntityPlayerMP) e.player);
            }
        }
        for(int a = 0; a <= inv.getSizeInventory(); a++){
            if (inv.getStackInSlot(a).getItem() instanceof Item_Firearm && inv.getStackInSlot(a).hasCapability(GunFactoryProvider.GUN,null)){
                IGunInterface igun = inv.getStackInSlot(a).getCapability(GunFactoryProvider.GUN,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(igun.getAmmo(),igun.getMaxAmmo(),a,0,1),(EntityPlayerMP) e.player);
            }
        }
        for(int z = 0; z < iadv.getSlots(); z++){
            if(iadv.getStackInSlot(z).getItem() instanceof Item_Firearm && inv.getStackInSlot(z).hasCapability(GunFactoryProvider.GUN,null)){
                IGunInterface igun = inv.getStackInSlot(z).getCapability(GunFactoryProvider.GUN,null);
                main.simpleNetworkWrapper.sendTo(new MessageUpdateAmmoHolders(igun.getAmmo(),igun.getMaxAmmo(),z,1,1), (EntityPlayerMP) e.player);
            }
        }


        updatePlayerRads(e.player);
        updatePlayerWater(e.player);
        //TODO: THIS IS UNSAFE, LIKE TOTALLY UNSAFE, WE NEED TO PERFORMANCE THE SHIT OUT OF THIS
        if (e.player.getEntityWorld().getTotalWorldTime() % 15000 == 0) {
            if (e.player.getCapability(LEVEL_CAPABILITY, null).getProgress() < e.player.experienceTotal) {
                e.player.getCapability(LEVEL_CAPABILITY, null).setProgress(e.player.experienceTotal);
            } else if (e.player.getCapability(LEVEL_CAPABILITY, null).getProgress() > e.player.experienceTotal) {
                e.player.getCapability(LEVEL_CAPABILITY, null).setProgress(
                        e.player.getCapability(LEVEL_CAPABILITY, null).getProgress() +
                                (e.player.getCapability(LEVEL_CAPABILITY, null).getProgress() -
                                        e.player.experienceTotal));
            }
        }
    }


    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
        if (e.player.getCapability(FTJFactoryProvider.FTJ_CAPABILITY, null).getFTJ()) {
            ISkillsCapability skills = e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);
            ILevelCapability lvl = e.player.getCapability(LEVEL_CAPABILITY, null);
            ISpechialCapability spe = e.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null);
            Item lvliningCrystal = ItemInit.lvlingCrystall;
            ItemStack lvlingcrystallS = new ItemStack(lvliningCrystal);
            lvlingcrystallS.setCount(1);
            e.player.inventory.addItemStackToInventory(lvlingcrystallS);
            e.player.getCapability(WaterFactoryProvider.WATER_CAPABILITY, null).setWater(100);
            lvl.initNewplayer();
            skills.setAll(10);
            spe.setAll(0);
            e.player.getCapability(FTJFactoryProvider.FTJ_CAPABILITY, null).setFTJ(false);
        }

        ITrigger_item trigger_item = e.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null);
        main.simpleNetworkWrapper.sendTo(new MessageUpdateClientTrigger_Item(trigger_item), (EntityPlayerMP) e.player);


        e.player.getCapability(IAdvProvider.Adv_Inv, null).updateClient(e.player);
        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
        updatePlayerLevel(e.player);
    }

    private void updatePlayerRads(EntityPlayer player) {
        if (!player.world.isRemote) {
            IRadiationCapability rad = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null);
            rad.setRadiation(rad.getRadiation());
            rad.updateClient(player);
        }
    }

    public void updatePlayerWater(EntityPlayer player) {
        if (!player.world.isRemote) {
            IWaterCapability water = player.getCapability(WaterFactoryProvider.WATER_CAPABILITY, null);
            water.setWater(water.getWater());
            water.updateClient(player);
        }
    }

    public void updatePlayerLevel(EntityPlayer player) {
        if (!player.world.isRemote) {
            ILevelCapability level = player.getCapability(LEVEL_CAPABILITY, null);
            level.setLevel(level.getLevel());
            level.setProgress(level.getProgress());
            level.updateClient(player);
        }
    }

    private void updatePlayerSpechial(EntityPlayer player) {
        if (!player.world.isRemote) {
            ILevelCapability level = player.getCapability(LEVEL_CAPABILITY, null);
            ISpechialCapability spe = player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null);
            spe.setAgility(spe.getAgility());
            spe.setCharisma(spe.getCharisma());
            spe.setEndurance(spe.getEndurance());
            spe.setIntelligence(spe.getIntelligence());
            spe.setLuck(spe.getLuck());
            spe.setPerception(spe.getPerception());
            spe.setStreinght(spe.getStreinght());
            spe.updateClient(player);
        }
    }

    private void updatePlayerSkills(EntityPlayer player) {
        if (!player.world.isRemote) {
            ISkillsCapability skill = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);
            skill.setMagic(skill.getMagic());
            skill.setMelee(skill.getMelee());
            skill.setFirearms(skill.getFirearms());
            skill.setEnergyWeapons(skill.getEnergyWeapons());
            skill.setSaddlebag_guns(skill.getSaddlebag_guns());
            skill.setExplosives(skill.getExplosives());
            skill.setRepair(skill.getRepair());
            skill.setMedicine(skill.getMedicine());
            skill.setLockpick(skill.getLockpick());
            skill.setScience(skill.getScience());
            skill.setSneak(skill.getSneak());
            skill.setBarter(skill.getBarter());
            skill.setSurvival(skill.getSurvival());
            skill.updateClient(player);
        }
    }


    @SubscribeEvent
    public void onExit(PlayerEvent.PlayerLoggedOutEvent e) {
//        updatePlayerSpechial(e.player);
//        updatePlayerSkills(e.player);
//        updatePlayerLevel(e.player);
    }

    @SubscribeEvent
    public void onRespawned(PlayerEvent.PlayerRespawnEvent event) {
        EntityPlayer player = event.player;
        updatePlayerSpechial(player);
        updatePlayerSkills(player);
        updatePlayerLevel(player);
        updatePlayerWater(player);
        updatePlayerRads(player);
        PlayerParamsSetup.normalizer(player);
    }
}
