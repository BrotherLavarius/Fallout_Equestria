package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 04.12.2016.
 */
public class MyMessageClass implements IMessage {
    public int toSend;

    // A default constructor is always required
    public MyMessageClass() {}

    public MyMessageClass(int toSend) {
        this.toSend = toSend;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // Writes the int into the buf
        buf.writeInt(toSend);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
        toSend = buf.readInt();
    }
}
