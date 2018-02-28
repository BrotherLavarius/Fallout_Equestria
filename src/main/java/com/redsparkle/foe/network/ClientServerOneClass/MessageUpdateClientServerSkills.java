package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.api.Capability.Player.skills.Skill_names;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by hoijima on 3/1/2017.
 */
public class MessageUpdateClientServerSkills implements IMessage {

    public HashMap<String, Integer> map = new HashMap<>();
    public boolean type;

    public MessageUpdateClientServerSkills() {
    }

    public MessageUpdateClientServerSkills(LinkedHashMap<String, Integer> skills) {
        map = skills;
    }
    public MessageUpdateClientServerSkills(ISkillsCapability skills) {
        map = skills.getFullMap();
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        for (Skill_names skillName : Skill_names.values()) {
            map.put(skillName.getName(), buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {

        for (Skill_names skillName : Skill_names.values()) {
            buf.writeInt(map.get(skillName.getName()));
        }
    }

    public static class HandlerClient implements IMessageHandler<MessageUpdateClientServerSkills, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateClientServerSkills message, MessageContext ctx) {
            ClientOnlyProxy.handleSkillsMessage(message);
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageUpdateClientServerSkills, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateClientServerSkills message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            DedicatedServerProxy.handleSkillsMessage(message, playerMP);
            return null;
        }
    }

    public static class ServerOnLVLUP implements IMessageHandler<MessageUpdateClientServerSkills, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateClientServerSkills message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            DedicatedServerProxy.handleSkillsLVLUPMessage(message, playerMP);
            return null;
        }
    }
}
