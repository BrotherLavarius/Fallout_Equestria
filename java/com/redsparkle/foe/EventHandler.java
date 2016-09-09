package com.redsparkle.foe;


import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsFactory;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandler {

    @SubscribeEvent
    public void on(AttachCapabilitiesEvent.Entity event) {


        // Having the Provider implement the cap is not recomended as this creates a hard dep on the cap interface.
        // And doesnt allow for sidedness.
        // But as this is a example and we dont care about that here we go.
        class Provider implements ICapabilityProvider, IRadiationCapability {

            public Provider(Entity entity) {
            }

            @Override
            public int getRadiation() {
                return 0;
            }

            @Override
            public int removeRadiation() {
                return 0;
            }

            @Override
            public int setRadiation() {
                return 0;
            }

            @Override
            public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
                return false;
            }

            @Override
            public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
                return null;
            }
        }

        //Attach it! The resource location MUST be unique it's recomneded that you tag it with your modid and what the cap is.
        event.addCapability(new ResourceLocation(main.MODID + ":RadiationCapability"), new Provider(event.getEntity()));

    }
}
