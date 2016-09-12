package com.redsparkle.foe;


import com.example.examplemod.TestCapabilityMod;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.FOECapabilitiesInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.redsparkle.foe.FOECapabilitiesInit.RADIATION_CAPABILITY;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandler {


    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        // Having the Provider implement the cap is not recomended as this creates a hard dep on the cap interface.
        // And doesnt allow for sidedness.
        // But as this is a example and we dont care about that here we go.
        class Provider implements ICapabilityProvider, TestCapabilityMod.IExampleCapability
        {
            private Entity te;

            Provider(Entity te)
            {
                this.te = te;
            }
            @Override
            public boolean hasCapability(Capability<?> capability, EnumFacing facing)
            {
                return RADIATION_CAPABILITY != null && capability == RADIATION_CAPABILITY;
            }
            @Override
            public <T> T getCapability(Capability<T> capability, EnumFacing facing)
            {
                if (RADIATION_CAPABILITY != null && capability == RADIATION_CAPABILITY) return RADIATION_CAPABILITY.cast((IRadiationCapability) this);
                return null;
            }
            @Override
            public String getOwnerType() {
                return te.getClass().getName();
            }
        }

        //Attach it! The resource location MUST be unique it's recomneded that you tag it with your modid and what the cap is.
        event.addCapability(new ResourceLocation(main.MODID+":Radiation_CAPABILITY"), new Provider(event.getEntity()));
        
    }



}
