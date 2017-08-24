package com.redsparkle.foe.events;

import com.redsparkle.api.Capability.Player.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.rad.IRadiationCapability;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.IWaterCapability;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.Init.FluidsInit;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.Init.PotionInit;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.redsparkle.api.Capability.Player.level.LevelFactoryProvider.LEVEL_CAPABILITY;

/**
 * Created by hoijima on 04.08.17.
 */
public class UpdateEvents {
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

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
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
        e.player.getCapability(IAdvProvider.Adv_Inv, null).updateClient(e.player);
        updatePlayerSpechial(e.player);
        updatePlayerSkills(e.player);
        updatePlayerLevel(e.player);
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

    @SubscribeEvent
    public void in_Gas(GetCollisionBoxesEvent event) {


        if (event.getEntity() instanceof EntityLivingBase) {

            EntityLivingBase player = (EntityLivingBase) event.getEntity();
            AxisAlignedBB axisalignedbb = event.getEntity().getEntityBoundingBox();
            double d0 = axisalignedbb.maxY + 0.002D;
            int i = MathHelper.floor(axisalignedbb.minX);
            int j = MathHelper.ceil(axisalignedbb.maxX);
            int k = MathHelper.floor(axisalignedbb.maxY);
            int l = MathHelper.ceil(d0);
            int i1 = MathHelper.floor(axisalignedbb.minZ);
            int j1 = MathHelper.ceil(axisalignedbb.maxZ);
            boolean flag = false;
            BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

            try {
                for (int k1 = i; k1 < j; ++k1) {
                    for (int l1 = k; l1 < l; ++l1) {
                        for (int i2 = i1; i2 < j1; ++i2) {
                            blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
                            IBlockState iblockstate = event.getWorld().getBlockState(blockpos$pooledmutableblockpos);

                            if (iblockstate.getBlock() == FluidsInit.PINKCLOUD.getBlock()) {
                                if (iblockstate.getValue(BlockLiquid.LEVEL).intValue() != 0 && !player.getActivePotionEffects().contains(PotionInit.STATICPOISON)) {

                                    player.addPotionEffect(new PotionEffect(PotionInit.STATICPOISON, 100));
                                }
                            }
                        }
                    }
                }
            } finally {
                blockpos$pooledmutableblockpos.release();
            }


        }
    }
}
