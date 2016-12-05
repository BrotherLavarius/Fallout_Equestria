package com.redsparkle.foe.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by NENYN on 12/5/2016.
 */

public class TutorialMessage implements IMessage {
    public int extremelyImportantInteger;

    public TutorialMessage() {}

    public TutorialMessage(int a) {
        this.extremelyImportantInteger = a;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(extremelyImportantInteger);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.extremelyImportantInteger = buf.readInt();
    }

}