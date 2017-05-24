package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import com.redsparkle.foe.capa.spechial.ISpechialCapability;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 3/1/2017.
 */
public class MessageUpdateClientServerSPECHIAL implements IMessage {
    public int Streinght, Perception, Endurance, Charisma, Intelligence, Agility, Luck;
    public int[] params = {
            this.Streinght,
            this.Perception,
            this.Endurance,
            this.Charisma,
            this.Intelligence,
            this.Agility,
            this.Luck
    };

    public MessageUpdateClientServerSPECHIAL() {
    }

    public MessageUpdateClientServerSPECHIAL(ISpechialCapability spechial) {

        this.Streinght = spechial.getStreinght();
        this.Perception = spechial.getPerception();
        this.Endurance = spechial.getEndurance();
        this.Charisma = spechial.getCharisma();
        this.Intelligence = spechial.getIntelligence();
        this.Agility = spechial.getAgility();
        this.Luck = spechial.getLuck();

    }

    public MessageUpdateClientServerSPECHIAL(int[] finished) {
        this.Streinght = finished[0];
        this.Perception = finished[1];
        this.Endurance = finished[2];
        this.Charisma = finished[3];
        this.Intelligence = finished[4];
        this.Agility = finished[5];
        this.Luck = finished[6];
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        Streinght = buf.readInt();
        Perception = buf.readInt();
        Endurance = buf.readInt();
        Charisma = buf.readInt();
        Intelligence = buf.readInt();
        Agility = buf.readInt();
        Luck = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(Streinght);
        buf.writeInt(Perception);
        buf.writeInt(Endurance);
        buf.writeInt(Charisma);
        buf.writeInt(Intelligence);
        buf.writeInt(Agility);
        buf.writeInt(Luck);
    }

    public static class HandlerClient implements IMessageHandler<MessageUpdateClientServerSPECHIAL, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientServerSPECHIAL message, MessageContext ctx) {
            ClientOnlyProxy.handleSpechialMessage(message);
            return null;
        }

    }

    public static class HandlerServer implements IMessageHandler<MessageUpdateClientServerSPECHIAL, IMessage> {

        @Override
        public IMessage onMessage(MessageUpdateClientServerSPECHIAL message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleSpechialMessage(message, playerMP);
            return null;
        }

    }
}
