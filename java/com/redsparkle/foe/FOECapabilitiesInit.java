package com.redsparkle.foe;

import com.redsparkle.foe.capa.IRadiationCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Created by hoijima on 12.09.16.
 */
public class FOECapabilitiesInit {
    @CapabilityInject(IRadiationCapability.class)
    public static final Capability<IRadiationCapability> RADIATION_CAPABILITY = null;
}
