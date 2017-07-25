package com.redsparkle.foe.events;


import com.redsparkle.api.Capability.Player.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.api.Capability.Player.FirtsTimeJoin.IFTJCapability;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.rad.IRadiationCapability;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.api.Capability.Player.level.LevelFactoryProvider.LEVEL_CAPABILITY;
import static net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import static net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;


/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {

    public static boolean canHaveAttributes(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean canHaveAttributes(Item item) {
        return (item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemBow
                || item instanceof ItemArmor || item instanceof ItemShield);
    }


    @SubscribeEvent
    public void onAddCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {

        if (canHaveAttributes(event.getObject())) {
            EntityLivingBase ent = (EntityLivingBase) event.getObject();

            if (ent instanceof EntityPlayer) {
                event.addCapability(new ResourceLocation(main.MODID + ":radiation_capability"), new RadsFactoryProvider());
                event.addCapability(new ResourceLocation(main.MODID + ":water_capability"), new WaterFactoryProvider());
                event.addCapability(new ResourceLocation(main.MODID + ":spechial_capability"), new SpechialFactoryProvider());
                event.addCapability(new ResourceLocation(main.MODID + ":skills_capability"), new SkillsFactoryProvider());
                event.addCapability(new ResourceLocation(main.MODID + ":level_capability"), new LevelFactoryProvider());
                event.addCapability(new ResourceLocation(main.MODID + ":ftj_capability"), new FTJFactoryProvider());
                event.addCapability(new ResourceLocation(main.MODID + ":adv_inv_capability"), new IAdvProvider());
            }
        }


    }

    @SubscribeEvent
    public void onAddCapabilitiesItemStack(AttachCapabilitiesEvent<Item> e) {
        if (canHaveAttributes(e.getObject())) {
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        //if (e.phase != TickEvent.Phase.END) return;
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
            //System.out.println("RUnning Update");

        }
    }

    @SubscribeEvent
    public void onJoin(PlayerLoggedInEvent e) {
        if (e.player.getCapability(FTJFactoryProvider.FTJ_CAPABILITY, null).getFTJ()) {
            ISkillsCapability skills = e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);
            ILevelCapability lvl = e.player.getCapability(LEVEL_CAPABILITY, null);
            ISpechialCapability spe = e.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null);
            Item lvliningCrystal = GlobalItemArray_For_init.AllInit[1];
            ItemStack lvlingcrystallS = new ItemStack(lvliningCrystal);
            lvlingcrystallS.setCount(1);
            e.player.inventory.addItemStackToInventory(lvlingcrystallS);
            e.player.getCapability(WaterFactoryProvider.WATER_CAPABILITY, null).setWater(100);

            lvl.initNewplayer();
            skills.setAll(10);
            spe.setAll(0);
            e.player.getCapability(FTJFactoryProvider.FTJ_CAPABILITY, null).setFTJ(false);

        }
        e.player.getCapability(IAdvProvider.Adv_Inv, null).updateClient(e.player);
        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
        updatePlayerLevel(e.player);


    }

    @SubscribeEvent
    public void onExit(PlayerLoggedOutEvent e) {
        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
        updatePlayerLevel(e.player);
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

    @SubscribeEvent
    public void onPlayerCloning(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        if (event.isWasDeath()) {

            if (event.getEntityPlayer().hasCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null)) {
                ILevelCapability originalLvl = event.getOriginal().getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null);
                ILevelCapability newPLayerLvl = event.getEntityPlayer().getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null);


                newPLayerLvl.setLevel(originalLvl.getLevel());
                newPLayerLvl.setProgress(originalLvl.getProgress());
            }
            if (event.getEntityPlayer().hasCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null)) {
                ISpechialCapability originalSpecial = event.getOriginal().getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null);
                ISpechialCapability newPlayerSpecial = event.getEntityPlayer().getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null);

                newPlayerSpecial.setAgility(originalSpecial.getAgility());
                newPlayerSpecial.setCharisma(originalSpecial.getCharisma());
                newPlayerSpecial.setEndurance(originalSpecial.getEndurance());
                newPlayerSpecial.setIntelligence(originalSpecial.getIntelligence());
                newPlayerSpecial.setLuck(originalSpecial.getLuck());
                newPlayerSpecial.setPerception(originalSpecial.getPerception());
                newPlayerSpecial.setStreinght(originalSpecial.getStreinght());
            }
            if (event.getEntityPlayer().hasCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null)) {

                ISkillsCapability originalSkills = event.getOriginal().getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);
                ISkillsCapability newSkills = event.getEntityPlayer().getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null);

                newSkills.setMagic(originalSkills.getMagic());
                newSkills.setMelee(originalSkills.getMelee());
                newSkills.setFirearms(originalSkills.getFirearms());
                newSkills.setEnergyWeapons(originalSkills.getEnergyWeapons());
                newSkills.setSaddlebag_guns(originalSkills.getSaddlebag_guns());
                newSkills.setExplosives(originalSkills.getExplosives());
                newSkills.setRepair(originalSkills.getRepair());
                newSkills.setMedicine(originalSkills.getMedicine());
                newSkills.setLockpick(originalSkills.getLockpick());
                newSkills.setScience(originalSkills.getScience());
                newSkills.setSneak(originalSkills.getSneak());
                newSkills.setBarter(originalSkills.getBarter());
            }
            if (event.getEntityPlayer().hasCapability(FTJFactoryProvider.FTJ_CAPABILITY, null)) {

                IFTJCapability ftjO = event.getOriginal().getCapability(FTJFactoryProvider.FTJ_CAPABILITY, null);
                IFTJCapability ftjN = event.getEntityPlayer().getCapability(FTJFactoryProvider.FTJ_CAPABILITY, null);

                ftjN.setFTJ(ftjO.getFTJ());
            }
            if (event.getEntityPlayer().hasCapability(WaterFactoryProvider.WATER_CAPABILITY, null)) {
                event.getEntityPlayer().getCapability(WaterFactoryProvider.WATER_CAPABILITY, null).setWater(100);
            }

            PlayerParamsSetup.normalizer(event.getEntityPlayer());

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

    private void onUpdate(TickEvent.WorldTickEvent event) {

    }

    @SubscribeEvent
    public void onPlayerTick(PlayerContainerEvent.Open event) {
        event.getContainer();
    }
}
    
    