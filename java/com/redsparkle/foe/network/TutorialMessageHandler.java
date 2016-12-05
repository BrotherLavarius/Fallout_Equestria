package com.redsparkle.foe.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 12/5/2016.
 */
public class TutorialMessageHandler implements IMessageHandler<TutorialMessage, IMessage> {

    @Override
    public IMessage onMessage(TutorialMessage message, MessageContext ctx) {
        System.out.println(message.extremelyImportantInteger);
        return null;
    }

}