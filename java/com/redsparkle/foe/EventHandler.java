package com.redsparkle.foe;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.redsparkle.foe.main.MODID;
import static com.redsparkle.foe.main.RADIATION_CAPABILITY;

/**
 * Created by hoijima desu on 07.08.16 desu.
 */
public class EventHandler {
    @SubscribeEvent
    public void onEntityConstruct(AttachCapabilitiesEvent evt)
    {
        evt.addCapability(new ResourceLocation(MODID, "IRadiation"), new ICapabilitySerializable<NBTBase.NBTPrimitive>()
        {
            IRadiation inst = RADIATION_CAPABILITY.getDefaultInstance();
            @Override
            public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                return capability == RADIATION_CAPABILITY;
            }

            @Override
            public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                return capability == RADIATION_CAPABILITY ? RADIATION_CAPABILITY.<T>cast(inst) : null;
            }

            @Override
            public NBTBase.NBTPrimitive serializeNBT() {
                return (NBTBase.NBTPrimitive)RADIATION_CAPABILITY.getStorage().writeNBT(RADIATION_CAPABILITY, inst, null);
            }

            @Override
            public void deserializeNBT(NBTBase.NBTPrimitive nbt) {
                RADIATION_CAPABILITY.getStorage().readNBT(RADIATION_CAPABILITY, inst, null, nbt);
            }
        });
    }
}
