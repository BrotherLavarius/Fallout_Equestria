package com.redsparkle.foe.Init;

import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsDefaultImpl;
import com.redsparkle.foe.capa.RadsFactoryStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by hoijima on 14.12.16.
 */
public class CapabilityInit {
    public static final Capability<IRadiationCapability> RADIATION_CAPABILITY = null;
    public static void radRegistered() {
    //public static void radRegistered(Capability<IRadiationCapability> cap) {

        System.out.println("I-----------------------------------I");
        System.out.println(" RadiationCapability was initialized ");
        System.out.println("        YAY FOR THOSE ATOMS!         ");
        System.out.println("        You will die, enjoy          ");
        System.out.println("I-----------------------------------I");
        CapabilityManager.INSTANCE.register(IRadiationCapability.class, new RadsFactoryStorage(), RadsDefaultImpl.class);
    }
    public static void InitCommon()
    {

    }
    public static void postInitCommon()
    {

    }
}
