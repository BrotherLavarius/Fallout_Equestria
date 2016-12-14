package com.redsparkle.foe;

import com.redsparkle.foe.Init.ClientOnlyStartup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by hoijima on 14.12.16.
 */
public class ClientOnlyProxy extends CommonProxy {

    public void preInit(){
        super.preInit();
        ClientOnlyStartup.preInitClientOnly();
    }

    public void Init() {
        super.init();
        ClientOnlyStartup.initClientOnly();
    }


    public void postInit() {
        super.postInit();
        ClientOnlyStartup.postInitClientOnly();
    }
    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
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
