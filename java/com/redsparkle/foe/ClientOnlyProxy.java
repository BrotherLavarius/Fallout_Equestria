package com.redsparkle.foe;

import com.redsparkle.foe.Init.ClientOnlyStartup;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsFactoryProvider;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.keys.KeyInputHandler;
import com.redsparkle.foe.keys.testkey;
import com.redsparkle.foe.network.MessageGunReloadToClient;
import com.redsparkle.foe.network.MessageUpdateClientRads;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
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

    public static void handleRadMessage(MessageUpdateClientRads message) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IRadiationCapability rad = RadsFactoryProvider.instanceFor(player);
            rad.setRadiation(message.radiation);
            /** DEBUG MESSAGE ENABLER
             * System.out.println("Client: "+message.radiation);
             */

        });
    }

    public static void handleGunMessageFire(Item gun){

    }
    public static void handleGundMessageReload(MessageGunReloadToClient message){
        Minecraft.getMinecraft().addScheduledTask(() -> {
        System.out.println(message.gun);
        EntityPlayer player = Minecraft.getMinecraft().player;
        //if ( gun. instanceof TenMM){
        //    world.playSound(player, player.getPosition(), SoundInit.tenMMReload, SoundCategory.HOSTILE, 1.0F, 1.0F);

        });
    }

}


