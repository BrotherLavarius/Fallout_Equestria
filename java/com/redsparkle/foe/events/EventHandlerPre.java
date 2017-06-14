package com.redsparkle.foe.events;


import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.capa.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.foe.capa.level.ILevelCapability;
import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.rad.IRadiationCapability;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.skills.ISkillsCapability;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.capa.water.IWaterCapability;
import com.redsparkle.foe.capa.water.WaterFactoryProvider;
import com.redsparkle.foe.main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.foe.capa.level.LevelFactoryProvider.LEVEL_CAPABILITY;


/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {


    @SubscribeEvent
    public void onAddCapabilitiesEntity(AttachCapabilitiesEvent<Entity> e) {

            if (canHaveAttributes(e.getObject()))
            {
                EntityLivingBase ent = (EntityLivingBase) e.getObject();

                if (ent instanceof EntityPlayer){
                    e.addCapability(new ResourceLocation(main.MODID + ":Radiation_CAPABILITY"), new RadsFactoryProvider());
                    e.addCapability(new ResourceLocation(main.MODID + ":Water_CAPABILITY"),new WaterFactoryProvider());
                    e.addCapability(new ResourceLocation(main.MODID + ":Spechial_CAPABILITY"), new SpechialFactoryProvider());
                    e.addCapability(new ResourceLocation(main.MODID + ":SKILLS_CAPABILITY"), new SkillsFactoryProvider());
                    e.addCapability(new ResourceLocation(main.MODID + ":LEVEL_CAPABILITY"), new LevelFactoryProvider());
                    e.addCapability(new ResourceLocation(main.MODID + ":FTJ_CAPABILITY"), new FTJFactoryProvider());
                }
        }

    }
    @SubscribeEvent
    public void onAddCapabilitiesItemStack(AttachCapabilitiesEvent<Item> e)
    {
        if (canHaveAttributes(e.getObject()))
        {
        }
    }



    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        //if (e.phase != TickEvent.Phase.END) return;
        updatePlayerRads(e.player);
        updatePlayerWater(e.player);



        //TODO: THIS IS UNSAFE, LIKE TOTALLY UNSAFE, WE NEED TO PERFORMANCE THE SHIT OUT OF THIS
        if(e.player.getEntityWorld().getTotalWorldTime() % 15000 == 0){
            if (e.player.getCapability(LEVEL_CAPABILITY,null).getProgress() < e.player.experienceTotal){
                e.player.getCapability(LEVEL_CAPABILITY,null).setProgress(e.player.experienceTotal);
            } else
                if (e.player.getCapability(LEVEL_CAPABILITY,null).getProgress() > e.player.experienceTotal){
                    e.player.getCapability(LEVEL_CAPABILITY,null).setProgress(
                            e.player.getCapability(LEVEL_CAPABILITY,null).getProgress() +
                                    (e.player.getCapability(LEVEL_CAPABILITY,null).getProgress() -
                                    e.player.experienceTotal));
                }
            System.out.println("RUnning Update");

        }
    }
    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e){
        if(e.player.getCapability(FTJFactoryProvider.FTJ_CAPABILITY,null).getFTJ())
        {

            Item lvliningCrystal = ItemInit.lvlingCrystall;
            ItemStack lvlingcrystallS = new ItemStack(lvliningCrystal);
            lvlingcrystallS.setCount(1);
            e.player.inventory.addItemStackToInventory(lvlingcrystallS);
            e.player.getCapability(WaterFactoryProvider.WATER_CAPABILITY,null).setWater(100);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setBigGuns(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setSmallGuns(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setEnergyWeapons(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setExplosives(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setMeleeWeapons(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setUnarmed(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setMedicine(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setLockpick(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setRepair(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setScience(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setSneak(10);
            e.player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).setBarter(10);

            e.player.getCapability(FTJFactoryProvider.FTJ_CAPABILITY,null).setFTJ(false);
        }

        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
        updatePlayerLevel(e.player);


        
    }

    @SubscribeEvent
    public void onExit(PlayerEvent.PlayerLoggedOutEvent e){
        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
        updatePlayerLevel(e.player);
    }
    @SubscribeEvent
    public void onYOUDIEEED(LivingDeathEvent event){

    }




    private void updatePlayerSpechial(EntityPlayer player) {
        if (!player.world.isRemote) {
            ILevelCapability level = player.getCapability(LEVEL_CAPABILITY, null);
            ISpechialCapability spe = player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY,null);
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
            ISkillsCapability skill = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null);
            skill.setBigGuns(skill.getBigGuns());
            skill.setSmallGuns(skill.getSmallGuns());
            skill.setEnergyWeapons(skill.getEnergyWeapons());
            skill.setExplosives(skill.getExplosives());
            skill.setMeleeWeapons(skill.getMeleeWeapons());
            skill.setUnarmed(skill.getUnarmed());
            skill.setMedicine(skill.getMedicine());
            skill.setLockpick(skill.getLockpick());
            skill.setRepair(skill.getRepair());
            skill.setScience(skill.getScience());
            skill.setSneak(skill.getSneak());
            skill.setBarter(skill.getBarter());

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
    private void updatePlayerWater(EntityPlayer player) {
        if (!player.world.isRemote) {
            IWaterCapability water = player.getCapability(WaterFactoryProvider.WATER_CAPABILITY, null);
            water.setWater(water.getWater());
            water.updateClient(player);
        }
    }

    private void updatePlayerLevel(EntityPlayer player) {
        if (!player.world.isRemote) {
            ILevelCapability level = player.getCapability(LEVEL_CAPABILITY, null);
            level.setLevel(level.getLevel());
            level.setProgress(level.getProgress());
            level.updateClient(player);
        }
    }


    public static boolean canHaveAttributes(Entity entity)
    {
        if (entity instanceof EntityLivingBase)
            return true;
        return false;
    }

    public static boolean canHaveAttributes(Item item)
    {
        if ((item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemBow
                || item instanceof ItemArmor || item instanceof ItemShield))
            return true;
        return false;
    }

    private void onUpdate(TickEvent.WorldTickEvent event) {

    }
}
    
    