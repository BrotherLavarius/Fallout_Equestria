package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.skills.ISkillsCapability;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 3/1/2017.
 */
public class MessageUpdateClientServerSkills implements IMessage {
    public NonNullList<Integer> skills = NonNullList.withSize(13, 0);

    public MessageUpdateClientServerSkills() {
    }

    public MessageUpdateClientServerSkills(ISkillsCapability skills) {
        /**
         Magic
         Melee_Weapons
         Firearms
         EneryWeapons
         Saddlebag_Guns
         Explosives
         Repair
         Medicine
         Lockpicking
         Science
         Sneak
         Barter
         Survival
         */
        //TODO finish this it gets NULL
        this.skills.set(0, new Integer(skills.getMagic()));
        this.skills.set(1, new Integer(skills.getMelee()));
        this.skills.set(2, new Integer(skills.getFirearms()));
        this.skills.set(3, new Integer(skills.getEnergyWeapons()));
        this.skills.set(4, new Integer(skills.getSaddlebag_guns()));
        this.skills.set(5, new Integer(skills.getExplosives()));
        this.skills.set(6, new Integer(skills.getRepair()));
        this.skills.set(7, new Integer(skills.getMedicine()));
        this.skills.set(8, new Integer(skills.getLockpick()));
        this.skills.set(9, new Integer(skills.getScience()));
        this.skills.set(10, new Integer(skills.getSneak()));
        this.skills.set(11, new Integer(skills.getBarter()));
        this.skills.set(12, new Integer(skills.getSurvival()));
    }

    public MessageUpdateClientServerSkills(Integer[] Skills) {
        for (int i = 0; i < 13; i++) {
            this.skills.set(i, Skills[i]);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        for (int i = 0; i < 13; i++) {
            skills.set(i, buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 13; i++) {
            buf.writeInt(skills.get(i));
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
