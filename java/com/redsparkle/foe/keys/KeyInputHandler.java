package com.redsparkle.foe.keys;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunReload;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler
{
    public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public EntityPlayer player = Minecraft.getMinecraft().player;
    public ItemStack heldItem;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {


            if (testkey.reload.isPressed()){
                main.simpleNetworkWrapper.sendToServer(new MessageGunReload());
            }



            //Minecraft.getMinecraft().player.sendChatMessage("Hello! I got: " + heldItem.getUnlocalizedName()+ " in my hand");
            //Minecraft.getMinecraft().player.sendChatMessage("Is this a gun?: "+heldItem.getTagCompound().getBoolean("isgun"));

    }




}