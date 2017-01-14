package com.redsparkle.foe;

import com.redsparkle.foe.Init.ClientOnlyStartup;
import com.redsparkle.foe.keys.KeyInputHandler;
import com.redsparkle.foe.keys.testkey;
import com.redsparkle.foe.network.MessageUpdateClientRads;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by hoijima on 14.12.16.
 */
public class ClientOnlyProxy extends CommonProxy {

    public void preInit() {
        super.preInit();
        ClientOnlyStartup.preInitClientOnly();
        testkey.register();
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

    public void Init() {
        super.init();
        main.simpleNetworkWrapper.registerMessage(MessageUpdateClientRads.Handler.class, MessageUpdateClientRads.class, main.RAIATION_CAPABILITY_MESSAGE_ID_CLIENT, Side.CLIENT);

        ClientOnlyStartup.initClientOnly();
    }


    public void postInit() {
        super.postInit();
        ClientOnlyStartup.postInitClientOnly();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.interactionManager.isCreative();
        } else if (player instanceof EntityPlayerSP) {
            return Minecraft.getMinecraft().playerController.isInCreativeMode();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }
}
