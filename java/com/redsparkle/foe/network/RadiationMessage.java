package com.redsparkle.foe.network;


import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by NENYN on 04.12.2016.
 */
public class RadiationMessage implements IMessage {

    public int toSend;

    // A default constructor is always required
    public RadiationMessage() {}

    public RadiationMessage(int toSend) {
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
