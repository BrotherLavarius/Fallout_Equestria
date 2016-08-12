package com.redsparkle.foe;

import com.redsparkle.foe.capabilities.CapabilityRadiation;
import com.redsparkle.foe.packets.client.PacketDispatcher;
import com.redsparkle.foe.packets.client.SyncRadsData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


/**
 * Created by hoijima desu on 12.08.16 desu.
 */
public class FoeEventHandler {
    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent.Entity event) {
        event.addCapability(new ResourceLocation(main.MODID, "IRadiation"), new ICapabilitySerializable<NBTTagCompound>() {
            CapabilityRadiation.IRadiation inst = CapabilityRadiation.RADS.getDefaultInstance();

            @Override
            public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                return capability == CapabilityRadiation.RADS;
            }

            @Override
            public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                return capability == CapabilityRadiation.RADS ? (T) inst : null;
            }

            @Override
            public NBTTagCompound serializeNBT() {
                return (NBTTagCompound) CapabilityRadiation.RADS.getStorage().writeNBT(CapabilityRadiation.RADS, inst, null);
            }

            @Override
            public void deserializeNBT(NBTTagCompound nbt) {
                CapabilityRadiation.RADS.getStorage().readNBT(CapabilityRadiation.RADS, inst, null, nbt);
            }
        });
    }

    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent event) {
        // If you have any non-DataWatcher fields in your extended properties that
        // need to be synced to the client, you must send a packet each time the
        // player joins the world; this takes care of dying, changing dimensions, etc.
        if (event.getEntity() instanceof EntityPlayerMP) {
            PacketDispatcher.sendTo(new SyncRadsData(event.getEntity().getCapability(CapabilityRadiation.RADS, null)), (EntityPlayerMP) event.getEntity());

        }

    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            final CapabilityRadiation.IRadiation radsOriginal = event.getOriginal().getCapability(CapabilityRadiation.RADS, null);
            final CapabilityRadiation.IRadiation radsNew = event.getEntityPlayer().getCapability(CapabilityRadiation.RADS, null);
            radsNew.setRadiation(radsOriginal.getRadiation());
            radsNew.setMaxRadiation(radsOriginal.getMaxRadiation());
        }
    }
}
