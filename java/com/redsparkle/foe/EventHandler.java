package com.redsparkle.foe;


import com.redsparkle.foe.capa.DefaultRadsFactory;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by hoijima on 07.09.16.
 */
public class EventHandler {

    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent.Entity e) {

        if (!e.getEntity().hasCapability(RadsFactoryProvider.RADIATION, null) && e.getEntity() instanceof EntityPlayer)
            e.addCapability(new ResourceLocation(main.MODID + "Capability"), new RadsFactoryProvider(new DefaultRadsFactory()));
        }
}
