package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import com.redsparkle.foe.capa.skills.ISkillsCapability;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 3/1/2017.
 */
public class MessageUpdateClientServerSkills implements IMessage {
    public Integer
            BigGuns,
            SmallGuns,
            EnergyWeapons,
            Explosives,
            MeleeWeapons,
            Unarmed,
            Medicine,
            Lockpick,
            Repair,
            Science,
            Sneak,
            Barter;
    

    public MessageUpdateClientServerSkills() {
    }

    public MessageUpdateClientServerSkills(ISkillsCapability skills) {

        this.BigGuns=skills.getBigGuns();
        this.SmallGuns=skills.getSmallGuns();
        this.EnergyWeapons=skills.getEnergyWeapons();
        this.Explosives=skills.getExplosives();
        this.MeleeWeapons=skills.getMeleeWeapons();
        this.Unarmed=skills.getUnarmed();
        this.Medicine=skills.getMedicine();
        this.Lockpick=skills.getLockpick();
        this.Repair=skills.getRepair();
        this.Science=skills.getScience();
        this.Sneak=skills.getSneak();
        this.Barter=skills.getBarter();


    }

    @Override
    public void fromBytes(ByteBuf buf) {
        BigGuns= buf.readInt();
        SmallGuns= buf.readInt();
        EnergyWeapons= buf.readInt();
        Explosives= buf.readInt();
        MeleeWeapons= buf.readInt();
        Unarmed= buf.readInt();
        Medicine= buf.readInt();
        Lockpick= buf.readInt();
        Repair= buf.readInt();
        Science= buf.readInt();
        Sneak= buf.readInt();
        Barter= buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(BigGuns);
        buf.writeInt(SmallGuns);
        buf.writeInt(EnergyWeapons);
        buf.writeInt(Explosives);
        buf.writeInt(MeleeWeapons);
        buf.writeInt(Unarmed);
        buf.writeInt(Medicine);
        buf.writeInt(Lockpick);
        buf.writeInt(Repair);
        buf.writeInt(Science);
        buf.writeInt(Sneak);
        buf.writeInt(Barter);

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
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleSkillsMessage(message, playerMP);
            return null;
        }

    }
}
