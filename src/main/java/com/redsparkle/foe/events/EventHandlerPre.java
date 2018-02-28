package com.redsparkle.foe.events;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Player.FirtsTimeJoin.FTJFactoryProvider;
import com.redsparkle.api.Capability.Player.FirtsTimeJoin.IFTJCapability;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.Render.RenderProvider;
import com.redsparkle.api.Capability.Player.level.ILevelCapability;
import com.redsparkle.api.Capability.Player.level.LevelFactoryProvider;
import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.Player.spechial.ISpechialCapability;
import com.redsparkle.api.Capability.Player.spechial.SpechialFactoryProvider;
import com.redsparkle.api.Capability.Player.water.WaterFactoryProvider;
import com.redsparkle.api.Capability.block.Locks.LockFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaddleBagAmmo;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.api.utils.PlayerParamsSetup;
import com.redsparkle.foe.Init.PotionInit;
import com.redsparkle.foe.block.safe_TE;
import com.redsparkle.foe.main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandlerPre {
    public static boolean canHaveAttributes(Entity entity) {
        return entity instanceof EntityLivingBase;
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
                event.addCapability(new ResourceLocation(main.MODID + ":trigger_cap"), new ITrigger_item_Provider());
                event.addCapability(new ResourceLocation(main.MODID + ":render_cap"), new RenderProvider());

            }
        }
    }


    @SubscribeEvent
    public void onAddCapabilitiesBlock(AttachCapabilitiesEvent<TileEntity> event) {
        if (event.getObject() instanceof safe_TE) {
            event.addCapability(new ResourceLocation(main.MODID + ":lock_capability"), new LockFactoryProvider());
        }

    }
    @SubscribeEvent
    public void onAddCapabilitiesItemStack(AttachCapabilitiesEvent<ItemStack> e) {
        if (e.getObject().getItem() instanceof Item_AmmoHolder || e.getObject().getItem() instanceof Item_SaddleBagAmmo) {
            if (!e.getObject().hasCapability(AmmoFactoryProvider.AMMO_STORAGE, null)) {
                e.addCapability(new ResourceLocation(main.MODID + ":ammo_capability"), new AmmoFactoryProvider());
            }
        }
        if (e.getObject().getItem() instanceof Item_Firearm || e.getObject().getItem() instanceof Item_SaggleBagGun) {
            if (!e.getObject().hasCapability(GunFactoryProvider.GUN, null)) {
                e.addCapability(new ResourceLocation(main.MODID + ":gun_capability"), new GunFactoryProvider());
            }
        }

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
                newSkills.setAttribute(originalSkills.getFullMap());
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

    private void onUpdate(TickEvent.WorldTickEvent event) {
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerContainerEvent.Open event) {
        event.getContainer();
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDamageEvent(LivingAttackEvent event) {
        if (event.getEntityLiving().isPotionActive(PotionInit.STATICPOISON) && event.isCancelable())
            event.setCanceled(false);
    }
}
