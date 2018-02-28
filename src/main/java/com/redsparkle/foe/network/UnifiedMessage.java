package com.redsparkle.foe.network;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Base64;

public class UnifiedMessage implements IMessage {

    public String json = "";

    public UnifiedMessage() {
    }

    /**
     * Json Message template
     * {
     * "type": "type of message",
     * "details": {
     * "param_one": "",
     * "param_two": ""
     * }
     * }
     *
     * or
     *
     * {
     * "type": "type of message",
     * "details": ""
     * }
     *
     * @param object
     */
    public UnifiedMessage(JsonObject object) {
        json = object.toString();
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        json = new String(Base64.getDecoder().decode(ByteBufUtils.readUTF8String(buf)));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, Base64.getEncoder().withoutPadding().encodeToString(json.getBytes()));
    }

    public static class HandlerServer implements IMessageHandler<UnifiedMessage, IMessage> {


        @Override
        public IMessage onMessage(UnifiedMessage message, MessageContext ctx) {
            JsonObject object = new JsonParser().parse(message.json).getAsJsonObject();
            UnifiedProcessor.GP(object, ctx, "server");
            return null;
        }
    }

    public static class HandlerClient implements IMessageHandler<UnifiedMessage, IMessage> {
        @Override
        public IMessage onMessage(UnifiedMessage message, MessageContext ctx) {
            JsonObject object = new JsonParser().parse(message.json).getAsJsonObject();
            UnifiedProcessor.GP(object, ctx, "client");
            return null;
        }
    }
}
