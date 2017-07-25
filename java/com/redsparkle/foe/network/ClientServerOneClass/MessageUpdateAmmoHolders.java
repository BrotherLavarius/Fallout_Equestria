package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 25.07.17.
 */
public class MessageUpdateAmmoHolders implements IMessage {


    /**
     * @param MaxAmmo - just in case;
     * @param ammo - main sync;
     * @param slot - slot index , self explanatory;
     * @param invType  - 0 for plain , 1 for Advanced inventory
     *
     */
    public int maxAmmo=0;
    public int ammo=0;
    public int slot=0;
    public int invType=0;


    public MessageUpdateAmmoHolders(){}

    public MessageUpdateAmmoHolders(int ammo, int maxammo, int slot, int invtype){
        this.ammo = ammo;
        this.maxAmmo=maxammo;
        this.slot=slot;
        this.invType=invtype;
    }


    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(maxAmmo);
        buf.writeInt(ammo);
        buf.writeInt(slot);
        buf.writeInt(invType);
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        maxAmmo = buf.readInt();
        ammo = buf.readInt();
        slot = buf.readInt();
        invType = buf.readInt();
    }



    public static class HandlerClient implements IMessageHandler<MessageUpdateAmmoHolders, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateAmmoHolders message, MessageContext ctx) {
            ClientOnlyProxy.handleSync_AmmoItems(message);
            return null;
        }
    }
}
